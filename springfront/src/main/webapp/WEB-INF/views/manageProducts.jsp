<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<c:if test="${not empty message}">	
		<div class="row">			
			<div class="col-lg-8 offset-lg-2">			
				<div class="alert alert-warning alert-dismissible fade show">
					<strong>${message}</strong>
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
    					<span aria-hidden="true">&times;</span>
  					</button>	
  				</div>			
			</div>
		</div>
	</c:if>
	

	<div class="row">
		<div class="col-lg-6 offset-lg-3">
			<div class="card">
				<div class="card-header bg-info text-white">
					<h4 class="text-center">Product Management</h4>
				</div>
				<div class="card-body">
					<sf:form modelAttribute="product" action="${contextRoot}/manage/products" method="POST" enctype="multipart/form-data">
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Name</label>
							<div class="col-md-9">
								<sf:input type="text" path="name" class="form-control" placeholder="Product Name" />
								<sf:errors path="name" cssClass="text-danger" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Brand</label>
							<div class="col-md-9">
								<sf:input type="text" path="brand" class="form-control" placeholder="Brand Name" />
								<sf:errors path="brand" cssClass="text-danger" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Description</label>
							<div class="col-md-9">
								<sf:textarea path="description" class="form-control" placeholder="Enter product description.." />
								<sf:errors path="description" cssClass="text-danger" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Unit Price</label>
							<div class="col-md-9">
								<sf:input type="number" path="unitPrice" class="form-control" placeholder="Enter product unit price.." />
								<sf:errors path="unitPrice" cssClass="text-danger" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Quantity</label>
							<div class="col-md-9">
								<sf:input type="number" path="quantity" class="form-control" placeholder="Number of products.." />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Upload Image</label>
							<div class="col-md-9">
								<sf:input type="file" path="file" id="file" class="form-control" />
								<sf:errors path="file" cssClass="text-danger" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Category</label>
							<div class="col-md-9">
								<sf:select class="form-control" id="categoryId" path="categoryId" items="${categories}" itemLabel="name" itemValue="id"/>
								<div class="text-right">
									<br/>
									<button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#myCategoryModal">Add new Category</button>
								</div>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-4 offset-md-4">
								<input type="submit" name="submit" value="Save" class="btn btn-block btn-outline-primary" />
								<br/>			
									<sf:hidden path="id"/>
									<sf:hidden path="code"/>
									<sf:hidden path="supplierId"/>
									<sf:hidden path="active"/>
									<sf:hidden path="purchases"/>
									<sf:hidden path="views"/>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal Starts here -->
	
	<div class="modal fade" id="myCategoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header bg-primary">
	        <h4 class="modal-title" id="exampleModalLabel">New Category</h4>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <form>
	          <div class="form-group">
	            <label>Name</label>
	            <input type="text" class="form-control" placeholder="Category name.." />
	            <em class="form-text text-danger">Please provide name!</em>
	          </div>
	          <div class="form-group">
	            <label>Description</label>
	            <textarea class="form-control" placeholder="Enter category description here.."></textarea>
	            <em class="form-text text-danger">Please provide category description!</em>
	          </div>
	          <div class="form-group">
	            <input type="submit" class="btn btn-sm btn-block btn-outline-success" value="Save" />
	          </div>
	        </form>
	      </div>
	    </div>
	  </div>
	</div>
	<hr>
	
	<!-- All the products both active and non-active -->
	<h1>Available Products</h1>
	
</div>