<?php 	

require_once 'core.php';


$valid['success'] = array('success' => false, 'messages' => array());

$productId = $_POST['productId'];

if($productId) { 

 $sql = "UPDATE product SET active = 2, status = 2 WHERE product_id = {$productId}";

 if(mysqli_query($conn, $sql) === TRUE) {
 	$valid['success'] = true;
	$valid['messages'] = "Successfully Removed";		
 } else {
 	$valid['success'] = false;
 	$valid['messages'] = "Error while remove the brand";
 }
 
 mysqli_close($conn);

 echo json_encode($valid);
 
} // /if $_POST