<?php 	

require_once 'core.php';

$categoriesId = $_POST['categoriesId'];

$sql = "SELECT categories_id, categories_name, categories_active, categories_status FROM categories WHERE categories_id = $categoriesId";
$result = mysqli_query($conn, $sql);

if(mysqli_num_rows($result) > 0) { 
 $row =  mysqli_fetch_array($result);
} // if num_rows

mysqli_close($conn);

echo json_encode($row);