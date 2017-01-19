<?php

/*
Skripta za članova
*/

require_once './baza.class.php';

$DB = new Baza();

if(isset($_POST['oib']) ){
    $fireman = $_POST['oib'];
    $query = "select organizationId from fireman where oib = '$fireman'";

    $organizationIdInDB = $DB->selectDB($query);
    $organizationId = $organizationIdInDB->fetch_array();
    $org= $organizationId['organizationId'];
    $query2 = "SELECT * FROM fireman WHERE fireman.organizationId = '$org'";
    $members = $DB->selectDB($query2);

$send = array();


    while($member = $members->fetch_assoc()){
        $response['oib'] = $member['oib'];
        $response['name'] = $member['name'];
        $response['surname'] = $member['surname'];
        $response['organizationId'] = $member['organizationId'];
        $response['username'] = $member['username'];
        $response['password'] = $member['password'];
        $response['lieutenant'] = $member['lieutenant'];


        array_push($send, $response);
    }

    $res['valid'] = true;
    $res['members'] = $send;
    echo json_encode($res);

}

