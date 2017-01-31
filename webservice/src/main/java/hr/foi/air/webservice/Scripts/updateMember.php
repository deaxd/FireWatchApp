<?php
require_once './baza.class.php';

$DB = new Baza();

if(isset($_POST)) {
    
    $oib = $_POST['oib'];
    $name = $_POST['name'];
    $surname = $_POST['surname'];
    $username = $_POST['username'];
    $password = $_POST['password'];
    $lieutenant = $_POST['lieutenant'];
    
    
    $query = "UPDATE fireman set oib = '$oib', name = '$name', surname = '$surname', username = '$username', "
            . "password = '$password', lieutenant = $lieutenant WHERE fireman.oib = '$oib'";
    
    $rez = $DB->executeDB($query);
    if($rez->hasFailed()){
        $response['valid'] = false;
    }
    else $response['valid'] = true;
    
    echo json_encode($response);
}