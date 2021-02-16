<?php require_once 'includes/header.php'; ?>

<?php

$sql = "SELECT * FROM product WHERE status = 1";
$query = mysqli_query($conn, $sql);
$countProduct = mysqli_num_rows($query);

$orderSql = "SELECT * FROM orders WHERE order_status = 1";
$orderQuery = mysqli_query($conn, $orderSql);
$countOrder = mysqli_num_rows($orderQuery);

$totalRevenue = 0;
while ($orderResult = mysqli_fetch_assoc($orderQuery)) {
	$totalRevenue += (float)$orderResult['paid'];
}

$lowStockSql = "SELECT * FROM product WHERE quantity <= 3 AND status = 1";
$lowStockQuery = mysqli_query($conn, $lowStockSql);
$countLowStock = mysqli_num_rows($lowStockQuery);

$userwisesql = "SELECT users.username , SUM(orders.grand_total) as totalorder FROM orders INNER JOIN users ON orders.user_id = users.user_id WHERE orders.order_status = 1 GROUP BY orders.user_id";
$userwiseQuery = mysqli_query($conn, $userwisesql);
$userwieseOrder = mysqli_num_rows($userwiseQuery);

mysqli_close($conn);

?>


<style type="text/css">
	.ui-datepicker-calendar {
		display: none;
	}
</style>

<!-- fullCalendar 2.2.5-->
    <link rel="stylesheet" href="assets/plugins/fullcalendar/fullcalendar.min.css">
    <link rel="stylesheet" href="assets/plugins/fullcalendar/fullcalendar.print.css" media="print">

<div class="row">
	<?php { ?>
		<div class="col-md-4">
			<div class="panel panel-success">
				<div class="panel-heading">

				<?php  if(isset($_SESSION['userId']) && $_SESSION['userId']==1) { ?>
					<a href="product.php" style="text-decoration:none;color:black;">
						Welcome back
						<span class="badge pull pull-right"><?php echo htmlentities($_SESSION['username']); ?></span>
					</a>
				<?php } ?>

				<?php  if(isset($_SESSION['userId']) && $_SESSION['userId']!=1) { ?>
					<a style="text-decoration:none;color:black;">
						Welcome back
						<span class="badge pull pull-right"><?php echo htmlentities($_SESSION['username']); ?></span>
					</a>
				<?php } ?>

				</div> <!--/panel-hdeaing-->
			</div> <!--/panel-->
		</div> <!--/col-md-4-->
	<?php } ?>
</div>

<div class="row">
	<?php  if(isset($_SESSION['userId']) && $_SESSION['userId']==1) { ?>
	<div class="col-md-4">
		<div class="panel panel-success">
			<div class="panel-heading">

				<a href="product.php" style="text-decoration:none;color:black;">
					Total Product
					<span class="badge pull pull-right"><?php echo $countProduct; ?></span>
				</a>

			</div> <!--/panel-hdeaing-->
		</div> <!--/panel-->
	</div> <!--/col-md-4-->

	<div class="col-md-4">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<a href="product.php" style="text-decoration:none;color:black;">
					Low Stock
					<span class="badge pull pull-right"><?php echo $countLowStock; ?></span>
				</a>

			</div> <!--/panel-hdeaing-->
		</div> <!--/panel-->
	</div> <!--/col-md-4-->


	<?php } ?>
		<div class="col-md-4">
			<div class="panel panel-info">
			<div class="panel-heading">
				<a href="orders.php?o=manord" style="text-decoration:none;color:black;">
					Total Orders
					<span class="badge pull pull-right"><?php echo $countOrder; ?></span>
				</a>

			</div> <!--/panel-hdeaing-->
		</div> <!--/panel-->
		</div> <!--/col-md-4-->



	<div class="col-md-4">
		<div class="card">
		  <div class="cardHeader">
		    <h1><?php echo date('d'); ?></h1>
		  </div>

		  <div class="cardContainer">
		    <p><?php echo date('l') .' '.date('d').', '.date('Y'); ?></p>
		  </div>
		</div>
		<br/>

		<div class="card">
		  <div class="cardHeader" style="background-color:#245580;">
		    <h1><?php if($totalRevenue) {
		    	echo $totalRevenue;
		    	} else {
		    		echo '0';
		    		} ?></h1>
		  </div>

		  <div class="cardContainer">
		    <p> INR Total Revenue</p>
		  </div>
		</div>

	</div>

	<?php  if(isset($_SESSION['userId']) && $_SESSION['userId']==1) { ?>
	<div class="col-md-8">
		<div class="panel panel-default">
			<div class="panel-heading"> <i class="glyphicon glyphicon-calendar"></i> User Wise Order</div>
			<div class="panel-body">
				<table class="table" id="productTable">
			  	<thead>
			  		<tr>
			  			<th style="width:40%;">Name</th>
			  			<th style="width:20%;">Orders in Rupees</th>
			  		</tr>
			  	</thead>
			  	<tbody>
					<?php while ($orderResult = mysqli_fetch_assoc($userwiseQuery)) { ?>
						<tr>
							<td><?php echo htmlentities($orderResult['username'])?></td>
							<td><?php echo $orderResult['totalorder']?></td>

						</tr>

					<?php } ?>
				</tbody>
				</table>
				<!--<div id="calendar"></div>-->
			</div>
		</div>

	</div>
	<?php  } ?>

</div> <!--/row-->

<!-- fullCalendar 2.2.5 -->
<script src="assets/plugins/moment/moment.min.js"></script>
<script src="assets/plugins/fullcalendar/fullcalendar.min.js"></script>


<script type="text/javascript">
	$(function () {
			// top bar active
	$('#navDashboard').addClass('active');

      //Date for the calendar events (dummy data)
      var date = new Date();
      var d = date.getDate(),
      m = date.getMonth(),
      y = date.getFullYear();

      $('#calendar').fullCalendar({
        header: {
          left: '',
          center: 'title'
        },
        buttonText: {
          today: 'today',
          month: 'month'
        }
      });


    });
</script>

<?php require_once 'includes/footer.php'; ?>
