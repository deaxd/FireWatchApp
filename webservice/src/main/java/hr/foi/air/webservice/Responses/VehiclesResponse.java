package hr.foi.air.webservice.Responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import hr.foi.air.database.database.entities.Vehicle;

/**
 * Created by Denis on 28.1.2017..
 */

public class VehiclesResponse {
    @SerializedName("valid")
    private boolean isValid;

    @SerializedName("vehicles")
    private List<Vehicle> vehicleList;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
}
