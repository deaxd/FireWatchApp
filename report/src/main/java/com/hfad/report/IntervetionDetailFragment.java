package com.hfad.report;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hfad.report.listener.InterventionClickListener;

import hr.foi.air.database.database.entities.Intervention;

public class IntervetionDetailFragment extends Fragment {

    public static final String EXTRA_INTERVENTION = "EXTRA_INTERVENTION";
    private TextView txtType;
    private TextView txtAddress;
    private TextView txtInitialTime;
    private TextView txtDuration;
    private TextView txtDescription;

    private TextView txtLongitude;
    private TextView txtLatitude;
    private TextView txtFireman;
    private TextView txtVehicles;

    private InterventionClickListener interventionClickListener;
    private Intervention intervention;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interventionClickListener = (InterventionClickListener) context;

    }

    public static IntervetionDetailFragment newInstance(Intervention intervention){

        IntervetionDetailFragment intervetionDetailFragment = new IntervetionDetailFragment();

        Bundle args = new Bundle();
        args.putSerializable(EXTRA_INTERVENTION, intervention);
        intervetionDetailFragment.setArguments(args);

        return intervetionDetailFragment;
    }

    public IntervetionDetailFragment() {
    }

   @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       View v = inflater.inflate(R.layout.fragment_intervetion_detail, container, false);

        intervention = (Intervention) getArguments().getSerializable(EXTRA_INTERVENTION);

       bindViews(v);

       return v;

   }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillData();
    }

    private void bindViews(View v) {
        txtType = (TextView) getView().findViewById(R.id.type_of_int);
        txtAddress = (TextView) getView().findViewById(R.id.address_int);
        txtInitialTime = (TextView) getView().findViewById(R.id.initial_time_int);
        txtDuration = (TextView) getView().findViewById(R.id.duration_int);
        txtDescription = (TextView) getView().findViewById(R.id.description_int);
        txtLongitude = (TextView) getView().findViewById(R.id.longitude_int);
        txtLatitude = (TextView) getView().findViewById(R.id.latitude_int);

        // TODO check pulling a lists of firemans and vehicles on intervention
        txtFireman = (TextView) getView().findViewById(R.id.fireman_int);
        txtVehicles = (TextView) getView().findViewById(R.id.vehicles_int);

    }

        //ovo je vrlo vjerovatno nepotreban dio, nedostaje fill data, nedostaje on attach i dodavanje listenera, bundle sa Intervention details fragment
       /* Bundle data = getArguments();
        int interventionId = data.getInt("id", -1);

        if(interventionId != -1){
            Intervention intervention = Intervention.getInterventionById(interventionId);
*/
         private void fillData(){
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
