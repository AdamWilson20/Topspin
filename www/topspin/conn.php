<?php
$db_name = "players";
$mysql_username = "root";
$mysql_password = "";
$server_name = "localhost";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);
if ($conn){
  echo "Connection to Topspin server was successful!";
}
else {
  echo " Connection to Topspin server was unsuccesful!";
}

 ?>
