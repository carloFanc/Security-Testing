<?php 	

require_once 'core.php';

$userid = $_POST['userid'];

$sql = "SELECT * FROM users WHERE user_id = $userid";
$result = mysqli_query($conn, $sql);

if(mysqli_num_rows($result) > 0) { 
 $row = mysqli_fetch_array($result);
} // if num_rows

mysqli_close($conn);

echo json_encode($row);