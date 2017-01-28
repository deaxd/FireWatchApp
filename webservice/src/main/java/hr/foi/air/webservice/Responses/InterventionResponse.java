package hr.foi.air.webservice.Responses;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import hr.foi.air.database.database.entities.Intervention;

/**
 * Created by Denis on 3.12.2016..
 */

public class InterventionResponse {

    @SerializedName("valid")
    private boolean isValid;

    @SerializedName("interventions")
    private List<Intervention> interventionList;

    @SerializedName("numberOfInterventions")
    private String numberOfInterventions;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public List<Intervention> getInterventionList() {
        return interventionList;
    }

    public void setInterventionList(List<Intervention> interventionList) {
        this.interventionList = interventionList;
    }

    public String getNumberOfInterventions() {
        return numberOfInterventions;
    }

    public void setNumberOfInterventions(String numberOfInterventions) {
        this.numberOfInterventions = numberOfInterventions;
    }
}
