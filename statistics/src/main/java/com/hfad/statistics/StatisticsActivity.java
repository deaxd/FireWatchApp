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

import com.hfad.statistics.fragments.GraphFragment;
import com.hfad.statistics.fragments.TableFragment;

/**
 * Created by Matija on 29/01/2017.
 */

public class StatisticsActivity extends AppCompatActivity {

    private FrameLayout fragmentContainer;

    private Toolbar memToolbar;

    private FloatingActionButton fab;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);


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
