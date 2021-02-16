<?php 	

require_once 'core.php';

$productId = $_GET['i'];

$sql = "SELECT product_image FROM product WHERE product_id = {$productId}";
$data = mysqli_query($conn, $sql);
$result = mysqli_fetch_row($data);

mysqli_close($conn);

echo "stock/" . $result[0];
