<?php

/*
Skripta za provjeru logina
*/

require_once './baza.class.php';
 
$DB = new Baza();

if(isset($_POST['username']) && isset($_POST['password'])){
    $username = $_POST['username'];
    $password = $_POST['password'];

    $query = "SELECT * FROM fireman WHERE username = '$username'";
    $usersInDB = $DB->selectDB($query);

    if($user = $usersInDB->fetch_assoc()){
        if($user['password'] == $password){
            $response['valid'] = true;
            
            $response['user']['userOib'] = $user['oib'];
            $response['user']['userName'] = $user['name'];
            $response['user']['userSurname'] = $user['surname'];
            $response['user']['userOrganization'] = $user['organizationId'];
            $response['user']['userUsername'] = $user['username'];
            $response['user']['userPassword'] = $user['password'];
            $response['user']['userLieutenant'] = $user['lieutenant'];
           
        }
        else $response['valid'] = false;
    }
    else $response['valid'] = false;
      echo json_encode($response);
}
