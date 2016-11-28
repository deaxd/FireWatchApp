package com.hfad.core;

/**
 * Created by Matija on 24/11/2016.
 */

import java.util.ArrayList;

import hr.foi.air.database.database.entities.Intervention;

public interface DataLoadedListener {
    void onDataLoaded(ArrayList<Intervention> interventions);
}
