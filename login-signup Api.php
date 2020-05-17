<?php 

	include('dbConnect.php');
	
	//an array to display response
	$response = array();
	
	//if it is an api call 
	//that means a get parameter named api call is set in the URL 
	//and with this parameter we are concluding that it is an api call

	if(isset($_GET['apicall'])){
		
		switch($_GET['apicall']){
			
			case 'signup':
				//checking the parameters required are available or not 
				
					
					//getting the values 
					
					$username = $_POST['username']; 
					$email = $_POST['email']; 
					$password = md5($_POST['password']);
					$gender = $_POST['gender']; 
					
					//checking if the user is already exist with this username or email
					//as the email and username should be unique for every user 
				
				
					$stmt = "SELECT id FROM users WHERE username = '$username' OR email = '$email'";
					$ex= mysqli_query($con,$stmt);
					
					//if the user already exist in the database 
					if($ex->num_rows > 0)
					{
						$response['error'] = true;
						$response['message'] = 'User already registered';
						$ex->close();
					}
					else
					{
						
						//if user is new creating an insert query 
						$stmt = "INSERT INTO users (username, email, password, gender) VALUES ('$username', '$email', '$password', '$gender')";
						
						
						//if the user is successfully added to the database 
						if(mysqli_query($con,$stmt)){
							
							//fetching the user back 
							$sql = "SELECT id, username, email, gender FROM users WHERE username = '$username'"; 
							$ex=mysqli_query($con,$sql);
							$row =  mysqli_fetch_array($ex);
							
							$user = array(
								'id'=>$row['id'], 
								'username'=>$row['username'], 
								'email'=>$row['email'],
								'gender'=>$row['gender']
							);
							
							$ex->close();
							
							//adding the user data in response 
							$response['error'] = false; 
							$response['message'] = 'User registered successfully'; 
							$response['user'] = $user; 
						}
					}
					
				
				
			break; 
			
			case 'login':
				//for login we need the username and password 
				
					//getting values 
					$username = $_POST['username'];
					$password = md5($_POST['password']); 
					
					//creating the query 
					$stmt = "SELECT id, username, email, gender FROM users WHERE username = '$username' AND password = '$password'";
					$ex=mysqli_query($con,$stmt);
					
					//if the user exist with given credentials 
					if($ex->num_rows > 0){
						
						$result = mysqli_fetch_array($ex);;
						
						$user = array(
							'id'=>$result['id'], 
							'username'=>$result['username'], 
							'email'=>$result['email'],
							'gender'=>$result['gender']
						);
						
						$response['error'] = false; 
						$response['message'] = 'Login successfull'; 
						$response['user'] = $user; 
					}else{
						//if the user not found 
						$response['error'] = false; 
						$response['message'] = 'Invalid username or password';
					}
				
			break; 
			
			default: 
				$response['error'] = true; 
				$response['message'] = 'Invalid Operation Called';
		}
		
	}else{
		//if it is not api call 
		//pushing appropriate values to response array 
		$response['error'] = true; 
		$response['message'] = 'Invalid API Call';
	}
	
	//displaying the response in json structure 
	echo json_encode($response);
	

	