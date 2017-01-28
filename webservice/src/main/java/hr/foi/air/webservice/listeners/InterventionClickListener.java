package hr.foi.air.webservice.listeners;

import java.util.List;

import hr.foi.air.database.database.entities.Intervention;

/**
 * Created by DELL on 18.1.2017..
 */

public interface InterventionClickListener {

    void onInterventionsFetched(List<Intervention> interventionList);

    void onInterventionClicked(Intervention position);

    void onNumberOfInterventionFetched(String number);

    void onError(String error);

}
