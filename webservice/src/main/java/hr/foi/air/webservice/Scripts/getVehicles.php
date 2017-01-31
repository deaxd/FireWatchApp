<?php

/*
Skripta za dohvat vozila
*/

require_once './baza.class.php';

$DB = new Baza();
 
if(isset($_POST['organizationId']) ){
    $organizationId = $_POST['organizationId'];
    $query = "select * from vehicle where organizationId = '$organizationId'";
    
    $vehicleList = $DB->selectDB($query);
            
$send = array();


    while($vehicle = $vehicleList->fetch_assoc()){
        $response['name'] = $vehicle['name'];
        $response['seatNumber'] = $vehicle['seatNumber'];
        $response['waterVolume'] = $vehicle['waterVolume'];
        $response['kindOfVehicle'] = $vehicle['kindOfVehicle'];
      
        array_push($send, $response);
    }

    $res['valid'] = true;
    $res['vehicles'] = $send;
    echo json_encode($res);
    
}

