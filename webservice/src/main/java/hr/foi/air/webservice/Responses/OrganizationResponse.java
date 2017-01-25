package hr.foi.air.webservice.Responses;

import com.google.gson.annotations.SerializedName;

import hr.foi.air.database.database.entities.Organization;

/**
 * Created by Denis on 19.1.2017..
 */

public class OrganizationResponse {
    @SerializedName("valid")
    private boolean isValid;

    @SerializedName("organization")
    private Organization organization;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
