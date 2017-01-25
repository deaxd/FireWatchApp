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
    $userOib = $_POST['userOib'];
 
    $query1 = "SELECT organizationId FROM fireman WHERE oib = '$userOib'";
    $orgId = $DB->selectDB($query1);
    
    $org = $orgId->fetch_assoc();
    $orgid = $org['organizationId'];
    
    $query = "INSERT INTO fireman values( '$oib', '$name', '$surname', $orgid, '$username', '$password', $lieutenant)";
   
    $DB->executeDB($query);
  }