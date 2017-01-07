package hr.foi.air.webservice.Responses;

import java.lang.reflect.Array;

/**
 * Created by Denis on 3.12.2016..
 */

public class InterventionResponse {

    public boolean valid;
    public Array intervention;

    public InterventionResponse() {
    }

    public InterventionResponse(boolean valid, Array intervention) {
        this.valid = valid;
        this.intervention = intervention;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Array getIntervention() {
        return intervention;
    }

    public void setIntervention(Array intervention) {
        this.intervention = intervention;
    }
}
