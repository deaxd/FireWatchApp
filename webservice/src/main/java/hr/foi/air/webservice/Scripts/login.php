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

    $send = array();


    while($user = $usersInDB->fetch_assoc()){
        if($user['password'] == $password){
            $response['userOib'] = $user['oib'];
            $response['userName'] = $user['name'];
            $response['userSurname'] = $user['surname'];
            $response['userOrganization'] = $user['organizationId'];
            $response['userUsername'] = $user['username'];
            $response['userPassword'] = $user['password'];
            $response['userLieutenant'] = $user['lieutenant'];
            array_push($send, $response);
        }
    }
    $res['valid'] = true;
    $res['user'] = $send;
      echo json_encode($res);
}
