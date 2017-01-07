package com.hfad.equipment;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import hr.foi.air.database.database.entities.Equipment;
import hr.foi.air.database.database.entities.Organization;
import hr.foi.air.database.database.entities.Organization_Table;
import hr.foi.air.database.database.entities.User;

/**
 * Created by Denis on 23.12.2016..
 */

public class EquipmentInput extends Fragment{

    public EquipmentInput() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_equipment_input, container, false);



        final EditText equipname = (EditText) v.findViewById(R.id.eqiupname);
        final String equipmentname = equipname.getText().toString();

        final EditText quant = (EditText) v.findViewById(R.id.quantity);
        final String quantity = quant.getText().toString();
        final int numbQuantity=Integer.parseInt(quantity);


        final Button button = (Button) v.findViewById(R.id.submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                User user;
                user = SQLite.select().from(User.class).querySingle();

                Organization organization;
                organization = SQLite.select().from(Organization.class).where(Organization_Table.organizationId.eq(Integer.parseInt(user.getUserOrganization()))).querySingle();
                Equipment equipment = new Equipment();
                equipment.setName(equipmentname);
                equipment.setQuantity(numbQuantity);
                equipment.setOrganization(organization);
                equipment.save();

            }
        });
        return v;
    }


}
