package com.hfad.statistics.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hfad.statistics.R;
import com.hfad.statistics.StasticsInterface;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import hr.foi.air.database.database.entities.User;
import hr.foi.air.webservice.Responses.StatisticsResponse;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.listeners.StatisticReceivedListener;

/**
 * Created by Matija on 29/01/2017.
 */

/**
 * Class used for displaying data in table form
 */

public class TableFragment extends Fragment implements StasticsInterface {

    private TextView tnumberMembers;
    private TextView tnumberInterventions;
    private TextView tnumberIntThisYear;
    private TextView tnumberIntAvg;
    private TextView tnumberVehicles;
    private  MaterialDialog progressDialog;
    private int numberMembers;
    private int numberInterventions;
    private int numberIntThisYear;
    private double numberIntAvg;
    private int numberVehicles;

    public void onAttach(Context context) {
        super.onAttach(context);
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table, container, false);

        tnumberMembers = (TextView) view.findViewById(R.id.stv_number_members);
        tnumberInterventions = (TextView) view.findViewById(R.id.stv_number_interventions);
        tnumberIntThisYear = (TextView) view.findViewById(R.id.stv_number_intThisYear);
        tnumberIntAvg = (TextView) view.findViewById(R.id.stv_int_avg);
        tnumberVehicles = (TextView) view.findViewById(R.id.stv_number_vehicles);


        tnumberMembers.setText(String.valueOf(numberMembers));
        tnumberInterventions.setText(String.valueOf(numberInterventions));
        tnumberIntThisYear.setText(String.valueOf(numberIntThisYear));
        tnumberIntAvg.setText(String.valueOf(numberIntAvg));
        tnumberVehicles.setText(String.valueOf(numberVehicles));

        return view;



    }
@Override
public Fragment Statisticshow(int numberMembers, int numberInterventions, int numberIntThisYear, double numberIntAvg, int numberVehicles)
    {
        this.numberMembers = numberMembers;
        this.numberInterventions = numberInterventions;
        this.numberIntThisYear = numberIntThisYear;
        this.numberIntAvg = numberIntAvg;
        this.numberVehicles = numberVehicles;
        return this;
}


    private void showProgress() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new MaterialDialog.Builder(getActivity())
                    .title(R.string.app_name)
                    .content("Molimo pričekajte....")
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
