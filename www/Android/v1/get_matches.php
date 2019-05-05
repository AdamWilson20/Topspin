<?php

require_once '../includes/DbOperations.php';
$response = array();
//$matchID, eventID, $homeTeamSets, $awayTeamSets, $result
if($_SERVER['REQUEST_METHOD']=='POST'){
    if(isset($_POST['eventID']) 
		and isset($_POST['matchType'])
			 ){
        $db = new DbOperations();

        $result = $db->getMatches($_POST['eventID'],
                                 $_POST['matchType']
								 );
         /*if($result){
			 while($result){

			$result['matchType'] = $result['matchType'];
			$result['homePlayer1'] = $result['homePlayer1'];
			$result['homePlayer2'] = $result['homePlayer2'];
			$result['awayPlayer1'] = $result['awayPlayer1'];
			$result['awayPlayer2'] = $result['awayPlayer2'];
			$result['homeTeamSets'] = $result['homeTeamSets'];
			$result['awayTeamSets'] = $result['awayTeamSets'];
			 $result['result'] = $result['result'];}
			
			
        }else{
            $response['error'] = true;
            $response['message'] = "Some error occurred please try again";
        }*/
		$response ['error'] = false;
    }else{
    $response['error'] = true;
    $response['message'] = "Invalid Request";
	}
}

echo $result;