package com.hfad.statistics.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hfad.statistics.R;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import hr.foi.air.database.database.entities.User;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.listeners.StatisticReceivedListener;
import com.numetriclabz.numandroidcharts.ChartData;
import com.numetriclabz.numandroidcharts.HorizontalBarChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matija on 29/01/2017.
 */

public class GraphFragment extends Fragment implements StatisticReceivedListener {


    private int tnumberMembers;
    private int tnumberInterventions;
    private int tnumberIntThisYear;
    private int tnumberVehicles;
    HorizontalBarChart barChart;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph, container, false);

        WebServiceCaller wsc = new WebServiceCaller();
        User user = SQLite.select().from(User.class).querySingle();
        if (user != null) {
            wsc.getStatistics(user.getUserOib(), this);
            //showProgress();
        }
        
        barChart = (HorizontalBarChart) view.findViewById(R.id.chart);




        filldata(barChart);

        return view;



    }

    public void filldata(HorizontalBarChart horizontalBarChart){



        List<ChartData> value = new ArrayList<>();
        value.add(new ChartData(Float.valueOf(tnumberMembers),"Broj članova")); //values.add(new ChartData(Y,"Labels"));
        value.add(new ChartData(Float.valueOf(tnumberInterventions), "Broj intervencija"));
        value.add(new ChartData(Float.valueOf(tnumberIntThisYear),"Broj intervencija ove godine"));
        value.add(new ChartData(Float.valueOf(tnumberVehicles), "Broj vozila"));

        horizontalBarChart.setData(value);

        horizontalBarChart.setGesture(true);

        horizontalBarChart.setDescription("Statistika");
    }



    @Override
    public void onStatisticReceived(int numberMembers, int numberInterventions, int numberIntThisYear, double numberIntAvg, int numberVehicles) {

        tnumberMembers=numberMembers;
        tnumberInterventions=numberInterventions;
        tnumberIntThisYear=numberIntThisYear;
        tnumberVehicles=numberVehicles;







    }
}
