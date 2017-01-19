<?php

require_once './baza.class.php';

$DB = new Baza();

if(isset($_POST['oib']) ){
    $fireman = $_POST['oib'];
    $query = "SELECT organization.organizationId, organization.name,"
            . "organization.shortName ,organization.address FROM organization "
            . "join fireman using(organizationId) where fireman.oib = '$fireman'";
    $organization = $DB->selectDB($query);
$send = array();

while($organizationData = $organization->fetch_assoc()){
    $response['organizationId'] = $organizationData['organizationId'];
    $response['name'] = $organizationData['name'];
    $response['shortName'] = $organizationData['shortName'];
    $response['address'] = $organizationData['address'];

    array_push($send, $response);
}

$res['valid'] = true;
$res['organization'] = $send;
  echo json_encode($res);
  }