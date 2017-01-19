package com.hfad.core;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

import hr.foi.air.database.database.entities.Intervention;

/**
 * Created by Matija on 25/11/2016.
 */

public interface NavItem {
    public String getItemName();
    public int getPosition();
    public void setPosition(int position);
    public Fragment getFragment();
    public Drawable getIcon(Context context);
    public void setReadyForDataListener(ReadyForDataListener readyForDataListener);
    public void loadData(ArrayList<Intervention> Interventions);
}
