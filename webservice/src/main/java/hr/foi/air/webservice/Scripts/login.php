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
            $response['items']['oib'] = $user['oib'];
            $response['items']['name'] = $user['name'];
            $response['items']['surname'] = $user['surname'];
            $response['items']['organizationId'] = $user['organizationId'];
            $response['items']['username'] = $user['username'];
            $response['items']['password'] = $user['password'];
            $response['items']['lieutenant'] = $user['lieutenant'];

        }
    }
    else {
        $response['valid'] = false;
        $response['text'] = "Nepostojeci korisnik";
    }
    echo json_encode($response);

}

