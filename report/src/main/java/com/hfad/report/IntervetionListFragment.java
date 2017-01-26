package com.hfad.report;


import com.hfad.report.adapters.InterventionAdapter;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hr.foi.air.database.database.entities.Intervention;
import hr.foi.air.database.database.entities.User;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.listeners.InterventionClickListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntervetionListFragment extends Fragment implements InterventionClickListener {

    private RecyclerView recyclerView;

    private InterventionClickListener interventionClickListener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interventionClickListener = (InterventionClickListener) context;
    }

    public IntervetionListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intervetion_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_intervention);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        WebServiceCaller wsc = new WebServiceCaller();
        User user = SQLite.select().from(User.class).querySingle();
        if (user != null) {
            wsc.getInterventions(user.getUserOib(), this);
            //showProgress();
        }
        return view;

    }

    @Override
    public void onInterventionsFetched(List<Intervention> interventionList) {
        //hideProgress();
        recyclerView.setAdapter(new InterventionAdapter(interventionList, getActivity(), this));
    }

    @Override
    public void onInterventionClicked(Intervention intervention) {
        //interventionClickListener.onInterventionClicked(intervention);
    }

    @Override
    public void onError(String error) {

    }

    //TODO add showProgress, hideProgress, don't know if I need it

    //TODO 22.01.2017 - Yes you do, my suggestion would be to extract these methods to an abstract class which all our activities
    //TODO would extend so we dont need to retype every time these methods in every activity.
    //TODO So, BaseActivity would extend Activity class and have methods showProgress(), hideProgress() and our activities
    //TODO such as LoginActivity would extend BaseActivity thus having all these methods implemented by default
}