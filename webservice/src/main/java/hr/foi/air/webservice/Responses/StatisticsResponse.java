package hr.foi.air.webservice.Responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Denis on 29.1.2017..
 */

public class StatisticsResponse {

    @SerializedName("numberMembers")
    private int numberMembers;

    @SerializedName("numberInterventions")
    private int numberInterventions;

    @SerializedName("numberIntThisYear")
    private int numberIntThisYear;

    @SerializedName("numberIntAvg")
    private double numberIntAvg;

    @SerializedName("numberVehicles")
    private int numberVehicles;

    public int getNumberMembers() {
        return numberMembers;
    }

    public void setNumberMembers(int numberMembers) {
        this.numberMembers = numberMembers;
    }

    public int getNumberInterventions() {
        return numberInterventions;
    }

    public void setNumberInterventions(int numberInterventions) {
        this.numberInterventions = numberInterventions;
    }

    public int getNumberIntThisYear() {
        return numberIntThisYear;
    }

    public void setNumberIntThisYear(int numberIntThisYear) {
        this.numberIntThisYear = numberIntThisYear;
    }

    public double getNumberIntAvg() {
        return numberIntAvg;
    }

    public void setNumberIntAvg(double numberIntAvg) {
        this.numberIntAvg = numberIntAvg;
    }

    public int getNumberVehicles() {
        return numberVehicles;
    }

    public void setNumberVehicles(int numberVehicles) {
        this.numberVehicles = numberVehicles;
    }
}
