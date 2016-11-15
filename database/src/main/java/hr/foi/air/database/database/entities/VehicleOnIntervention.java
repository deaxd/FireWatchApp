package hr.foi.air.database.database.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import hr.foi.air.database.database.FireWatchDB;

/**
 * Created by Denis on 13.11.2016..
 */
@Table(database = FireWatchDB.class)
public class VehicleOnIntervention extends BaseModel {

    @PrimaryKey
    @Column @ForeignKey(tableClass = Vehicle.class)
    Vehicle vehicle;
    @PrimaryKey
    @Column @ForeignKey(tableClass = Intervention.class)
    Intervention intervention;

    public VehicleOnIntervention() {
    }

    public VehicleOnIntervention(Vehicle vehicle, Intervention intervention) {
        this.vehicle = vehicle;
        this.intervention = intervention;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Intervention getIntervention() {
        return intervention;
    }

    public void setIntervention(Intervention intervention) {
        this.intervention = intervention;
    }
}
