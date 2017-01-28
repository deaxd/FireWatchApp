package com.hfad.equipment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hfad.equipment.R;
import com.hfad.equipment.listeners.NewVehicleAdded;
import com.rengwuxian.materialedittext.MaterialEditText;

import hr.foi.air.webservice.Responses.NewVehicleRequest;
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
        View v = inflater.inflate(R.layout.fragment_equipment_input, container, false);
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
        validateInput();
        swapLayouts();


        WebServiceCaller webServiceCaller = new WebServiceCaller();
        //TODO webServiceCaller.insertVehicle(vName.getText().toString(),Integer.valueOf(vSeatNum.getText().toString(),Integer.valueOf(vWaterVolume.getText().toString()),vKindOf.getText().toString());

    }

    private void validateInput() {
        NewVehicleRequest nmr = new NewVehicleRequest();


        if (!TextUtils.isEmpty(vName.getText())) {
            nmr.setvName(vName.getText().toString());
        }

        if (!TextUtils.isEmpty(vSeatNum.getText())) {
            nmr.setvSeatNum(Integer.valueOf(vSeatNum.getText().toString()));
        }

        if (!TextUtils.isEmpty(vWaterVolume.getText())) {
            nmr.setvWaterVolume(Integer.valueOf(vWaterVolume.getText().toString()));
        }

        if (!TextUtils.isEmpty(vKindOf.getText())) {
            nmr.setvKindOf(vKindOf.getText().toString());
        }

    }

    private void swapLayouts(){

        newVehicleAdded.onNewVehicleAdded();

    }
}
