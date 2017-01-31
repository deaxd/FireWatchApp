package foi.hr.firewatchapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Matija on 24/01/2017.
 */

/**
 * Singleton class used for handling firebase request
 */

public class MySingleton {
    private static MySingleton mInstance;
    private static Context mCtx;
    private RequestQueue requestQueue;

    private MySingleton(Context context){
        mCtx= context;
        requestQueue=getRequestQueue();
    }

    /**
     * Method used for handling volley requests
     * @return
     */
    private RequestQueue getRequestQueue(){

        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return  requestQueue;
    }

    public static  synchronized MySingleton getmInsatnce(Context context){

        if (mInstance==null)
        {
            mInstance= new MySingleton(context);
        }
        return mInstance;
    }

    public<T> void addToRequestque  (Request<T> request){
        getRequestQueue().add(request);
    }
}
