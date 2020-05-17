<?php

include('connect.php');

$sql="select*from multicode";

$r=mysqli_query($con,$sql);
$response=array();

while($row=mysqli_fetch_array($r))
{
    
    $value["ID"]=$row["ID"];
    $value["NAME"]=$row["NAME"];
    $value["SURNAME"]=$row["SURNAME"];
    $value["EMAIL"]=$row["EMAIL"];
    $value["PASSWORD"]=$row["PASSWORD"];
  
    array_push($response,$value);
    
}

echo json_encode($response);
mysqli_close($con);

?>