package hr.foi.air.database.database.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

import hr.foi.air.database.database.FireWatchDB;

/**
 * Created by Denis on 13.11.2016..
 */
@Table(database = FireWatchDB.class)
public class Intervention extends BaseModel implements Serializable {

    @PrimaryKey(autoincrement = true)
    @Column
    int interventionId;
    @Column @Unique String alertNumber;
    @Column String kindOfIntervention;
    @Column String address;
    @Column String initialTIme;
    @Column String duration ;
    @Column String description;
    @Column double longitude;
    @Column double latitude;
    @Column @ForeignKey(tableClass = Fireman.class)
    Fireman fireman;
    @Column String members;

    public Intervention() {
    }

    public Intervention(int interventionId, String alertNumber, String kindOfIntervention, String address, String initialTIme, String duration, String description, double longitude, double latitude, Fireman fireman, String members) {
        this.interventionId = interventionId;
        this.alertNumber = alertNumber;
        this.kindOfIntervention = kindOfIntervention;
        this.address = address;
        this.initialTIme = initialTIme;
        this.duration = duration;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.fireman = fireman;
        this.members = members;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public int getInterventionId() {
        return interventionId;
    }

    public void setInterventionId(int interventionId) {
        this.interventionId = interventionId;
    }

   public String getKindOfIntervention() {
        return kindOfIntervention;
    }

    public void setKindOfIntervention(String kindOfIntervention) {
        this.kindOfIntervention = kindOfIntervention;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInitialTIme() {
        return initialTIme;
    }

    public void setInitialTIme(String initialTIme) {
        this.initialTIme = initialTIme;
    }

   public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Fireman getFireman() {
        return fireman;
    }

    public void setFireman(Fireman fireman) {
        this.fireman = fireman;
    }

    public String getAlertNumber() {
        return alertNumber;
    }

    public void setAlertNumber(String alertNumber) {
        this.alertNumber = alertNumber;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}
