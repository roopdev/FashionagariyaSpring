
<div class="container">
	<div class="row">
		 <div class="col-lg-9 offset-lg-1">
		 <h3>My order list</h3>
		 <c:forEach items="${OrderDetails}" var="orderDetail">
		 	<div class="row">
		 		<div class="col">
		 			<div class="card">
				 		<div class="card-header">
				 			<div class="row">
				 				<div class="col">
				 					<h4>Amount Paid: ${orderDetail.orderTotal}</h4>
				 				</div>
				 				<div class="col">
				 					<p class="pull-right">Order id # ${orderDetail.id}</p>
				 				</div>
				 			</div>
				 			<div class="row">
				 				<div class="col">
				 					<address>
				    					<strong>Payment Method:</strong><br>
				    					Card Payment <br>
				    					${orderDetail.user.email}
				    				</address>
				 				</div>
				 				<div class="col">
				 					<address>
				    					<strong>Order Date:</strong><br>
				    					${orderDetail.orderDate}<br><br>
				    				</address>
				 				</div>
				 			</div>				 			
				 		</div>
				 		<div class="card-body">
		    				<div class="table-responsive">
		    					<table class="table table-condensed">
		    						<thead>
		                                <tr>
		        							<td><strong>Item</strong></td>
		        							<td class="text-center"><strong>Price</strong></td>
		        							<td class="text-center"><strong>Quantity</strong></td>
		        							<td class="text-right"><strong>Totals</strong></td>
		                                </tr>
		    						</thead>
		    						<tbody>
		    							<!-- foreach ($order->lineItems as $line) or some such thing here -->
		    							<c:forEach items="${orderDetail.orderItems}" var="orderItem">
			    							<tr>
			    								<td>${orderItem.product.name}</td>
			    								<td class="text-center">&#8377; ${orderItem.buyingPrice}</td>
			    								<td class="text-center">${orderItem.productCount}</td>
			    								<td class="text-right">&#8377; ${orderItem.total}</td>
			    							</tr>
		    							</c:forEach>
		    						</tbody>
		    					</table>
		    				</div>
		    			</div>
				 	</div>
				 	<br>
		 		</div>
		 	</div>
		 	</c:forEach>
		 </div>
	</div>
</div>