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
import hr.foi.air.database.database.entities.Equipment;

/**
 * Created by Matija on 28/01/2017.
 */

/**
 * Adapter used for display of essential equipment data
 */

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.ViewHolder> {


    private List<Equipment> equipmentList = new ArrayList<>();

    private Context context;



    public EquipmentAdapter(List<Equipment> equipmentList, Context context) {
        this.equipmentList = equipmentList;
        this.context = context;

    }

    @Override
    public EquipmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_equipment, parent, false));
    }

    @Override
    public void onBindViewHolder(EquipmentAdapter.ViewHolder holder, int position) {

        holder.equipName.setText(equipmentList.get(position).getName());
        holder.equipQuantity.setText(String.valueOf(equipmentList.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {



        TextView equipName;

        TextView equipQuantity;


        public ViewHolder(View itemView) {
            super(itemView);

            equipName = (TextView) itemView.findViewById(R.id.etv_equipment_name);
            equipQuantity = (TextView) itemView.findViewById(R.id.etv_equipment_quantity);
        }
    }
}
