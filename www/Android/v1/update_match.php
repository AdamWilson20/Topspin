<?php

require_once '../includes/DbOperations.php';
$response = array();
//$matchID, eventID, $homeTeamSets, $awayTeamSets, $result
if($_SERVER['REQUEST_METHOD']=='POST'){
    if(isset($_POST['matchID']) 
		and isset($_POST['eventID'])
			and isset($_POST['homeTeamSets'])
				and isset($_POST['awayTeamSets'])
					and isset($_POST['result'])){
        $db = new DbOperations();

        $result = $db->updateMatch( $_POST['matchID'],
                                    $_POST['eventID'],
                                    $_POST['homeTeamSets'],
								    $_POST['awayTeamSets'],
								    $_POST['result']
                                     );
        if($result == 1){
            $response['error'] = false;
            $response['message'] = "Match updated successfully";
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

