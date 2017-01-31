package com.hfad.report;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import com.hfad.core.CurrentActivity;
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

    private Toolbar toolbar;

    private FloatingActionButton fabi;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_interventions);
        CurrentActivity.setActivity(this);

        toolbar = (Toolbar) findViewById(R.id.int_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addFragmentToList();


        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        fabi = (FloatingActionButton) findViewById(R.id.fabi);

        fabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFabClicked();
            }
        });

        /**
         * Initial fragment loaded is InterventionListFragment
         */

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragmentList.get(0));
        ft.addToBackStack(null);
        ft.commit();


    }

    /**
     * Method used for handling action over floating action button. When clicked InterventionListFragment is replaced with InterventionSubmitFragment
     */
    private void onFabClicked() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragmentList.get(1));
        ft.addToBackStack(null);
        ft.commit();

        fabi.setVisibility(View.GONE);

    }

    /**
     * Method used for adding fragments to fragment list, which is used for handling fragment replacement
     */
    private void addFragmentToList() {
        IntervetionListFragment intervetionListFragment = new IntervetionListFragment();
        InterventionSubmitFragment interventionSubmitFragment = new InterventionSubmitFragment();

        fragmentList.add(intervetionListFragment);
        fragmentList.add(interventionSubmitFragment);
    }

    @Override
    public void onInterventionsFetched(List<Intervention> interventionList) {

    }

    /**
     * Interface method used for handling on intervention clicked event. NEw instance of clicked intervetion is made and InterventionListFragment is replaced with InterventionDetailFragment
     * @param intervention
     */
    @Override
    public void onInterventionClicked(Intervention intervention) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, IntervetionDetailFragment.newInstance(intervention));
        ft.addToBackStack(null);
        ft.commit();

        fabi.setVisibility(View.VISIBLE);
    }


    /**
     * Interface method for handling new intervention added event. Replaces InterventionSubmitFragment with InterventionListFragment
     */

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
       if (fm.getBackStackEntryCount() > 0) {
           fm.popBackStack();
           fabi.setVisibility(View.VISIBLE);
       } else {
           finish();
       }

    }

}
