<?php
require_once './baza.class.php';

$DB = new Baza();

if(isset($_POST)) {
    
    $oib = $_POST['oib'];
    $alertNumber = $_POST['alertNumber'];
    $kindOfIntervention = $_POST['kindOfIntervention'];
    $address = $_POST['address'];
    $initialTime = $_POST['initialTime'];
    $duration = $_POST['duration'];
    $latitude = $_POST['latitude'];
    $description = $_POST['description'];
    $longitude = $_POST['longitude'];
    $members = $_POST['members'];
    
    $query = "INSERT INTO intervention values(default,  $alertNumber, '$kindOfIntervention','$address', '$initialTime', $duration,"
            . " '$description', '$oib', $longitude, $latitude, '$members')";
   
    $DB->executeDB($query);
    
    $response['valid'] = $query;
    echo json_encode($response);
  }