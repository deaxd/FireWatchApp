package hr.foi.air.webservice.Responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Denis on 25.1.2017..
 */

public class AddResponse {
    @SerializedName("valid")
    private boolean valid;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
