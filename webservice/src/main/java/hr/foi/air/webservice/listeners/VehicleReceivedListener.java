package hr.foi.air.webservice.listeners;

import java.util.List;

import hr.foi.air.database.database.entities.Vehicle;

/**
 * Created by Matija on 28/01/2017.
 */

public interface VehicleReceivedListener {
    void onVehiclesFetched(List<Vehicle> vehicleList);
}
