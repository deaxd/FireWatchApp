package hr.foi.air.database.database.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import hr.foi.air.database.database.FireWatchDB;

/**
 * Created by Denis on 3.12.2016..
 */
@Table(database = FireWatchDB.class)
public class User extends BaseModel{
    @PrimaryKey(autoincrement = false)
    @Column String userOib;
    @Column String userName;
    @Column String userSurname;
    @Column String userUsername;
    @Column String userPassword;
    @Column boolean userLieutenant;

    @Column @ForeignKey(tableClass = Organization.class)
    Organization userOrganization;

    public User() {
    }

    public User(String userOib, String userName, String userSurname, String userUsername, String userPassword, boolean userLieutenant, Organization userOrganization) {
        this.userOib = userOib;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userUsername = userUsername;
        this.userPassword = userPassword;
        this.userLieutenant = userLieutenant;
        this.userOrganization = userOrganization;
    }

    public static User getUser(){
        return SQLite.select().from(User.class).querySingle();
    }

    public String getUserOib() {
        return userOib;
    }

    public void setUserOib(String userOib) {
        this.userOib = userOib;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isUserLieutenant() {
        return userLieutenant;
    }

    public void setUserLieutenant(boolean userLieutenant) {
        this.userLieutenant = userLieutenant;
    }

    public Organization getUserOrganization() {
        return userOrganization;
    }

    public void setUserOrganization(Organization userOrganization) {
        this.userOrganization = userOrganization;
    }
}
