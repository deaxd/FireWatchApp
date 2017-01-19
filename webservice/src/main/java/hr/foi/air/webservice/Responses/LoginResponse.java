package hr.foi.air.webservice.Responses;

import com.google.gson.annotations.SerializedName;

import hr.foi.air.database.database.entities.User;

/**
 * Created by Denis on 1.12.2016..
 */

public class LoginResponse {
    @SerializedName("valid")
    private boolean isValid;

    @SerializedName("user")
    private User user;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
