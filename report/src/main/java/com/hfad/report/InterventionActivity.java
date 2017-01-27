package com.hfad.report;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.hfad.report.listeners.InterventionAddedListener;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.database.database.entities.Intervention;
import hr.foi.air.webservice.listeners.InterventionClickListener;

/**
 * Created by DELL on 18.1.2017..
 */

public class InterventionActivity extends AppCompatActivity implements InterventionClickListener, InterventionAddedListener {

    private FrameLayout fragmentContainer;

    private FloatingActionButton fabi;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_interventions);

        addFragmentToList();

        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        fabi = (FloatingActionButton) findViewById(R.id.fabi);

        fabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFabClicked();
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragmentList.get(0));
        ft.addToBackStack(null);
        ft.commit();


    }

    private void onFabClicked() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragmentList.get(1));
        ft.addToBackStack(null);
        ft.commit();

        fabi.setVisibility(View.GONE);

    }

    private void addFragmentToList() {
        IntervetionListFragment intervetionListFragment = new IntervetionListFragment();
        InterventionSubmitFragment interventionSubmitFragment = new InterventionSubmitFragment();

        fragmentList.add(intervetionListFragment);
        fragmentList.add(interventionSubmitFragment);
    }

    @Override
    public void onInterventionsFetched(List<Intervention> interventionList) {

    }

    @Override
    public void onInterventionClicked(Intervention intervention) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, IntervetionDetailFragment.newInstance(intervention));
        ft.addToBackStack(null);
        ft.commit();

        fabi.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNewInterventionAdded() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragmentList.get(0));
        ft.commit();

        fabi.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(String error) {

    }


    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStack();
            fabi.setVisibility(View.VISIBLE);
        } else {
            finish();
        }

    }

}
