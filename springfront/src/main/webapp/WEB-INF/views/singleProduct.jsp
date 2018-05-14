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

			<c:choose>
				<c:when test="${product.quantity < 1 }">
					<h6>
						Qty. Available: <span style="color: red">Out Of Stock</span>
					</h6>
				</c:when>
				<c:otherwise>
					<h6>Qty. Available: ${product.quantity}</h6>
					<a href="${contextRoot}/cart/add/${product.id}/product"
						class="btn btn-outline-success"> <i class="fa fa-cart-plus"
						aria-hidden="true"></i> Add to cart
					</a>
				</c:otherwise>
			</c:choose>


		</div>
	</div>
</div>