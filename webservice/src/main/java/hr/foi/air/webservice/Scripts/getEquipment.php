<?php


/*
Skripta za dohvat opreme
*/

require_once './baza.class.php';

$DB = new Baza();
 
if(isset($_POST['organizationId']) ){
    $organizationId = $_POST['organizationId'];
    $query = "select * from equipment where organizationId = '$organizationId'";
    
    $equipmentList = $DB->selectDB($query);
            
$send = array();


    while($equipment = $equipmentList->fetch_assoc()){
        $response['name'] = $equipment['name'];
        $response['quantity'] = $equipment['quantity'];
        array_push($send, $response);
    }

    $res['valid'] = true;
    $res['equipment'] = $send;
    echo json_encode($res);
    
}

