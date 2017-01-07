package hr.foi.air.webservice.Responses;

import hr.foi.air.database.database.entities.User;

/**
 * Created by Denis on 1.12.2016..
 */

public class LoginResponse {
    public Boolean valid;
    public String text;
    public User user;

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
