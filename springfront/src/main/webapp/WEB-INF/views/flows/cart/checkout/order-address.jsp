<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@include file="../../shared/flows-header.jsp" %>

<div class="container">
	<div class="row">
		<div class="col-lg-6">
			<h5>Select from previous addresses</h5><hr>
			
				<c:forEach items="${addresses}" var="address">
				<div class="row">
					<div class="col">
						<h3>${address.addressLineOne}</h3>
						<h3>${address.addressLineTwo}</h3>
						<h4>${address.city} - ${address.postalCode}</h4>
						<h4>${address.state} - ${address.country}</h4>
						<div class="text-center">
							<a href="${flowExecutionUrl}&_eventId_addressSelection&shippingId=${address.id}" class="btn btn-sm btn-outline-primary">Select</a>
						</div>
					</div>
				</div>
				<hr>
				</c:forEach>
			
		</div>
		
		<div class="col-lg-6">
			<div class="card">
				<div class="card-header bg-info text-white">
					<h4 class="text-center">New Shipping  Address</h4>
				</div>
				<div class="card-body">
					<sf:form id="billingForm" modelAttribute="shipping" method="POST" >
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Address Line One</label>
							<div class="col-md-9">
								<sf:input type="text" path="addressLineOne" class="form-control" placeholder="Address line one.." />
								<sf:errors path="addressLineOne" cssClass="text-danger" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Address Line Two</label>
							<div class="col-md-9">
								<sf:input type="text" path="addressLineTwo" class="form-control" placeholder="Address line two.." />
								<sf:errors path="addressLineTwo" cssClass="text-danger" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">City</label>
							<div class="col-md-9">
								<sf:input type="text" path="city" class="form-control" placeholder="City.." />
								<sf:errors path="city" cssClass="text-danger" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">State</label>
							<div class="col-md-9">
								<sf:input type="text" path="state" class="form-control" placeholder="State.." />
								<sf:errors path="state" cssClass="text-danger" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Country</label>
							<div class="col-md-9">
								<sf:input type="text" path="country" class="form-control" placeholder="Country.." />
								<sf:errors path="country" cssClass="text-danger" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Postal Code</label>
							<div class="col-md-9">
								<sf:input type="number" path="postalCode" class="form-control" placeholder="Postal code.." />
								<sf:errors path="postalCode" cssClass="text-danger" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-lg-12 text-center">
								<button type="submit" name="_eventId_saveAddress" class="btn btn-sm btn-outline-primary">
									<i class="fa fa-plus" aria-hidden="true"></i> Add Address
								</button>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>
</div>
			
<%@include file="../../shared/flows-footer.jsp" %>