<?php

 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
 require_once'connect.php';

 $username = $_POST['USERNAME'];
 $email = $_POST['EMAIL'];
 $phone = $_POST['PHONE'];
 $password = $_POST['PASSWORD'];
 
if($username=="" && $email=="" && $phone=="" && $password=="")
{
       echo '0';
}
else
{ 
  $sql = "INSERT INTO multicode (USERNAME,EMAIL,PHONE,PASSWORD) VALUES ('$username','$email','$phone','$password')";
//echo $sql;
    $ex=mysqli_query($con,$sql);
    
}
}
 
?>