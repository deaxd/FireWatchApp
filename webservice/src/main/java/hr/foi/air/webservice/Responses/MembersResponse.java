package hr.foi.air.webservice.Responses;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import hr.foi.air.database.database.entities.Fireman;

public class MembersResponse {

    @SerializedName("valid")
    private boolean isValid;

    @SerializedName("members")
    private List<Fireman> firemenList;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public List<Fireman> getFiremenList() {
        return firemenList;
    }

    public void setFiremenList(List<Fireman> firemenList) {
        this.firemenList = firemenList;
    }
}
