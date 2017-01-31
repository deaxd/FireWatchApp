package com.hfad.equipment.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.hfad.equipment.R;
import hr.foi.air.database.database.entities.Vehicle;

/**
 * Created by Matija on 28/01/2017.
 */

/**
 * Adapter used for handling vehicle list data and saving them to text view fields
 */

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {
    private List<Vehicle> vehicleList = new ArrayList<>();

    private Context context;



    public VehicleAdapter(List<Vehicle> vehicleList, Context context) {
        this.vehicleList = vehicleList;
        this.context = context;

    }

    @Override
    public VehicleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_vehicle, parent, false));
    }

    @Override
    public void onBindViewHolder(VehicleAdapter.ViewHolder holder, int position) {

        holder.name.setText(vehicleList.get(position).getName());
        holder.seatNumber.setText(String.valueOf(vehicleList.get(position).getSeatNumber()));
        holder.waterVolume.setText(String.valueOf(vehicleList.get(position).getWaterVolume()));
        holder.kindOfVehicle.setText(vehicleList.get(position).getKindOfVehicle());
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {



        TextView name;
        TextView seatNumber;  //int
        TextView  waterVolume; //int
        TextView  kindOfVehicle;


        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.vtv_vehicle_name);
            seatNumber = (TextView) itemView.findViewById(R.id.vtv_vehicle_seatNumber);
            waterVolume = (TextView) itemView.findViewById(R.id.vtv_vehicle_waterVolume);
            kindOfVehicle = (TextView) itemView.findViewById(R.id.vtv_vehicle_kindOfVehicle);

        }
    }
}
