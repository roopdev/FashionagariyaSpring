<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@include file="../shared/flows-header.jsp" %>

<div class="container">
	<div class="row">
		<div class="col-lg-6 offset-lg-3">
			<div class="card">
				<div class="card-header bg-info text-white">
					<h4 class="text-center">Register</h4>
				</div>
				<div class="card-body">
					<sf:form id="registerForm" modelAttribute="user" method="POST" >
						<div class="form-group row">
							<label class="col-md-3 col-form-label">First Name</label>
							<div class="col-md-9">
								<sf:input type="text" path="firstName" class="form-control" placeholder="First Name" />
								<sf:errors path="firstName" cssClass="text-danger" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Last Name</label>
							<div class="col-md-9">
								<sf:input type="text" path="lastName" class="form-control" placeholder="Last Name" />
								<sf:errors path="lastName" cssClass="text-danger" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Email</label>
							<div class="col-md-9">
								<sf:input type="email" path="email" class="form-control" placeholder="Email id" />
								<sf:errors path="email" cssClass="text-danger" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Password</label>
							<div class="col-md-9">
								<sf:input type="password" path="password" class="form-control" placeholder="Password.." />
								<sf:errors path="password" cssClass="text-danger" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Confirm Password</label>
							<div class="col-md-9">
								<sf:input type="password" path="confirmPassword" class="form-control" placeholder="Re-enter password.." />
								<sf:errors path="confirmPassword" cssClass="text-danger" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Contact Number</label>
							<div class="col-md-9">
								<sf:input type="number" path="contactNumber" class="form-control" placeholder="Mobile number.." />
								<sf:errors path="contactNumber" cssClass="text-danger" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label">Select Role</label>
							<div class="col-md-9">
									<label class="custom-control custom-control-inline">
										<sf:radiobutton path="role" value="USER" checked="checked"/> User
									</label>
									<label class="custom-control custom-control-inline">
										<sf:radiobutton path="role" value="SUPPLIER"/> Supplier
									</label>	
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-4 offset-md-4">
								<button type="submit" name="_eventId_billing" class="btn btn-block btn-outline-primary">
									Next - Billing <i class="fa fa-chevron-right" aria-hidden="true"></i>
								</button>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>
</div>
			
<%@include file="../shared/flows-footer.jsp" %>