package com.hfad.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hr.foi.air.database.database.entities.Intervention;

public class IntervetionDetailFragment extends Fragment {


    public IntervetionDetailFragment(){

    }

   @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       return inflater.inflate(R.layout.fragment_intervetion_detail, container, false);
   }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        TextView txtType = (TextView) getView().findViewById(R.id.type_of_int);
        TextView txtAddress = (TextView) getView().findViewById(R.id.address_int);
        TextView txtInitialTime = (TextView) getView().findViewById(R.id.initial_time_int);
        TextView txtDuration = (TextView) getView().findViewById(R.id.duration_int);
        TextView txtDescription = (TextView) getView().findViewById(R.id.description_int);
        TextView txtLongitude = (TextView) getView().findViewById(R.id.longitude_int);
        TextView txtLatitude = (TextView) getView().findViewById(R.id.latitude_int);

        // TODO check pulling a lists of firemans and vehicles on intervention
        TextView txtFireman = (TextView) getView().findViewById(R.id.fireman_int);
        TextView txtVehicles = (TextView) getView().findViewById(R.id.vehicles_int);

        Bundle data = getArguments();
        int interventionId = data.getInt("id", -1);

        if(interventionId != -1){
            Intervention intervention = Intervention.getInterventionById(interventionId);

            txtType.setText(intervention.getKindOfIntervention());
            txtAddress.setText(intervention.getAddress());
            txtInitialTime.setText(intervention.getInitialTIme());
            txtDuration.setText(intervention.getDuration());
            txtDescription.setText(intervention.getDescription());
            //txtLongitude.setText(intervention.getLongitude());
            //txtLatitude.setText(intervention.getLatitude());

            //txtFireman.setText(intervention.getFiremansOnIntervention());
            //txtVehicles.setText(intervention.getVehiclesOnIntervention());
        }

    }
}