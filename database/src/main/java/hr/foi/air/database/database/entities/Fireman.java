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
public class Fireman extends BaseModel{

    @PrimaryKey(autoincrement = false)
    @Column String oib;
    @Column String name;
    @Column String surname;
    @Column String username;
    @Column String password;
    @Column boolean lieutenant;

    @Column @ForeignKey(tableClass = Organization.class)
    Organization organization;

    public Fireman() {
    }

    public Fireman(String oib, String name, String surname, String username, String password, boolean lieutenant, Organization organization) {
        this.oib = oib;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.lieutenant = lieutenant;
        this.organization = organization;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLieutenant() {
        return lieutenant;
    }

    public void setLieutenant(boolean lieutenant) {
        this.lieutenant = lieutenant;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
