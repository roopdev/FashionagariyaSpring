<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Fashionagariya - ${title}</title>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Theme CSS -->
<link href="${css}/bootstrap-theme.min.css" rel="stylesheet">

<!-- FontAwesomeCDN -->
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>
	<div class="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top">
			<div class="container">
				<a class="navbar-brand" href="${contextRoot}/home">
					<img src="${images}/brand.png" width="30" height="30" class="d-inline-block align-top" alt="">
					fashionagariya
				</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarResponsive" aria-controls="navbarResponsive"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
			</div>
		</nav>
		
		<div class="content">
		<div class="container">
			<div class="alert alert-success">
				<h3 class="text-center">Your Order is Confirmed!!</h3>
			</div>
		    <div class="row">
		        <div class="col-lg-12">
		    		<div class="invoice-title">
		    			<h2>Invoice</h2><h3 class="pull-right">Order # ${orderDetail.id}</h3>
		    		</div>
		    		<hr>
		    		<div class="row">
		    			<div class="col-lg-6">
		    				<address>
		    				<strong>Billed To:</strong><br>
		    					${orderDetail.user.firstName} ${orderDetail.user.lastName}<br>
		    					${orderDetail.billing.addressLineOne}<br>
		    					${orderDetail.billing.addressLineTwo}<br>
		    					${orderDetail.billing.city} - ${orderDetail.billing.postalCode}<br>
		    					${orderDetail.billing.state} - ${orderDetail.billing.country}
		    				</address>
		    			</div>
		    			<div class="col-lg-6 text-right">
		    				<address>
		        			<strong>Shipped To:</strong><br>
		    					${orderDetail.user.firstName} ${orderDetail.user.lastName}<br>
		    					${orderDetail.shipping.addressLineOne}<br>
		    					${orderDetail.shipping.addressLineTwo}<br>
		    					${orderDetail.shipping.city} - ${orderDetail.shipping.postalCode}<br>
		    					${orderDetail.shipping.state} - ${orderDetail.shipping.country}
		    				</address>
		    			</div>
		    		</div>
		    		<div class="row">
		    			<div class="col-lg-6">
		    				<address>
		    					<strong>Payment Method:</strong><br>
		    					Card Payment <br>
		    					Send to: ${orderDetail.user.email}
		    					Amount Paid: ${orderDetail.orderTotal}
		    				</address>
		    			</div>
		    			<div class="col-lg-6 text-right">
		    				<address>
		    					<strong>Order Date:</strong><br>
		    					${orderDetail.orderDate}<br><br>
		    				</address>
		    			</div>
		    		</div>
		    	</div>
		    </div>
		    
		    <div class="row">
		    	<div class="col-lg-12">
		    		<div class="card">
		    			<div class="card-header">
		    				<h3 class="card-title"><strong>Order summary</strong></h3>
		    			</div>
		    			<div class="card-body">
		    				<div class="table-responsive">
		    					<table class="table table-condensed">
		    						<thead>
		                                <tr>
		        							<td><strong>Item</strong></td>
		        							<td class="text-center"><strong>Price</strong></td>
		        							<td class="text-center"><strong>Quantity</strong></td>
		        							<td class="text-right"><strong>Totals</strong></td>
		                                </tr>
		    						</thead>
		    						<tbody>
		    							<!-- foreach ($order->lineItems as $line) or some such thing here -->
		    							<c:forEach items="${orderDetail.orderItems}" var="orderItem">
			    							<tr>
			    								<td>${orderItem.product.name}</td>
			    								<td class="text-center">&#8377; ${orderItem.buyingPrice}</td>
			    								<td class="text-center">${orderItem.productCount}</td>
			    								<td class="text-right">&#8377; ${orderItem.total}</td>
			    							</tr>
		    							</c:forEach>
		    						</tbody>
		    					</table>
		    				</div>
		    			</div>
		    		</div>
		    	</div>
		    </div>
		    <hr>
		    <div class="text-center">
		    	<a href="${contextRoot}/show/all/products" class="btn btn-lg btn-warning">Continue Shopping</a>
		    </div>

		</div>
	</div>

		<!-- Footer -->
		<%@include file="../../../shared/footer.jsp"%>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.min.js"></script>
		<script src="${js}/bootstrap.bundle.min.js"></script>

		<!-- Application JavaScript -->
		<script src="${js}/myapp.js"></script>

	</div>
</body>

</html>
