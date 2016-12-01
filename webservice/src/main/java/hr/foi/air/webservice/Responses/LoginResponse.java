package hr.foi.air.webservice.Responses;

/**
 * Created by Denis on 1.12.2016..
 */

public class LoginResponse {
    public Boolean valid;
    public String text;
    public String oib;
    public String name;
    public String surname;
    public int organizationId;
    public String username;
    public String password;
    public Boolean lieutenant;

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

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
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

    public Boolean getLieutenant() {
        return lieutenant;
    }

    public void setLieutenant(Boolean lieutenant) {
        this.lieutenant = lieutenant;
    }
}
