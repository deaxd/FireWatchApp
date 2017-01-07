package com.hfad.report;


import com.hfad.core.NavItem;
import com.hfad.core.ReadyForDataListener;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import adapters.InterventionRecyclerAdapter;
import hr.foi.air.database.database.entities.Intervention;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntervetionListFragment extends Fragment implements NavItem {

    private InterventionRecyclerAdapter adapter;

    private int position;

    private String name = "Intervencije";

    private ReadyForDataListener readyForDataListener;


    public IntervetionListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intervetion_list, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        readyForDataListener.onReadyForData(this);
    }

    @Override
    public String getItemName() {
        return name;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public Drawable getIcon(Context context) {
        return null;
    }

    @Override
    public void setReadyForDataListener(ReadyForDataListener readyForDataListener) {
        this.readyForDataListener = readyForDataListener;
    }

    @Override
    public void loadData(ArrayList<Intervention> interventions) {
        List<Intervention> interventionItemList = new ArrayList<Intervention>();

        RecyclerView mRecycler = (RecyclerView) getView().findViewById(R.id.recycler_intervention);

        if (mRecycler != null) {
            adapter = new InterventionRecyclerAdapter(interventionItemList, getActivity());
            mRecycler.setAdapter(adapter);
            mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }
}