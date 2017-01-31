<?php

/*
Skripta za slanje i dodavanje intervenicja
*/

require_once './baza.class.php';

$DB = new Baza();

if(isset($_POST['oib']) ){
    $fireman = $_POST['oib'];
    
    $query = "select organizationId from fireman where oib = '$fireman'";
    
    $organizationIdInDB = $DB->selectDB($query);
    $organizationId = $organizationIdInDB->fetch_array();
    $org= $organizationId['organizationId'];
    $query2 = "SELECT intervention.interventionId, intervention.alertNumber, 
        intervention.kindOfIntervention, intervention.address, intervention.initialTime, 
        intervention.duration, intervention.description, intervention.firemanOib, intervention.longitude, 
        intervention.latitude, intervention.members FROM  intervention join fireman on intervention.firemanOib = 
    fireman.oib and fireman.organizationId = '$org'";
    $interventions = $DB->selectDB($query2);
            
    $query2 = "SELECT count( * ) FROM intervention WHERE firemanOib = '$fireman'";
    $brojInt = $DB->selectDB($query2);
    $broj = $brojInt->fetch_assoc();
    $send = array();


    while($intervention = $interventions->fetch_assoc()){
        $response['interventionId'] = $intervention['interventionId'];
        $response['alertNumber'] = $intervention['alertNumber'];
        $response['kindOfIntervention'] = $intervention['kindOfIntervention'];
        $response['address'] = $intervention['address'];
        $response['initialTime'] = $intervention['initialTime'];
        $response['duration'] = $intervention['duration'];
        $response['description'] = $intervention['description'];
        $response['firemanOib'] = $intervention['firemanOib'];
        $response['longitude'] = $intervention['longitude'];
        $response['latitude'] = $intervention['latitude'];
        $response['members'] = $intervention['members'];
        

        array_push($send, $response);
    }

    $res['valid'] = true;
    $res['numberOfInterventions'] = $broj;
    $res['interventions'] = $send;
    
    echo json_encode($res);
}
    
  

