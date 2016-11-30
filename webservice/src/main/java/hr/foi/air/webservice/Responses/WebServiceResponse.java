package hr.foi.air.webservice.Responses;

/**
 * Created by Denis on 30.11.2016..
 */

public class WebServiceResponse {
    public Boolean valid;
    public String text;
    public String items;

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

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
}
