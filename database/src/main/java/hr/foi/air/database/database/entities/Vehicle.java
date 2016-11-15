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
public class Vehicle extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    int vehicleId;
    @Column String name;
    @Column int seatNumber;
    @Column int waterVolume;
    @Column String kindOfVehicle;
    @Column @ForeignKey(tableClass = Organization.class)
    Organization organization;

    public Vehicle() {
    }

    public Vehicle(int vehicleId, String name, int seatNumber, int waterVolume, String kindOfVehicle, Organization organization) {
        this.vehicleId = vehicleId;
        this.name = name;
        this.seatNumber = seatNumber;
        this.waterVolume = waterVolume;
        this.kindOfVehicle = kindOfVehicle;
        this.organization = organization;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getWaterVolume() {
        return waterVolume;
    }

    public void setWaterVolume(int waterVolume) {
        this.waterVolume = waterVolume;
    }

    public String getKindOfVehicle() {
        return kindOfVehicle;
    }

    public void setKindOfVehicle(String kindOfVehicle) {
        this.kindOfVehicle = kindOfVehicle;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
