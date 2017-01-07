package com.hfad.core;

/**
 * Created by Matija on 24/11/2016.
 */

import java.util.ArrayList;

import hr.foi.air.database.database.entities.Intervention;
import hr.foi.air.database.database.entities.User;

public interface DataLoadedListener {
    void onInterventionLoaded(ArrayList<Intervention> interventions);
    void onUserLoaded(User user);
}
