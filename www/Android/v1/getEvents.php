<?php

require_once '../includes/DbOperations.php';
$response = array();

if($_SERVER['REQUEST_METHOD']=='POST'){
        
	$db = new DbOperations();
	$numEvents = $db->getEvents()->num_rows;
	$result = $db->getEvents();

    if($numEvents > 0){
		$i = 0;
        $response['error'] = false;
		$response['num_rows'] = $numEvents; 
		while ($row = mysqli_fetch_array($result)){
			$response['tID'.$i] 	  = $row['eventID'];
			$response['oppTeam'.$i]   = $row['opposingTeam'];
			$response['location'.$i]  = $row['location'];
			$response['date'.$i] 	  = $row['date'];
			$response['time'.$i] 	  = $row['startTime'];
			$response['isHome'.$i] 	  = $row['home/away'];
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