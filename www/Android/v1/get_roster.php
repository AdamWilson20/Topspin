<?php

require_once '../includes/DbOperations.php';
$response = array();

if($_SERVER['REQUEST_METHOD']=='POST'){
        
	$db = new DbOperations();
	$numEvents = $db->getRoster()->num_rows;
	$result = $db->getRoster();

    if($numEvents > 0){
		$i = 0;
        $response['error'] = false;
		$response['num_rows'] = $numEvents; 
		while ($row = mysqli_fetch_array($result)){
			$response['playerID'.$i] 	  = $row['player_id'];
			$response['playerImage'.$i]  = $row['playerImage'];
			$response['firstName'.$i] = $row['firstName'];
			$response['lastName'.$i]  = $row['lastName'];
			$response['height'.$i] 	  = $row['height'];
			$response['weight'.$i] 	  = $row['weight'];
			$response['year'.$i] 	  = $row['year'];
			$response['hometown'.$i]  = $row['hometown'];
			$i++;
		}
    }else{
        $response['error'] = false;
        $response['num_rows'] = $numEvents;
    }
	
}else{
    $response['error'] = true;
    $response['message'] = "Invalid Request";
}

echo json_encode($response);