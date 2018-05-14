$(function() {
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break
	default:
		if(menu == "Home") break;
		$('#listProducts').addClass('active');
		$('#a_'+menu).addClass('active');
		break;
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
					 return '<img src="' +window.contextRoot+ '/resources/images/' +data+ '.jpg" class="img-thumbnail"/>';
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
					 
					 if(row.quantity < 1) {
						 str += '<a href="javascript: void(0)" class="btn btn-sm btn-success disabled"><i class="fa fa-cart-plus" aria-hidden="true"></i></a>';
					 }
					 else {
						 str += '<a href="'+window.contextRoot+ '/cart/add/'+data+'/product" class="btn btn-sm btn-success"><i class="fa fa-cart-plus" aria-hidden="true"></i></a>';
					 }
					 
					 
					 return str;
				 }
				}
			]
		});
	}
	
});