<?php
require_once './baza.class.php';
$DB = new Baza();

if(isset($_POST)) {
    
    $name = $_POST['name'];
    $seatNumber = $_POST['seatNumber'];
    $waterVolume = $_POST['waterVolume'];
    $kindOfVehicle = $_POST['kindOfVehicle'];
    $organizationId = $_POST['organizationId'];
    
    $query = "INSERT INTO vehicle values (default, '$name', $seatNumber, $waterVolume, '$kindOfVehicle', $organizationId)";
   
    $DB->executeDB($query);
    
  }