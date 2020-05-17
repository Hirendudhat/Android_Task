<?php
include 'connect.php';

$unm=$_REQUEST["USERNAME"];
$pwd=$_REQUEST["PASSWORD"];

//$sel="select * from registration where  EMAIL='$unm' and PASSWORD='$pwd'";
$sel="select * from multicode where  USERNAME='$unm' and PASSWORD = '$pwd'";
$ex=mysqli_query($con,$sel);
$no=mysqli_num_rows($ex);
//echo $no;


if($no>0)
{
$fet=mysqli_fetch_object($ex);
echo json_encode($fet);
}
else
{
echo "0";
}


?>