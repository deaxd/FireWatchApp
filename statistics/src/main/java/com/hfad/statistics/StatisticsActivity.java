package com.hfad.statistics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hfad.statistics.fragments.GraphFragment;
import com.hfad.statistics.fragments.TableFragment;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import hr.foi.air.database.database.entities.User;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.listeners.StatisticReceivedListener;

/**
 * Created by Matija on 29/01/2017.
 */

public class StatisticsActivity extends AppCompatActivity implements StatisticReceivedListener{

    private FrameLayout fragmentContainer;

    private Toolbar memToolbar;

    private FloatingActionButton fab;

    private List<Fragment> fragmentList = new ArrayList<>();

    private int tnumberMembers;
    private int tnumberInterventions;
    private int tnumberIntThisYear;
    private double tnumberIntAvg;
    private int tnumberVehicles;

    StasticsInterface listener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);


        WebServiceCaller wsc = new WebServiceCaller();
        User user = SQLite.select().from(User.class).querySingle();
        if (user != null) {
            wsc.getStatistics(user.getUserOib(), this);

        }

        memToolbar = (Toolbar) findViewById(R.id.mem_toolbar);
        setSupportActionBar(memToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            addFragmentsToList();

        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
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

    /**
     * Method for handling floating action button click. When clicked TableFragment is replaced with GraphFragment
     */
    @Override
    public void onStatisticReceived(int numberMembers, int numberInterventions, int numberIntThisYear, double numberIntAvg, int numberVehicles) {
        tnumberMembers = numberMembers;
        tnumberInterventions = numberInterventions;
        tnumberIntThisYear = numberIntThisYear;
        tnumberIntAvg = numberIntAvg;
        tnumberVehicles = numberVehicles;
        sendData(listener);
    }


    private void sendData(StasticsInterface listener){

        listener.Statisticshow(tnumberMembers, tnumberInterventions, tnumberIntThisYear,tnumberIntAvg, tnumberVehicles);



    }
    private void onFabClicked() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragmentList.get(1));
        ft.addToBackStack(null);
        ft.commit();

        fab.setVisibility(View.GONE);
    }

    private void addFragmentsToList() {
        TableFragment tableFragment = new TableFragment();
       GraphFragment graphFragment = new GraphFragment();

        fragmentList.add(tableFragment);
        fragmentList.add(graphFragment);
    }



    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() != 0) {
            fm.popBackStack();
            fab.setVisibility(View.VISIBLE);
        } else {
            finish();
        }
    }


}
