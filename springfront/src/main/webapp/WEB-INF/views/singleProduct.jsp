<div class="container">
	<!-- Breadcrumb component -->
	<div class="row">
		<div class="col-lg-12">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
				<li class="breadcrumb-item"><a
					href="${contextRoot}/show/all/products">Products</a></li>
				<li class="breadcrumb-item active" aria-current="page">${product.name}</li>
			</ol>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-4">
			<img class="img-thumbnail" src="${images}/${product.code}.jpg" />
		</div>
		<div class="col-lg-8">
			<h3>${product.name}</h3>
			<hr />

			<p>${product.description}</p>
			<hr />

			<h4>
				Price: <strong>&#8377; ${product.unitPrice} /-</strong>
			</h4>
			<hr />
			
			
			
			<security:authorize access="isAnonymous()">
				<c:choose>
					<c:when test="${product.quantity < 1 }">
						<h6>
							Qty. Available: <span style="color: red">Out Of Stock</span>
						</h6>
						<hr>
						<a href="javascript:void(0)"
							class="btn btn-outline-success disabled">
							<strike><i class="fa fa-cart-plus" aria-hidden="true"></i> Add to cart</strike>
						</a>
					</c:when>
					<c:otherwise>
						<h6>Qty. Available: ${product.quantity}</h6>
						<hr>
						<a href="${contextRoot}/cart/add/${product.id}/product"
							class="btn btn-outline-success"> <i class="fa fa-cart-plus"
							aria-hidden="true"></i> Add to cart
						</a>
					</c:otherwise>
				</c:choose>
			</security:authorize>
			
			<security:authorize access="hasAuthority('USER')">
				<c:choose>
					<c:when test="${product.quantity < 1 }">
						<h6>
							Qty. Available: <span style="color: red">Out Of Stock</span>
						</h6>
						<hr>
						<a href="javascript:void(0)"
							class="btn btn-outline-success disabled">
							<strike><i class="fa fa-cart-plus" aria-hidden="true"></i> Add to cart</strike>
						</a>
					</c:when>
					<c:otherwise>
						<h6>Qty. Available: ${product.quantity}</h6>
						<hr />
						<a href="${contextRoot}/cart/add/${product.id}/product"
							class="btn btn-outline-success"> <i class="fa fa-cart-plus"
							aria-hidden="true"></i> Add to cart
						</a>
					</c:otherwise>
				</c:choose>
			</security:authorize>

			<security:authorize access="hasAuthority('ADMIN')">
				<h6>Qty. Available: ${product.quantity}</h6>
				<hr />
				<a href="${contextRoot}/manage/${product.id}/product"
					class="btn btn-outline-warning"> <i class="fa fa-pencil"
							aria-hidden="true"></i> Edit
				</a>
			</security:authorize>
		</div>
	</div>
</div>