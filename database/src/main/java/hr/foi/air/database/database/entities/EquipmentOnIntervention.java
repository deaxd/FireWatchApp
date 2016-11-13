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
public class EquipmentOnIntervention extends BaseModel {

    @PrimaryKey
    @Column
    @ForeignKey(tableClass = Equipment.class)
    Equipment equipment;
    @PrimaryKey
    @Column
    @ForeignKey(tableClass = Intervention.class)
    Intervention intervention;

    public EquipmentOnIntervention() {
    }

    public EquipmentOnIntervention(Equipment equipment, Intervention intervention) {
        this.equipment = equipment;
        this.intervention = intervention;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Intervention getIntervention() {
        return intervention;
    }

    public void setIntervention(Intervention intervention) {
        this.intervention = intervention;
    }
}
