
<?php 

 
 
 define('DB_NAME','topspin');
 define('DB_USER','root');
 define('DB_PASSWORD','');
 define('DB_HOST','localhost');
 
 //connecting to database and getting the connection object
 $conn = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);
 
 //Checking if any error occured while connecting
 if (mysqli_connect_errno()) {
 echo "Failed to connect to MySQL: " . mysqli_connect_error();
 die();
 }
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
    if(isset($_POST['eventID']) 
		and isset($_POST['matchType'])
			 ){
 //creating a query
 $stmt = $conn->prepare("SELECT * FROM `matches` WHERE eventID = $eventID AND matchType = $matchType;");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($matchID, $eventID, $matchType, $homePlayer1, $homePlayer2, $awayPlayer1, $awayPlayer2, $homeTeamSets, $awayTeamSets, $result);
 
 $matches = array(); 
 
 //traversing through all the result 
      while($stmt->fetch()){
      $temp = array();
      $temp['matchID'] = $matchID; 
      $temp['eventID'] = $eventID; 
      $temp['matchType'] = $matchType; 
      $temp['homePlayer1'] = $homePlayer1; 
      $temp['homePlayer2'] = $homePlayer2; 
      $temp['awayPlayer2'] = $awayPlayer1; 
      $temp['awayPlayer2'] = $awayPlayer2; 
      $temp['homeTeamSets'] = $homeTeamSets; 
      $temp['awayTeamSets'] = $awayTeamSets; 
      $temp['result'] = $result; 
      array_push($matches, $temp);
     }
 }
 
 //displaying the result in json format 
 echo json_encode($matches);
