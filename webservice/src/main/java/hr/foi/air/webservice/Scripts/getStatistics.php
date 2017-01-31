<?php


/*
Skripta za dohvat podataka za statistiku
*/

require_once './baza.class.php';

$DB = new Baza();
 
if(isset($_POST['oib']) ){
    $fireman = $_POST['oib'];
    
    $query = "select organizationId from fireman where oib = '$fireman'";
    $organizationIdInDB = $DB->selectDB($query);
    $organizationId = $organizationIdInDB->fetch_array();
    $org= $organizationId['organizationId'];
    
    //number of members
    $queryMembers = "SELECT count( * ) FROM fireman WHERE fireman.organizationId = '$org'";
    $members = $DB->selectDB($queryMembers);
    $membersNo = $members->fetch_assoc();
    
    //number of interventions
    $queryInterventions = "SELECT count( * ) FROM intervention WHERE firemanOib = '$fireman'";
    $interventions = $DB->selectDB($queryInterventions);
    $interventionsNo = $interventions->fetch_assoc();
    
    //number of intervention this year
    $queryInterventionsCount = "select count( * ) from intervention where YEAR(initialTime) = YEAR(CURDATE()) and firemanOib = '$fireman'";
    $interventionsThisYear = $DB->selectDB($queryInterventionsCount);
    $interventionsNoThisYear = $interventionsThisYear->fetch_assoc();
    
    //average duration of interventions
    $queryInterventionsAvg = "select avg( duration ) from intervention where firemanOib = '$fireman'";
    $interventionsAvg = $DB->selectDB($queryInterventionsAvg);
    $interventionsNoAvg = $interventionsAvg->fetch_assoc();
    
    //number of vehicles
    
    $queryVehicles = "select count( * ) from vehicle where organizationId = '$org'";
    $vehicleList = $DB->selectDB($queryVehicles);
    $vehicleNo = $vehicleList->fetch_assoc();
       
    $response['numberMembers'] = $membersNo['count( * )'];
    $response['numberInterventions'] = $interventionsNo['count( * )'];
    $response['numberIntThisYear'] = $interventionsNoThisYear['count( * )'];
    $response['numberIntAvg'] = $interventionsNoAvg['avg( duration )'];
    $response['numberVehicles'] = $vehicleNo['count( * )'];
    
    
    echo json_encode($response);
    
}