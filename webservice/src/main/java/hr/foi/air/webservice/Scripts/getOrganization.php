<?php

require_once './baza.class.php';

$DB = new Baza();

if(isset($_POST['oib']) ){
    $fireman = $_POST['oib'];
    $query = "SELECT organization.organizationId, organization.name,"
            . "organization.shortName ,organization.address FROM organization "
            . "join fireman using(organizationId) where fireman.oib = '$fireman'";
    $organization = $DB->selectDB($query);

while( $organizationData = $organization->fetch_assoc()){
    $response['valid'] = true;
    $response['organization']['organizationId'] = $organizationData['organizationId'];
    $response['organization']['name'] = $organizationData['name'];
    $response['organization']['shortName'] = $organizationData['shortName'];
    $response['organization']['address'] = $organizationData['address'];
   
}

echo json_encode($response);
}