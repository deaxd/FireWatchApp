package com.hfad.equipment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import hr.foi.air.database.database.entities.Intervention;
import hr.foi.air.database.database.entities.Organization;
import hr.foi.air.database.database.entities.Organization_Table;
import hr.foi.air.database.database.entities.User;
import hr.foi.air.database.database.entities.Vehicle;


/**
 * A simple {@link Fragment} subclass.
 */
public class VehicleInput extends Fragment {


    public VehicleInput() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_vehicle_input, container, false);

        final EditText vehname = (EditText) v.findViewById(R.id.vehname);
        final String vehiname = vehname.getText().toString();

        final EditText seatnum = (EditText) v.findViewById(R.id.seatnum);
        final String seatnumber = seatnum.getText().toString();
        final int pseatnumber=Integer.parseInt(seatnumber);

        final EditText water = (EditText) v.findViewById(R.id.capac);
        final String watercap = water.getText().toString();
        final int pwatercap=Integer.parseInt(watercap);

        final EditText kindoveh = (EditText) v.findViewById(R.id.kindoveh);
        final String kind = kindoveh.getText().toString();

        final EditText organ = (EditText) v.findViewById(R.id.organ);
        final String organisation = organ.getText().toString();

        final Button button = (Button) v.findViewById(R.id.predaj);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                User user;
                user = SQLite.select().from(User.class).querySingle();

                Organization organization;
                organization = SQLite.select().from(Organization.class).where(Organization_Table.organizationId.eq(Integer.parseInt(user.getUserOrganization()))).querySingle();

                Vehicle vehicle1  = new Vehicle();
                vehicle1.setName(vehiname);
                vehicle1.setSeatNumber(pseatnumber);
                vehicle1.setWaterVolume(pwatercap);
                vehicle1.setKindOfVehicle(kind);
                vehicle1.setOrganization(organization);
                vehicle1.save();
            }
        });

        return v;
    }

}
