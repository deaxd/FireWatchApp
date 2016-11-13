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
public class Equipment extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    int equipmentId;
    @Column String name;
    @Column int quantity;
    @Column @ForeignKey(tableClass = Organization.class)
    Organization organization;

    public Equipment() {
    }

    public Equipment(int equipmentId, String name, int quantity, Organization organization) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.quantity = quantity;
        this.organization = organization;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
