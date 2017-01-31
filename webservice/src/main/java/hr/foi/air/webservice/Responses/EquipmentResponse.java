package hr.foi.air.webservice.Responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import hr.foi.air.database.database.entities.Equipment;

/**
 * Created by Denis on 28.1.2017..
 */

public class EquipmentResponse {

    @SerializedName("valid")
    private boolean isValid;

    @SerializedName("equipment")
    private List<Equipment> equipmentList;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }
}
