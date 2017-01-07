package com.hfad.core;

import java.util.ArrayList;

import hr.foi.air.database.database.entities.Intervention;
import hr.foi.air.database.database.entities.User;

/**
 * Created by Matija on 24/11/2016.
 */

public abstract class DataLoader {

    public ArrayList<Intervention> interventions;
    public User user;

    protected DataLoadedListener mDataLoadedListener;

    public void loadData(DataLoadedListener dataLoadedListener){
        this.mDataLoadedListener = dataLoadedListener;
    }

    public boolean dataLoaded(){
        if(interventions==null || user == null){
            return false;
        }
        else{
            return true;
        }
    }
}
