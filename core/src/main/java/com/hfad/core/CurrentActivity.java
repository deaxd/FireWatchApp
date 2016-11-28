package com.hfad.core;

import android.app.Activity;

/**
 * Created by Matija on 28/11/2016.
 */

public class CurrentActivity {
    private static Activity mActivity;

    public static Activity getActivity()
    {
        return mActivity;
    }

    public static void setActivity(Activity activity)
    {
        mActivity = activity;
    }
}
