package com.hfad.equipment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.hfad.equipment.fragments.EquipmentInputFragment;
import com.hfad.equipment.fragments.EquipmentListFragment;
import com.hfad.equipment.listeners.NewEquipmentAdded;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matija on 28/01/2017.
 */

public class EquipmentActivity extends AppCompatActivity implements NewEquipmentAdded {
    private FrameLayout fragmentContainer;

    private Toolbar equipToolbar;

    private FloatingActionButton fab;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);

        equipToolbar = (Toolbar) findViewById(R.id.equip_toolbar);
        setSupportActionBar(equipToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        addFragmentsToList();
        /**
         * Code used for initial fragment (EquipmentListFragment)
         */
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
     * Method used for handling floating action button. fab is used for fragment replace to EquipmentInputFragment
     */

    private void onFabClicked() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragmentList.get(1));
        ft.addToBackStack(null);
        ft.commit();

        fab.setVisibility(View.GONE);
    }

    /**
     * Method used for handling fragment list used for handling fragment replacement
     */
    private void addFragmentsToList() {
        EquipmentListFragment equipmentListFragment = new EquipmentListFragment();
        EquipmentInputFragment equipmentInputFragment = new EquipmentInputFragment();

        fragmentList.add(equipmentListFragment);
        fragmentList.add(equipmentInputFragment);
    }




    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStack();
            fab.setVisibility(View.VISIBLE);
        } else {
            finish();
        }
    }

    /**
     * Interface method used for handling onNewEquipmentAdded action. When new equipment is added, it replaces EquipmentInputFragment with EquipmentListFragment
     */
    @Override
    public void onNewEquipmentAdded() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragmentList.get(0));
        ft.commit();

        fab.setVisibility(View.VISIBLE);

    }
}
