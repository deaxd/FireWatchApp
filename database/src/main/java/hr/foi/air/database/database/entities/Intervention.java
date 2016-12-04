package hr.foi.air.database.database.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.sql.language.Join;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

import hr.foi.air.database.database.FireWatchDB;

/**
 * Created by Denis on 13.11.2016..
 */
@Table(database = FireWatchDB.class)
public class Intervention extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    int interventionId;
    @Column @Unique int alertNumber;
    @Column String kindOfIntervention;
    @Column String address;
    @Column String initialTIme;
    @Column int duration ;
    @Column String description;
    @Column int longitude;
    @Column int latitude;
    @Column @ForeignKey(tableClass = Fireman.class)
    Fireman fireman;

    List<Equipment> equipmentsOnIntervention;
    List<Vehicle> vehiclesOnIntervention;
    List<Fireman> firemansOnIntervention;

    public Intervention() {
    }

    public Intervention(int interventionId, int alertNumber, String kindOfIntervention, String address,
                        String initialTIme, int duration, String description, int longitude, int latitude, Fireman fireman) {
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
    }

    public int getInterventionId() {
        return interventionId;
    }

    public void setInterventionId(int interventionId) {
        this.interventionId = interventionId;
    }

    public int getAlertNumber() {
        return alertNumber;
    }

    public void setAlertNumber(int alertNumber) {
        this.alertNumber = alertNumber;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public Fireman getFireman() {
        return fireman;
    }

    public void setFireman(Fireman fireman) {
        this.fireman = fireman;
    }

    public static List<Intervention> getAll(){
        return SQLite.select().from(Intervention.class).queryList();
    }

    public static Intervention getInterventionById(int id){
        return SQLite.select().from(Intervention.class).where(Intervention_Table.interventionId.eq(id)).querySingle();
    }

    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "equipmentsOnIntervention")
    public List<Equipment> getEquipmentsOnIntervention(){
                equipmentsOnIntervention = SQLite.select().from(Equipment.class).join(EquipmentOnIntervention.class, Join.JoinType.INNER)
                .on(Equipment_Table.equipmentId.withTable().eq(EquipmentOnIntervention_Table.equipment_equipmentId.withTable()))
                .join(Intervention.class, Join.JoinType.INNER).on(EquipmentOnIntervention_Table.intervention_interventionId.withTable()
                .eq(interventionId)).queryList();
    return equipmentsOnIntervention;
    }


    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "vehiclesOnIntervention")
    public List<Vehicle> getVehiclesOnIntervention(){
            vehiclesOnIntervention = SQLite.select().from(Vehicle.class).join(VehicleOnIntervention.class, Join.JoinType.INNER)
            .on(Vehicle_Table.vehicleId.withTable().eq(VehicleOnIntervention_Table.vehicle_vehicleId.withTable()))
            .join(Intervention.class, Join.JoinType.INNER).on(VehicleOnIntervention_Table.intervention_interventionId.withTable()
            .eq(interventionId)).queryList();
    return vehiclesOnIntervention;
    }

    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "firemansOnIntervention")
    public List<Fireman> getFiremansOnIntervention(){
        firemansOnIntervention = SQLite.select().from(Fireman.class).join(FiremanOnIntervention.class, Join.JoinType.INNER)
        .on(Fireman_Table.oib.withTable().eq(FiremanOnIntervention_Table.fireman_oib.withTable()))
        .join(Intervention.class, Join.JoinType.INNER).on(FiremanOnIntervention_Table.intervention_interventionId.withTable()
        .eq(interventionId)).queryList();
    return firemansOnIntervention;
    }
}
