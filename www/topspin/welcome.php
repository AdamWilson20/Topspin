<?php
   include('session.php');
   $sql = "SELECT school,firstName, lastName, match1, match2, match3 from player_data";
   $result = mysqli_query($conn,$sql);
?>
<html">

   <head>
      <title>Update Scores </title>
   </head>

   <body>
      <h1>Welcome <?php echo $login_session; ?></h1>

      <html>
      <head>
      <style>
      table {
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 100%;
      }

      td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
      }

      tr:nth-child(even) {
        background-color: #dddddd;
      }
      </style>
      </head>
      <body>

      <h2>ODU Men's Tennis Scores</h2>

      <table>
        <tr>
          <th>Player</th>
          <th>College</th>
          <th>Match 1</th>
          <th>Match 2</th>
          <th>Match 3</th>
        </tr>


      <h3><?php    if ($result->num_rows > 0) {

          // output data of each row
          while($row = $result->fetch_assoc()) {
              echo "<tr>". "<td>". $row["firstName"]. " " . $row["lastName"]."</td>".
              "<td>". $row["school"].  "</td>". "<td>".$row["match1"]. "</td>".  "<td>".
              $row["match2"]."</td>". "<td>".$row["match3"]."</td>". "</tr>";
            }


      } else {
          echo "0 results";
      }
      ?></h3>

      <h2><a href = "logout.php">Sign Out</a></h2>
   </body>

   <html>
   <head>
   <style>
   table {
     font-family: arial, sans-serif;
     border-collapse: collapse;
     width: 100%;
   }

   td, th {
     border: 1px solid #dddddd;
     text-align: left;
     padding: 8px;
   }

   tr:nth-child(even) {
     background-color: #dddddd;
   }
   </style>
   </head>
   <body>


   </body>
   </html>
