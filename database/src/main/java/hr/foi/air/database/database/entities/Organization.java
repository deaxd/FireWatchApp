package hr.foi.air.database.database.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

import hr.foi.air.database.database.FireWatchDB;

/**
 * Created by Denis on 13.11.2016..
 */
@Table(database = FireWatchDB.class)
public class Organization extends BaseModel {

    @PrimaryKey(autoincrement = false)
    @Column
    int organizationId;
    @Column String name;
    @Column String shortName;
    @Column String address;


    List<Fireman> members;
    List<Fireman> myLiuetenants;
    List<Vehicle> myVehicles;
    List<Equipment> myEquipments;

    public Organization() {
    }

    public Organization(int organizationId, String name, String shortName, String address) {
        this.organizationId = organizationId;
        this.name = name;
        this.shortName = shortName;
        this.address = address;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "members")
    public List<Fireman> getMembers(){
        members = SQLite.select().from(Fireman.class).where(Fireman_Table.organization_organizationId.eq(organizationId)).queryList();
        return members;
    }

    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "myLiuetenants")
    public List<Fireman> getMyLiuetenants(){
        myLiuetenants = SQLite.select().from(Fireman.class).where(Fireman_Table.organization_organizationId.eq(organizationId)).and(Fireman_Table.lieutenant.eq(true)).queryList();
        return myLiuetenants;
    }

    @OneToMany(methods = {OneToMany.Method.ALL},variableName = "myVehicles")
    public List<Vehicle> getMyVehicles(){
        myVehicles = SQLite.select().from(Vehicle.class).where(Vehicle_Table.organization_organizationId.eq(organizationId)).queryList();
        return myVehicles;
    }

    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "myEquipments")
    public List<Equipment> getMyEquipments(){
        myEquipments = SQLite.select().from(Equipment.class).where(Equipment_Table.organization_organizationId.eq(organizationId)).queryList();
        return myEquipments;
    }

}
