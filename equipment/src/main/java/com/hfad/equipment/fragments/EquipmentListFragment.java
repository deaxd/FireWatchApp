package com.hfad.equipment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hfad.equipment.adapters.EquipmentAdapter;
import com.hfad.equipment.listeners.NewEquipmentAdded;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import com.hfad.equipment.R;
import hr.foi.air.database.database.entities.Equipment;
import hr.foi.air.database.database.entities.Organization;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.listeners.EquipmentReceivedListener;

/**
 * Created by Matija on 28/01/2017.
 */

public class EquipmentListFragment extends Fragment implements EquipmentReceivedListener {

    private NewEquipmentAdded newEquipmentAdded;
    private RecyclerView recyclerView;

    MaterialDialog progressDialog;

    public EquipmentListFragment(){

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        newEquipmentAdded=(NewEquipmentAdded) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipment_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.erv_equipment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        WebServiceCaller wsc = new WebServiceCaller();
        Organization org = SQLite.select().from(Organization.class).querySingle();
        if (org != null) {
            wsc.getEquipment(org.getOrganizationId(),this );
            showProgress();
        }



        // TODO wsc.getEquipment(this);

        return view;
    }



    @Override
    public void onEquipmentFetched(List<Equipment> equipmentList) {
        hideProgress();
        recyclerView.setAdapter(new EquipmentAdapter(equipmentList,getContext()));
    }

    @Override
    public void onError(String error) {showMessage(error);}

    private void showMessage(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.app_name)
                .setMessage(message)
                .create();
        alertDialog.show();
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
