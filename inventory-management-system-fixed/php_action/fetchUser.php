<?php



require_once 'core.php';

$sql = "SELECT * FROM users";

$result = mysqli_query($conn, $sql);

$output = array('data' => array());
if(mysqli_num_rows($result) > 0) {

 // $row = $result->fetch_array();
 $active = "";

 while($row = mysqli_fetch_array($result)) {
 	$userid = $row[0];
 	// active
 	$username = $row[1];

 	$button = '<!-- Single button -->
	<div class="btn-group">
	  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    Action <span class="caret"></span>
	  </button>
	  <ul class="dropdown-menu">
	    <li><a type="button" data-toggle="modal" id="editUserModalBtn" data-target="#editUserModal" onclick="editUser('.$userid.')"> <i class="glyphicon glyphicon-edit"></i> Edit</a></li>
	    <li><a type="button" data-toggle="modal" data-target="#removeUserModal" id="removeUserModalBtn" onclick="removeUser('.$userid.')"> <i class="glyphicon glyphicon-trash"></i> Remove</a></li>
	  </ul>
	</div>';



 	$output['data'][] = array(
 		// name
 		htmlentities($username),
 		// button
 		$button
 		);
 } // /while

}// if num_rows

mysqli_close($conn);

echo json_encode($output);
