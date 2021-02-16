<?php 

require_once 'core.php';

if($_POST) {

	$valid['success'] = array('success' => false, 'messages' => array());

	$bio = mysqli_real_escape_string($conn,$_POST['bio']); ;
	$userId = $_POST['user_id'];

	$sql = "UPDATE users SET bio = '$bio' WHERE user_id = {$userId}";
	if(mysqli_query($conn, $sql) === TRUE) {
		$valid['success'] = true;
		$valid['messages'] = "Successfully Update";	
	} else {
		$valid['success'] = false;
		$valid['messages'] = "Error while updating user info";
	}

	mysqli_close($conn);
	echo json_encode($valid);

}

?>