<%@include file="../shared/flows-header.jsp" %>
<div class="container">
	<div class="row">
		<div class="col-lg-6">
			<div class="card text-center">
				<div class="card-header bg-info text-white">
					<h4>Personal Details</h4>
				</div>
				<div class="card-body">
					<h3>Name : <strong>${registerModel.user.firstName} ${registerModel.user.lastName}</strong></h3>
					<h4>Email : <strong>${registerModel.user.email}</strong></h4>
					<h4>Contact : <strong>${registerModel.user.contactNumber}</strong></h4>
					<h4>Role : <strong>${registerModel.user.role}</strong></h4>
				</div>
				<div class="card-footer">
					<a href="${flowExecutionUrl}&_eventId_personal" class="btn btn-sm btn-outline-primary">
						<i class="fa fa-pencil" aria-hidden="true"></i>
					</a>
				</div>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="card text-center">
				<div class="card-header bg-info text-white">
					<h4>Billing Address</h4>
				</div>
				<div class="card-body">
					<p>${registerModel.billing.addressLineOne}, </p>
					<p>${registerModel.billing.addressLineTwo}, </p>
					<p>${registerModel.billing.city} -  ${registerModel.billing.postalCode}, </p>
					<p>${registerModel.billing.state}</p>
					<p>${registerModel.billing.country}</p>
				</div>
				<div class="card-footer">
					<a href="${flowExecutionUrl}&_eventId_billing" class="btn btn-sm btn-outline-primary">
						<i class="fa fa-pencil" aria-hidden="true"></i>
					</a>
				</div>
			</div>
		</div>
	</div>
	<br>
	<hr>
	<div class="row">
		<div class="col-lg-4 offset-lg-4">
			<a href="${flowExecutionUrl}&_eventId_submit" class="btn btn-block btn-outline-primary">Confirm</a>
		</div>
	</div>
</div>
<%@include file="../shared/flows-footer.jsp" %>