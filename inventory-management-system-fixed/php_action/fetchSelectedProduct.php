<?php 	

require_once 'core.php';

$productId = $_POST['productId'];

$sql = "SELECT product_id, product_name, product_image, brand_id, categories_id, quantity, rate, active, status FROM product WHERE product_id = $productId";
$result = mysqli_query($conn, $sql);

if(mysqli_num_rows($result) > 0) { 
 $row =  mysqli_fetch_array($result);
} // if num_rows

mysqli_close($conn);

echo json_encode($row);