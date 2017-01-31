package com.hfad.equipment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hfad.equipment.R;
import com.hfad.equipment.listeners.NewVehicleAdded;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.rengwuxian.materialedittext.MaterialEditText;

import hr.foi.air.database.database.entities.Organization;
import com.hfad.equipment.NewVehicleRequest;
import hr.foi.air.webservice.WebServiceCaller;

/**
 * Created by Matija on 28/01/2017.
 */

public class VehicleInputFragment extends Fragment {

    private MaterialEditText vName;

    private MaterialEditText vSeatNum;
    private MaterialEditText vWaterVolume;
    private MaterialEditText vKindOf;

    private Button btnSave;

    private NewVehicleAdded newVehicleAdded;


    public VehicleInputFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        newVehicleAdded = (NewVehicleAdded) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vehicle_input, container, false);
        bindViews(v);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveClicked();
            }
        });

        return v;
    }

    private void bindViews(View v) {
        vName = (MaterialEditText) v.findViewById(R.id.vet_vehicle_name);
        vSeatNum = (MaterialEditText) v.findViewById(R.id.vet_seatNumber);
        vWaterVolume = (MaterialEditText) v.findViewById(R.id.vet_waterVolume);
        vKindOf = (MaterialEditText) v.findViewById(R.id.vet_kindOfVehicle);
        btnSave = (Button) v.findViewById(R.id.btn_save);
    }

    private void onSaveClicked() {
       if( validateInput()) {
           swapLayouts();

           WebServiceCaller wsc = new WebServiceCaller();

           Organization org = SQLite.select().from(Organization.class).querySingle();

           if (org != null) {
               wsc.insertVehicle(vName.getText().toString(), Integer.valueOf(vSeatNum.getText().toString()), Integer.valueOf(vWaterVolume.getText().toString()), vKindOf.getText().toString(), org.getOrganizationId());
               //showProgress();
           }
       }
    }

    private boolean validateInput() {
        NewVehicleRequest nmr = new NewVehicleRequest();


        if (!TextUtils.isEmpty(vName.getText()) && vName.getText().length()!=0) {
            nmr.setvName(vName.getText().toString());
        } else {
            Toast toast = Toast.makeText(getContext(),"Niste unijeli naziv vozila",Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        if (!TextUtils.isEmpty(vSeatNum.getText()) && vSeatNum.getText().length()!=0) {

            try{
                Integer.parseInt(vSeatNum.getText().toString());
                nmr.setvSeatNum(Integer.valueOf(vSeatNum.getText().toString()));
        }   catch (NumberFormatException e) {
                Toast toast = Toast.makeText(getContext(),"Niste unijeli ispravan broj sjedala",Toast.LENGTH_LONG);
                toast.show();
                return false;
            }
        } else {
            Toast toast = Toast.makeText(getContext(),"Niste unijeli broj sjedala",Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        if (!TextUtils.isEmpty(vWaterVolume.getText())  && vWaterVolume.getText().length()!=0) {


            try{
                Integer.parseInt(vWaterVolume.getText().toString());
                nmr.setvWaterVolume(Integer.valueOf(vWaterVolume.getText().toString()));
            }   catch (NumberFormatException e) {
                Toast toast = Toast.makeText(getContext(),"Niste unijeli ispravan kapacitet",Toast.LENGTH_LONG);
                toast.show();
                return false;
            }
        } else {
            Toast toast = Toast.makeText(getContext(),"Niste unijeli kapacitet",Toast.LENGTH_LONG);
            toast.show();
            return false;
            }


        if (!TextUtils.isEmpty(vKindOf.getText()) && vKindOf.getText().length()!=0) {
            nmr.setvKindOf(vKindOf.getText().toString());
        }  else {
            Toast toast = Toast.makeText(getContext(),"Niste unijeli ispravnu karakteristiku vozila",Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        return true;

    }

    private void swapLayouts(){

        newVehicleAdded.onNewVehicleAdded();

    }
}
