<?php

/**
 * Skripta za povezivanje s bazom i dohvačanje/slanje podataka
 */
require_once './config.php';
class Baza{
     private function spojiDB(){

        $mysqli = new mysqli(SERVER, USER, PASSWORD, DATABASE);
        $mysqli->set_charset("utf8");
        return $mysqli;
    }

    /**
     * Metoda za izvršavanje select naredbe
     * @param type upit
     * @return type podaci iz baze
     */

    function selectDB($query){
        $connection = self::spojiDB();
        $rezultat = $connection->query($query);
        $connection->close();
        return $rezultat;

        }
    /**
     * Metoda za update, insert, delete i druge naredbe
     * @param type prosljeđena naredba
     * @return boolean true ako se izvrđi naredba
     */

    function executeDB($query){
        $connection = self::spojiDB();
        if($rezultat = $connection->query($query)){
            $connection->close();
            return true;
            }
        else{
            $connection->close();
            return false;
            }
        }
}
?>