<?php 	

require_once 'core.php';

$brandId = $_POST['brandId'];

$sql = "SELECT brand_id, brand_name, brand_active, brand_status FROM brands WHERE brand_id = $brandId";
$result = mysqli_query($conn, $sql);

if(mysqli_num_rows($result) > 0) { 
 $row =  mysqli_fetch_array($result);
} // if num_rows

mysqli_close($conn);

echo json_encode($row);