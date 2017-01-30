package com.hfad.statistics;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import hr.foi.air.database.database.entities.User;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.listeners.StatisticReceivedListener;

/**
 * Created by Matija on 30/01/2017.
 */

public class Loader implements StatisticReceivedListener {

    private  int tnubmerMembers;

    public Loader() {

        WebServiceCaller wsc = new WebServiceCaller();
        User user = SQLite.select().from(User.class).querySingle();
        if (user != null) {
            wsc.getStatistics(user.getUserOib(), this);
            //showProgress();
        }

    }

    public int getNumMembers (){
        return tnubmerMembers;
    }

    @Override
    public void onStatisticReceived(int numberMembers, int numberInterventions, int numberIntThisYear, double numberIntAvg, int numberVehicles) {
        tnubmerMembers=numberMembers;
    }
}
