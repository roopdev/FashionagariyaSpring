$(function() {
	
	// Active menu
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	case 'My Cart':
		$('#userCart').addClass('active');
		break;
	default:
		if(menu == "Home") break;
		$('#listProducts').addClass('active');
		$('#a_'+menu).addClass('active');
		break;
	}
	
	// for handling CSRF token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if((token!=undefined && header !=undefined) && (token.length > 0 && header.length > 0)) {		
		// set the token header for the ajax request
		$(document).ajaxSend(function(e, xhr, options) {			
			xhr.setRequestHeader(header,token);			
		});				
	}
	
	
	// DataTable 
	
	var $table = $('#productListTable');
	
	if($table.length) {
		console.log('Inside table');
		var jsonUrl = '';
		if(window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		}
		else {
			jsonUrl = window.contextRoot + '/json/data/category/'+ window.categoryId +'/products';
		}
		
		$table.DataTable({
			lengthMenu: [[3,5,10,-1],['3','5','10','All']],
			pageLength: 5,
			ajax: {
				url: jsonUrl,
				dataSrc: ''			
			},
			columns: [
				{data: 'code',
				 mRender: function(data,type,row) {
					 return '<img src="' +window.contextRoot+ '/resources/images/' +data+ '.jpg" class="DataTableImg"/>';
				 }
				},
				{data: 'name'},
				{data: 'brand'},
				{data: 'unitPrice', 
				 mRender: function(data,type,row) {
					return '&#8377; ' + data;
				 }
				},
				{data: 'quantity',
				 mRender: function(data, type, row) {
					 if (data < 1) {
							return '<span style="color:red">Out of Stock!</span>';
						}
						return data;
				 }
				},
				{data: 'id',
				 bSortable: false,
				 mRender: function(data,type,row){
					 var str = '';
					 str += '<a href="'+window.contextRoot+ '/show/'+data+'/product" class="btn btn-sm btn-primary"><i class="fa fa-eye" aria-hidden="true"></i></a> &#160;';

					 if(userRole == 'ADMIN') {
						 str += '<a href="'+window.contextRoot+ '/manage/'+data+'/product" class="btn btn-sm btn-warning"><i class="fa fa-pencil" aria-hidden="true"></i></a>';
					 }
					 else {
						 
						 if(row.quantity < 1 || userRole == 'SUPPLIER' ) {
							 str += '<a href="javascript: void(0)" class="btn btn-sm btn-success disabled"><i class="fa fa-cart-plus" aria-hidden="true"></i></a>';
						 }
						 else {
						 str += '<a href="'+window.contextRoot+ '/cart/add/'+data+'/product" class="btn btn-sm btn-success"><i class="fa fa-cart-plus" aria-hidden="true"></i></a>';
						 }
					 } 
					 
					 return str;
				 }
				}
			]
		});
	}
	
	
	// Alert function
	
	var $alert = $(".alert");
	
	if($alert.length) {
		setTimeout(function() {
			$alert.fadeOut('slow')
		} , 3000)
	}
	
	
	
	
	// Admin DataTable
	var $adminProductsTable = $('#adminProductsTable');
	
	if($adminProductsTable.length) {
		console.log('Inside table');
		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
		
		
		$adminProductsTable.DataTable({
			lengthMenu: [[10,30,50,-1],['10','30','50','All']],
			pageLength: 30,
			ajax: {
				url: jsonUrl,
				dataSrc: ''			
			},
			columns: [
				{data: 'id'},
				{data: 'code',
				 bSortable: false,
				 mRender: function(data,type,row) {
					 return '<img src="' +window.contextRoot+ '/resources/images/' +data+ '.jpg" class="DataTableImg"/>';
				 }
				},
				{data: 'name'},
				{data: 'brand'},
				{data: 'quantity',
				 mRender: function(data, type, row) {
					 if (data < 1) {
							return '<span style="color:red">Out of Stock!</span>';
						}
						return data;
				 }
				},
				{data: 'unitPrice', 
				 mRender: function(data,type,row) {
					return '&#8377; ' + data;
				 }
				},
				{data: 'active',
				 bSortable: false,
				 mRender: function(data, type, row) {
					 var str = '';
					 
					 str += '<label class="switch">';
					 if(data) {
						 str += '<input type="checkbox" checked="checked" value="'+row.id+'">';
					 }
					 else {
						 str += '<input type="checkbox" value="'+row.id+'">';
					 }					 
					 str += '<span class="slider round"></span></label>';
					 
					 return str;
				 }				 
				},
				{data: 'id',
				 bSortable: false,
				 mRender: function(data, type, row) {
					 var str = '';
					 
					 str += '<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-sm btn-warning">';
					 str += '<i class="fa fa-pencil" aria-hidden="true"></i></a>';
					 
					 return str;
				 }
				}
			],
			initComplete: function() {
				var api = this.api();
				// Toggle switch 
				api.$('.switch input[type="checkbox"]').on('change', function() {
					var checkbox = $(this);
					var checked = checkbox.prop('checked');
					var dMsg = (checked)? 'You want to activate the product?':
										  'You want to de-activate the product?';
					var value = checkbox.prop('value');
					
					bootbox.confirm({
						size: 'medium',
						title: 'Product activation & deactivation',
						message: dMsg,
						callback: function(confirmed) {
							if(confirmed) {
								console.log(value);
								var activationUrl = window.contextRoot + '/manage/product/' + value + '/activation';
								$.post(activationUrl, function(data) {
									bootbox.alert({
										size: 'medium',
										title: 'Information',
										message: data
									});
								});
								
							}
							else {
								checkbox.prop('checked', !checked);
							}
						}
					});
				});
			}
		});
	}
	
	// JQuery validation
	
	function errorPlacement(error, element) {
		error.addClass("text-danger");
		
		error.insertAfter(element);
		
		element.parents(".validate").addClass("had-feedback");
	}
	
	// for category form
	var $categoryForm = $('#categoryForm');
	
	if($categoryForm.length) {
		$categoryForm.validate({
			rules: {
				name: {
					required: true,
					minlength: 3
				},
				description: {
					required: true,
					minlength: 3
				}
			},
			messages: {
				name: {
					required: 'Please provide category name.',
					minlength: 'Provide minimum 5 characters'
				},
				description: {
					required: 'Please provide category description.',
					minlength: 'Provide minimum 5 characters'
				}
			},
			errorElement: "em",
			errorPlacement: function(error, element) {
				errorPlacement(error, element);
			}
		});
	}
	
	// for login form
var $loginForm = $('#loginForm');
	
	if($loginForm.length) {
		$loginForm.validate({
			rules: {
				username: {
					required: true,
					email: true
				},
				password: {
					required: true,
				}
			},
			messages: {
				username: {
					required: 'Please provide email address.',
					email: 'Provide valid email id'
				},
				password: {
					required: 'Please provide password.',
				}
			},
			errorElement: "em",
			errorPlacement: function(error, element) {
				errorPlacement(error, element);
			}
		});
	}
	
	// Cart refresh button
	$('button[name="refreshCart"]').click(function() {
		var cartLineId = $(this).attr('value');
		var countElement = $('#count_' + cartLineId);
		
		var originalCount = countElement.attr('value');
		var currentCount = countElement.val();
		
		if(currentCount !== originalCount) {
			console.log("current count: " + currentCount);
			console.log("original count: " + originalCount);
			
			if(currentCount < 1  || currentCount > 3) {
				countElement.val(originalCount);
				bootbox.alert({
					size: 'medium',
					title: 'Error',
					message: 'Product should be minimum 1 and maximum 3'
				});
			}
			else {
				var updateUrl = window.contextRoot + '/cart/' + cartLineId + '/update?count=' + currentCount;
				console.log(currentCount);
				window.location.href = updateUrl;
			}
		}
	});
	
	
});