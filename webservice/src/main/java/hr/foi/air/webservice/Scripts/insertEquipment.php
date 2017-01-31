<?php
require_once './baza.class.php';
$DB = new Baza();

if(isset($_POST)) {
    
    $name = $_POST['name'];
    $quantity = $_POST['quantity'];
    $organizationId = $_POST['organizationId'];
  
    $query = "INSERT INTO equipment values (default, '$name', $quantity, $organizationId)";
   
    $DB->executeDB($query);
    
  }