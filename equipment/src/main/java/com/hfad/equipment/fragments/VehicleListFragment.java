package com.hfad.equipment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hfad.equipment.R;
import com.hfad.equipment.listeners.NewVehicleAdded;

import java.util.List;

import hr.foi.air.database.database.entities.Vehicle;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.listeners.VehicleReceivedListener;

/**
 * Created by Matija on 28/01/2017.
 */

public class VehicleListFragment extends Fragment implements VehicleReceivedListener {


    private NewVehicleAdded newVehicleAdded;
    private RecyclerView recyclerView;

    public VehicleListFragment(){

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        newVehicleAdded=(NewVehicleAdded) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.erv_vehicle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        WebServiceCaller wsc = new WebServiceCaller();
        // TODO wsc.getVehicle(this);

        return view;
    }





    @Override
    public void onEquipmentFetched(List<Vehicle> vehicleList) {

    }
}