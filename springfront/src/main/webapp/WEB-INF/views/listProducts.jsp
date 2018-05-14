<div class="container">
	<div class="row">
		<!-- Sidebar -->
		<div class="col-lg-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>

		<!-- List of products -->
		<div class="col-lg-9">
			<!-- Breadcrumb component -->
			<div class="row">
				<div class="col-lg-12">
					<c:if test="${userClickAllProducts == true }">
						<script>
							window.categoryId = '';
						</script>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">All
								products</li>
						</ol>
					</c:if>

					<c:if test="${userClickCategoryProducts == true }">
						<script>
							window.categoryId = '${category.id}';
						</script>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">Category</li>
							<li class="breadcrumb-item active" aria-current="page">
								${category.name}</li>
						</ol>
					</c:if>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12">
					<table id="productListTable"
						class="table table-striped table-bordered">
						<thead>
							<tr>
								<th scope="col">Code</th>
								<th scope="col">Name</th>
								<th scope="col">Brand</th>
								<th scope="col">Price</th>
								<th scope="col">Qty. Available</th>
								<th scope="col"></th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th scope="col">Code</th>
								<th scope="col">Name</th>
								<th scope="col">Brand</th>
								<th scope="col">Price</th>
								<th scope="col">Qty. Available</th>
								<th scope="col"></th>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>