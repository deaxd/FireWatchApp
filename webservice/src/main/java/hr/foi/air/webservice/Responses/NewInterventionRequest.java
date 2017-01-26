package hr.foi.air.webservice.Responses;

/**
 * Created by Matija on 26/01/2017.
 */

public class NewInterventionRequest {

    private String kindOfInt;
    private String adress;
    private String initTime;
    private String duration;
    private String description;
    private double latitude;
    private double longitude;
    private String members;



    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }



    public String getKindOfInt() {
        return kindOfInt;
    }

    public void setKindOfInt(String kindOfInt) {
        this.kindOfInt = kindOfInt;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getInitTime() {
        return initTime;
    }

    public void setInitTime(String initTime) {
        this.initTime = initTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


}
