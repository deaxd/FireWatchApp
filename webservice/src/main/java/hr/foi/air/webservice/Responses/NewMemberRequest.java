package hr.foi.air.webservice.Responses;


import com.google.gson.annotations.SerializedName;

public class NewMemberRequest {

    private String oib;

    private String name;

    private String surname;

    private String username;

    private String password;

    @SerializedName("lieutenant")
    private boolean isLieutenant;

    private int organizationId;

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
        return isLieutenant;
    }

    public void setLieutenant(boolean lieutenant) {
        isLieutenant = lieutenant;
    }
}
