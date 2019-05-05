<?php

require_once '../includes/DbOperations.php';
$response = array();
//$matchID, eventID, $homeTeamSets, $awayTeamSets, $result
if($_SERVER['REQUEST_METHOD']=='POST'){
    if(isset($_POST['matchID']) 
		and isset($_POST['eventID'])){
        $db = new DbOperations();

        $result = $db->getMatch($_POST['matchID'],
                                 $_POST['eventID']);
         if($result){

            $result['error'] = false;
			$result['matchType'] = $result['matchType'];
			$result['homePlayer1'] = $result['homePlayer1'];
			$result['homePlayer2'] = $result['homePlayer2'];
			$result['awayPlayer1'] = $result['awayPlayer1'];
			$result['awayPlayer2'] = $result['awayPlayer2'];
			$result['homeTeamSets'] = $result['homeTeamSets'];
			$result['awayTeamSets'] = $result['awayTeamSets'];
			$result['result'] = $result['result'];
			
			
        }else{
            $response['error'] = true;
            $response['message'] = "Some error occurred please try again";
        }
    }else{
    $response['error'] = true;
    $response['message'] = "Invalid Request";
	}
}

echo json_encode($result);

