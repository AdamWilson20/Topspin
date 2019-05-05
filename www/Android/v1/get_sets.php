<?php

require_once '../includes/DbOperations.php';
$response = array();
//$matchID, eventID, $homeTeamSets, $awayTeamSets, $result
if($_SERVER['REQUEST_METHOD']=='POST'){
    if(isset($_POST['matchID']))
			 ){
        $db = new DbOperations();

        if($result = $db->getSets($_POST['matchID']);){
			$response ['error'] = false;
		}
         
		
    }else{
    $response['error'] = true;
    $response['message'] = "Invalid Request";
	}
}

echo $result;