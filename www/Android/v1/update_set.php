<?php

require_once '../includes/DbOperations.php';
$response = array();
//$matchID, $setID, $homeScore, $awayScore, $result
if($_SERVER['REQUEST_METHOD']=='POST'){
    if(isset($_POST['setID']) 
		and isset($_POST['matchID'])
			and isset($_POST['homeScore'])
				and isset($_POST['awayScore'])
					and isset($_POST['result'])){
        $db = new DbOperations();

        $result = $db->updateSet( $_POST['setID'],
                                  $_POST['matchID'],
                                  $_POST['homeScore'],
								  $_POST['awayScore'],
								  $_POST['result']
                                  );
        if($result == 1){
            $response['error'] = false;
            $response['message'] = "Set updated successfully";
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