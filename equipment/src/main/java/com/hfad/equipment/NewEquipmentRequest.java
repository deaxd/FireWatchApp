package com.hfad.equipment;

/**
 * Created by Matija on 28/01/2017.
 */

public class NewEquipmentRequest {

    private String Name;
    private int Quantity;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
