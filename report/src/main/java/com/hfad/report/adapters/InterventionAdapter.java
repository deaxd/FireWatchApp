package com.hfad.report.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hfad.report.R;

import hr.foi.air.webservice.listeners.InterventionClickListener;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.database.database.entities.Intervention;

/**
 * Created by DELL on 17.1.2017..
 */

public class InterventionAdapter extends RecyclerView.Adapter<InterventionAdapter.ViewHolder> {

    List<Intervention> interventionList = new ArrayList<>();

    Context context;

    private InterventionClickListener interventionClickListener;

    public InterventionAdapter(List<Intervention> interventionList, Context context, InterventionClickListener interventionClickListener) {
        this.interventionList = interventionList;
        this.context = context;
        this.interventionClickListener = interventionClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_intervention, parent, false), interventionClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.mIntId.setText(interventionList.get(position).getInterventionId());
        holder.mKindOfIntervention.setText(interventionList.get(position).getKindOfIntervention());
        holder.mAddress.setText(interventionList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return interventionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mKindOfIntervention;
        TextView mAddress;

        public ViewHolder(View itemView, final InterventionClickListener interventionClickListener){
            super(itemView);


            mKindOfIntervention = (TextView) itemView.findViewById(R.id.tv_int_kindOf);
            mAddress = (TextView) itemView.findViewById(R.id.tv_int_address);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    interventionClickListener.onInterventionClicked(interventionList.get(getAdapterPosition()));
                }
            });
        }
    }



}