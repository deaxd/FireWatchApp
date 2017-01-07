<?php

/*
Skripta za provjeru logina
*/

require_once './baza.class.php';

$DB = new Baza();
$response = array();
if(isset($_POST['username']) && isset($_POST['password'])){
    $username = $_POST['username'];
    $password = $_POST['password'];

    $query = "SELECT * FROM fireman WHERE username = '$username'";
    $usersInDB = $DB->selectDB($query);


    if($usersInDB->num_rows != 0 ){
        $user = $usersInDB->fetch_array();
        if($user['password'] == $password){
            $response['valid'] = true;
            $response['text'] = "Success";
            $response['oib'] = $user['oib'];
            $response['name'] = $user['name'];
            $response['surname'] = $user['surname'];
            $response['organizationId'] = $user['organizationId'];
            $response['username'] = $user['username'];
            $response['password'] = $user['password'];
            $response['lieutenant'] = $user['lieutenant'];

        }
    }
    else {
        $response['valid'] = false;
        $response['text'] = "Nepostojeci korisnik";
    }
    echo json_encode($response);

}

