
<div class="container-fluid">
	<!-- carousel -->
	<div class="row">
		<div class="col">
			<div id="carouselExampleIndicators" class="carousel slide my-4"
				data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#carouselExampleIndicators" data-slide-to="0"
						class="active"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<div class="carousel-item active">
						<img class="d-block img-fluid" src="http://placehold.it/1360x350"
							alt="First slide">
					</div>
					<div class="carousel-item">
						<img class="d-block img-fluid" src="http://placehold.it/1360x350"
							alt="Second slide">
					</div>
					<div class="carousel-item">
						<img class="d-block img-fluid" src="http://placehold.it/1360x350"
							alt="Third slide">
					</div>
				</div>
				<a class="carousel-control-prev" href="#carouselExampleIndicators"
					role="button" data-slide="prev"> <span
					class="carousel-control-prev-icon" aria-hidden="true"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
					role="button" data-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
		</div>
	</div>
	<!-- /.carousel -->
		<h2>Latest Products-</h2>
	<!-- Products -->
	<div class="row">
		<c:forEach items="${products}" var="product">
				<div class="col-lg-3 mb-4">
					<div class="card h-100">
						<a href="${contextRoot}/show/${product.id}/product"><img class="card-img-top"
							src="${images}/${product.code}.jpg" alt=""></a>
						<div class="card-body">
							<h4 class="card-title">
								<a href="${contextRoot}/show/${product.id}/product">${product.name}</a>
							</h4>
							<h5>&#8377; ${product.unitPrice}</h5>
							<p class="card-text">${product.description}</p>
						</div>
						<div class="card-footer">
							<a href="" class="btn btn-outline-danger"> 
								<i class="fa fa-heart" aria-hidden="true"></i>
							</a>
							<a href="${contextRoot}/cart/add/${product.id}/product" class="btn btn-outline-success pull-right"> 
								<i class="fa fa-cart-plus" aria-hidden="true"></i>
							</a>
						</div>
					</div>
				</div>
		</c:forEach>
	</div>
	<!-- /.Products -->

</div>
<!-- /.container -->