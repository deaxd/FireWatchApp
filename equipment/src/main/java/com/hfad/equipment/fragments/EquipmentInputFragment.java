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
import com.hfad.equipment.listeners.NewEquipmentAdded;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.rengwuxian.materialedittext.MaterialEditText;

import hr.foi.air.database.database.entities.Organization;
import com.hfad.equipment.NewEquipmentRequest;
import hr.foi.air.webservice.WebServiceCaller;

/**
 * Created by Matija on 28/01/2017.
 */

public class EquipmentInputFragment extends Fragment {

    private MaterialEditText eqName;

    private MaterialEditText eqQuantity;

    private Button btnSave;

    private NewEquipmentAdded newEquipmentAdded;


    public EquipmentInputFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        newEquipmentAdded = (NewEquipmentAdded) context;
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
        eqName = (MaterialEditText) v.findViewById(R.id.eet_name);
        eqQuantity = (MaterialEditText) v.findViewById(R.id.eet_quantity);
        btnSave = (Button) v.findViewById(R.id.btn_save);
    }

    private void onSaveClicked() {
        if(validateInput()) {
            swapLayouts();


            WebServiceCaller wsc = new WebServiceCaller();

            Organization org = SQLite.select().from(Organization.class).querySingle();

            if (org != null) {
                wsc.insertEquipment(eqName.getText().toString(), Integer.valueOf(eqQuantity.getText().toString()), org.getOrganizationId());
                //showProgress();
            }
        }

    }

    private boolean validateInput() {
        NewEquipmentRequest nmr = new NewEquipmentRequest();


        if (!TextUtils.isEmpty(eqName.getText()) && eqName.getText().length()!=0) {
            nmr.setName(eqName.getText().toString());
        } else {
            Toast toast = Toast.makeText(getContext(),"Niste unijeli naziv opreme",Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        if (!TextUtils.isEmpty(eqQuantity.getText()) && eqQuantity.getText().length()!=0) {
            try {
                Integer.parseInt(eqQuantity.getText().toString());
                nmr.setQuantity(Integer.valueOf(eqQuantity.getText().toString()));
            } catch (NumberFormatException e) {
                Toast toast = Toast.makeText(getContext(), "Niste unijeli ispravnu vrijednost količine", Toast.LENGTH_LONG);
                toast.show();
                return false;
            }
        } else {
            Toast toast = Toast.makeText(getContext(), "Niste unijeli količinu", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        return true;

    }

    private void swapLayouts(){

        newEquipmentAdded.onNewEquipmentAdded();

    }

}
