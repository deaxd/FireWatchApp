package com.hfad.statistics.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hfad.statistics.Loader;
import com.hfad.statistics.R;
import com.numetriclabz.numandroidcharts.BarChart;
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
    private View view;
    private LayoutInflater inflater;
    private  ViewGroup container;
    HorizontalBarChart barChart;
    MaterialDialog progressDialog;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph, container, false);

        //Loader loader = new Loader();
        //tnumberMembers=loader.getNumMembers();
        WebServiceCaller wsc = new WebServiceCaller();
        User user = SQLite.select().from(User.class).querySingle();
        if (user != null) {
            wsc.getStatistics(user.getUserOib(), this);
            showProgress();
        }

        List<ChartData> value1 = new ArrayList<>();

        barChart = (HorizontalBarChart) view.findViewById(R.id.chart);

        value1.add(new ChartData(Float.valueOf(tnumberMembers),"Broj članova")); //values.add(new ChartData(Y,"Labels"));
        value1.add(new ChartData(Float.valueOf(tnumberInterventions), "Broj intervencija"));
        value1.add(new ChartData(Float.valueOf(tnumberIntThisYear),"Broj intervencija ove godine"));
        value1.add(new ChartData(Float.valueOf(tnumberVehicles), "Broj vozila"));

        barChart.setData(value1);

        barChart.setGesture(true);


        barChart.setDescription("Statistika");




        return view;

    }


    @Override
    public void onStatisticReceived(int numberMembers, int numberInterventions, int numberIntThisYear, double numberIntAvg, int numberVehicles) {


        tnumberMembers=numberMembers;
        tnumberInterventions=numberInterventions;
        tnumberIntThisYear=numberIntThisYear;
        tnumberVehicles=numberVehicles;

        hideProgress();

        List<ChartData> value = new ArrayList<>();
        value.add(new ChartData(Float.valueOf(tnumberMembers),"Broj članova")); //values.add(new ChartData(Y,"Labels"));
        value.add(new ChartData(Float.valueOf(tnumberInterventions), "Broj intervencija"));
        value.add(new ChartData(Float.valueOf(tnumberIntThisYear),"Broj intervencija ove godine"));
        value.add(new ChartData(Float.valueOf(tnumberVehicles), "Broj vozila"));

        barChart.setData(value);

        barChart.setGesture(true);
        
        barChart.setLeft(1);


        barChart.setDescription("Statistika");
    }

    private void showProgress() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new MaterialDialog.Builder(getActivity())
                    .title(R.string.app_name)
                    .content("Please Wait....")
                    .progress(true, 0)
                    .build();
            progressDialog.setCanceledOnTouchOutside(false);
        }
        if (!getActivity().isFinishing()) {
            progressDialog.show();
        }
    }

    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing() && !getActivity().isFinishing()) {
            progressDialog.dismiss();
        }
    }
}
