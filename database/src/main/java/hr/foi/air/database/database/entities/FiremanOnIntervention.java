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
public class FiremanOnIntervention extends BaseModel {

    @PrimaryKey
    @Column
    @ForeignKey(tableClass = Fireman.class)
    Fireman fireman;
    @PrimaryKey
    @Column @ForeignKey(tableClass = Intervention.class)
    Intervention intervention;

    public FiremanOnIntervention() {
    }

    public FiremanOnIntervention(Fireman fireman, Intervention intervention) {
        this.fireman = fireman;
        this.intervention = intervention;
    }

    public Fireman getFireman() {
        return fireman;
    }

    public void setFireman(Fireman fireman) {
        this.fireman = fireman;
    }

    public Intervention getIntervention() {
        return intervention;
    }

    public void setIntervention(Intervention intervention) {
        this.intervention = intervention;
    }
}
