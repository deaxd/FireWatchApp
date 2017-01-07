package hr.foi.air.webservice;

/**
 * Created by Denis on 3.12.2016..
 */

public interface WebServiceHandler {
    void onDataArrived(Object result, boolean ok);
}
