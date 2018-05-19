<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ">
				<li id="about" class="nav-item"><a class="nav-link" href="${contextRoot}/about">About</a></li>
				<li id="contact" class="nav-item"><a class="nav-link" href="${contextRoot}/contact">Contact</a></li>
				<li id="listProducts" class="nav-item"><a class="nav-link" href="${contextRoot}/show/all/products">Products</a></li>
				<security:authorize access="hasAuthority('ADMIN')">
					<li id="manageProducts" class="nav-item"><a class="nav-link" href="${contextRoot}/manage/products">Manage Products</a></li>	
				</security:authorize>			
			</ul>
			
			<ul class="navbar-nav ml-auto">
			  <security:authorize access="isAnonymous()">
				<li id="register" class="nav-item"><a class="nav-link" href="${contextRoot}/register">Signup</a></li>
				<li id="register" class="nav-item"><a class="nav-link" href="${contextRoot}/login">Login</a></li>
			  </security:authorize>
			  
			  <security:authorize access="isAuthenticated()">
				<li class="nav-item dropdown">
			        <a class="nav-link dropdown-toggle" href="javascript:void(0)" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          ${userModel.fullName}
			        </a>
			        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
			         <security:authorize access="hasAuthority('USER')">
			          <a class="dropdown-item" href="${contextRoot}/cart">
			          	<i class="fa fa-shopping-cart" aria-hidden="true"></i>
			          	<span class="badge badge-info">${userModel.cart.cartLines}</span>
			          	- &#8377; ${userModel.cart.grandTotal}
			          </a>
			         </security:authorize>
			          <a class="dropdown-item" href="javascript:void(0)">Profile</a>
			          <div class="dropdown-divider"></div>
			          <a class="dropdown-item" href="${contextRoot}/perform-logout">Logout</a>
			        </div>
			    </li>
			  </security:authorize>
			</ul>
		</div>
	</div>
</nav>


<script>
	window.userRole = '${userModel.role}';
</script>
