<?php

	class DbOperations{
		
		private $con;
		
		function __construct(){
			
			require_once dirname(__FILE__).'/DbConnect.php';
			
			$db = new DbConnect();
			
			$this->con = $db->connect();
		}
		
		/*CRUD -> C -> Create*/
		
		public function createUser($username, $pass, $email){
			if($this->isUserExist($username,$email)){
			    return 0;
            }else{
                $password = md5($pass);
                $stmt = $this->con->prepare("INSERT INTO `users` (`id`, `username`, `password`, `email`) VALUES (NULL, ?, ?, ?);");
                $stmt->bind_param("sss", $username, $password, $email);

                if ($stmt->execute()) {
                    return 1;
                } else {
                    return 2;
                }
            }
		}

		public function userLogin($username, $pass){
		    $password = md5($pass);
		    $stmt = $this->con->prepare("SELECT `id` FROM `users` WHERE `username` = ? AND `password` = ?");
		    $stmt->bind_param("ss", $username, $password);
		    $stmt->execute();
		    $stmt->store_result();
		    return $stmt->num_rows > 0;
        }

        public function getUserByUsername($username){
            $stmt = $this->con->prepare("SELECT * FROM `users` WHERE `username` = ?");
            $stmt->bind_param("s", $username);
            $stmt->execute();
            return $stmt->get_result()->fetch_assoc();
		}

		private function isUserExist($username, $email){
            $stmt = $this->con->prepare("SELECT `id` FROM `users` WHERE `username` = ? OR `email` = ?");
            $stmt->bind_param("ss", $username, $email);
            $stmt->execute();
            $stmt->store_result();
            return $stmt->num_rows > 0;
        }
		
		public function createEvent($date, $time, $location, $opposingTeam, $homeOrAway){
			$stmt = $this->con->prepare("INSERT INTO `events` (`eventID`, `homeTeam`, `awayTeam`, `#SinglesMatches`, `#DoublesMatches`, `date`, `startTime`, `active?`, `location`, `opposingTeam`, `home/away`) VALUES (NULL, NULL, NULL, NULL, NULL, ?, ?, NULL, ?, ?, ?)");
			$stmt->bind_param("sssss", $date, $time, $location, $opposingTeam, $homeOrAway);
			
			if ($stmt->execute()) {
                    return 1;
                } else {
                    return 2;
                }
		}
		/**
		*This function will update a set with new live results author = jwatso
		**/
		public function updateSet($setID, $matchID, $homeScore, $awayScore, $result){
			$stmt = $this->con->prepare("Update match_Sets SET homeScore = $homeScore, awayScore = $awayScore, result = '$result' WHERE matchID = $matchID AND setID = $setID");
			$stmt->bind_param("sssss",$setID, $matchID, $homeScore, $awayScore, $result );
			
			if($stmt->execute()){
				return 1;
				} else{
					return 2;
				}
		}
		/**
		*This Function will update a match with new live results author = jwatso
		**/
		
		public function updateMatch($matchID, $eventID, $homeTeamSets, $awayTeamSets, $result){
			$stmt = $this->con->prepare("Update matches SET homeTeamSets = $homeTeamSets, awayTeamSets = $awayTeamSets, result = '$result' WHERE matchID = $matchID AND eventID = $eventID");
			$stmt->bind_param("sssss", $matchID, $eventID, $homeTeamSets, $awayTeamSets, $result);
			
			if($stmt->execute()){
				return 1;
				} else{
					return 2;
				}
		}
		
		/**
		*This Function retrieves a match to be displayed author jwatso
		**/
		public function getMatch($matchID, $eventID){
			$stmt = $this->con->prepare("SELECT * FROM `matches` WHERE matchID = ? AND eventID = ?");
			$stmt->bind_param("ii", $matchID, $eventID);
			$stmt->execute();
			
			
			return $stmt->get_result()->fetch_assoc();

		}
		
		/**
		*This function returns all matches associated with an event's id and matchType
		**/
		public function getMatches($eventID, $matchType){
			$stmt = $this->con->prepare("SELECT * FROM `matches` WHERE eventID = ? AND matchType = ?");
			$stmt->bind_param("is", $eventID, $matchType);
			$stmt->bind_result( $matchID, $eventID, $matchType, $homePlayer1, $homePlayer2, $awayPlayer1, $awayPlayer2, $homeTeamSets, $awayTeamSets, $result);
			
			$stmt->execute();
			$matches = array();
			
			while($stmt->fetch()){
            $temp = array();
            $temp['matchID'] = $matchID; 
            $temp['eventID'] = $eventID; 
            $temp['matchType'] = $matchType; 
            $temp['homePlayer1'] = $homePlayer1; 
            $temp['homePlayer2'] = $homePlayer2; 
            $temp['awayPlayer1'] = $awayPlayer1; 
            $temp['awayPlayer2'] = $awayPlayer2; 
            $temp['homeTeamSets'] = $homeTeamSets; 
            $temp['awayTeamSets'] = $awayTeamSets; 
            $temp['result'] = $result; 
            array_push($matches, $temp);
            }
			
            echo json_encode($matches);
			

		}
		
		/**
		*This function returns all sets associated with an event's id 
		**/
		public function getSets($matchID){
			$stmt = $this->con->prepare("SELECT * FROM match_sets WHERE matchID = ?");
			$stmt->bind_param("i", $matchID);
			$stmt->bind_result($setID, $matchID, $homeScore, $awayScore, $result);
			
			$stmt->execute();
			$matches = array();
			
			while($stmt->fetch()){
            $temp = array();
            $temp['setID'] = $setID; 
            $temp['matchID'] = $matchID; 
            $temp['homeScore'] = $homeScore; 
            $temp['awayScore'] = $awayScore; 
            $temp['result'] = $result; 
            array_push($matches, $temp);
            }
			
            echo json_encode($matches);
			

		}
		
		public function getEvents(){
            $stmt = $this->con->prepare("SELECT * FROM `events`");
            $stmt->execute();
            return $stmt->get_result();
		}
		
		public function getRoster(){
            $stmt = $this->con->prepare("SELECT * FROM `roster`");
            $stmt->execute();
            return $stmt->get_result();
		}
	}