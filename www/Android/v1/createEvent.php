<?php

require_once '../includes/DbOperations.php';
$response = array();
//$date, $time, $location, $opposingTeam, $homeOrAway
if($_SERVER['REQUEST_METHOD']=='POST'){
    if(isset($_POST['date']) 
		and isset($_POST['time'])
			and isset($_POST['location'])
				and isset($_POST['opposingTeam'])
					and isset($_POST['homeOrAway'])){
        $db = new DbOperations();

        $result = $db->createEvent( $_POST['date'],
                                    $_POST['time'],
                                    $_POST['location'],
									$_POST['opposingTeam'],
									$_POST['homeOrAway']
                                  );
        if($result == 1){
            $response['error'] = false;
            $response['message'] = "Event created successfully";
        }elseif($result == 2){
            $response['error'] = true;
            $response['message'] = "Some error occurred please try again";
        }

    }else{
        $response['error'] = true;
        $response['message'] = "Required fields are missing";
    }
}else{
    $response['error'] = true;
    $response['message'] = "Invalid Request";
}

echo json_encode($response);