package com.hfad.report;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

import hr.foi.air.webservice.listeners.InterventionClickListener;

import hr.foi.air.database.database.entities.Intervention;

public class IntervetionDetailFragment extends Fragment {

    public static final String EXTRA_INTERVENTION = "EXTRA_INTERVENTION";
    private TextView txtType;
    private TextView txtAddress;

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
        txtType = (TextView) v.findViewById(R.id.type_of_int);
        txtAddress = (TextView) v.findViewById(R.id.address_int);

        txtDuration = (TextView) v.findViewById(R.id.duration_int);
        txtDescription = (TextView) v.findViewById(R.id.description_int);


    }


         private void fillData(){

             txtType.setText(intervention.getKindOfIntervention());
             txtAddress.setText(intervention.getAddress());

             txtDuration.setText(intervention.getDuration().toString());
             txtDescription.setText(intervention.getDescription().toString());


        }


    }
