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

<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
</script>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Theme CSS -->
<link href="${css}/bootstrap-theme.min.css" rel="stylesheet">

<!-- FontAwesomeCDN -->
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

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

		<!-- Page Content -->
		<div class="content">
			<div class="container">			
				<c:if test="${not empty message}">
					<div class="row">
						<div class="col-lg-6 offset-lg-3">
							<div class="alert alert-warning text-center" role="alert">
								${message}
							</div>
						</div>
					</div>
				</c:if>
				<div class="row">
					<div class="col-lg-6 offset-lg-3">
						<div class="card">
							<div class="card-header bg-info">
								<h3>Login</h3>
							</div>
							<div class="card-body">
								<form action="${contextRoot}/login" method="POST" id="loginForm">
									<div class="form-group">
										<label for="username">Email:</label>
										<input type="email" id="username" name="username" class="form-control">
									</div>
									<div class="form-group">
										<label for="password">Password:</label>
										<input type="password" id="password" name="password" class="form-control">
									</div>
									<div class="form-group text-center">
										<input type="submit" class="btn btn-outline-primary" value="Login">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
									</div>
								</form>
							</div>
							<div class="card-footer ml-auto">
								<p>Not a member? <a href="${contextRoot}/register">Register here!</a></p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.min.js"></script>
		<script src="${js}/bootstrap.bundle.min.js"></script>
		<!-- Jquery Validate JavaScript -->
		<script src="${js}/jquery.validate.js"></script>
		<!-- Application JavaScript -->
		<script src="${js}/myapp.js"></script>

	</div>
</body>

</html>
