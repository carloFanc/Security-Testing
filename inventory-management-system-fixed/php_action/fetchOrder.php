<?php

require_once 'core.php';

$sql = "SELECT order_id, order_date, client_name, client_contact, payment_status FROM orders WHERE order_status = 1";
$result = mysqli_query($conn, $sql);



$output = array('data' => array());

if(mysqli_num_rows($result) > 0) {

 $paymentStatus = "";
 $x = 1;

 while($row =  mysqli_fetch_array($result)) {
 	$orderId = $row[0];

 	$countOrderItemSql = "SELECT count(*) FROM order_item WHERE order_id = $orderId";
 	$itemCountResult = mysqli_query($conn, $countOrderItemSql);
 	$itemCountRow = mysqli_fetch_row($itemCountResult);


 	// active
 	if($row[4] == 1) {
 		$paymentStatus = "<label class='label label-success'>Full Payment</label>";
 	} else if($row[4] == 2) {
 		$paymentStatus = "<label class='label label-info'>Advance Payment</label>";
 	} else {
 		$paymentStatus = "<label class='label label-warning'>No Payment</label>";
 	} // /else

 	$button = '<!-- Single button -->
	<div class="btn-group">
	  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    Action <span class="caret"></span>
	  </button>
	  <ul class="dropdown-menu">
	    <li><a href="orders.php?o=editOrd&i='.$orderId.'" id="editOrderModalBtn"> <i class="glyphicon glyphicon-edit"></i> Edit</a></li>

	    <li><a type="button" data-toggle="modal" id="paymentOrderModalBtn" data-target="#paymentOrderModal" onclick="paymentOrder('.$orderId.')"> <i class="glyphicon glyphicon-save"></i> Payment</a></li>

	    <li><a type="button" onclick="printOrder('.$orderId.')"> <i class="glyphicon glyphicon-print"></i> Print </a></li>

	    <li><a type="button" data-toggle="modal" data-target="#removeOrderModal" id="removeOrderModalBtn" onclick="removeOrder('.$orderId.')"> <i class="glyphicon glyphicon-trash"></i> Remove</a></li>
	  </ul>
	</div>';

 	$output['data'][] = array(
 		// image
 		$x,
 		// order date
 		$row[1],
 		// client name
 		htmlentities($row[2]),
 		// client contact
 		htmlentities($row[3]), 		 	
 		$itemCountRow,
 		$paymentStatus,
 		// button
 		$button
 		);
 	$x++;
 } // /while

}// if num_rows

mysqli_close($conn);

echo json_encode($output);
