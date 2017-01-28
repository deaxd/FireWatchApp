package foi.hr.firewatchapp;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.iid.FirebaseInstanceId;
import com.hfad.core.CurrentActivity;
import com.hfad.equipment.EquipmentActivity;
import com.hfad.report.InterventionActivity;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLCondition;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import android.app.DownloadManager;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import foi.hr.firewatchapp.helper.MockData;
import foi.hr.members.MembersActivity;
import hr.foi.air.database.database.entities.Organization;
import hr.foi.air.database.database.entities.User;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.listeners.OrganizationReceivedListener;

public class MainActivity extends AppCompatActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener, NavigationView.OnNavigationItemSelectedListener,
        FragmentManager.OnBackStackChangedListener, OrganizationReceivedListener {

    // private DrawerLayout mDrawerLayout;



    private ActionBarDrawerToggle mToggle;

    private Toolbar mToolbar;

    private DrawerLayout mDrawer;

    private NavigationView mNavigationView;

    private FragmentManager mFragmentManager;

    private SharedPreferences mSharedPreferences;

    Button button;
    String app_server_url="http://127.0.0.1:8080/fcmtest/fcm_insert.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CurrentActivity.setActivity(this);

        String recent_token = FirebaseInstanceId.getInstance().getToken();

        long org = SQLite.select().from(Organization.class).query().getCount();
        if(org == 0) {
            User user = SQLite.select().from(User.class).querySingle();
            System.out.println(user.getUserOib());
            WebServiceCaller webServiceCaller = new WebServiceCaller();
            webServiceCaller.getOrganization(user.getUserOib(), this);
        }

        button= (Button)findViewById(R.id.tokbut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
                //final String token = sharedPreferences.getString(getString(R.string.FCM_TOKEN),"");
                final String token = android.preference.PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                        .getString(getString(R.string.FCM_TOKEN), "");

                StringRequest stringRequest = new StringRequest(Request.Method.POST, app_server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
                {
                    @Override
                    public Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                       String recent_token = FirebaseInstanceId.getInstance().getToken();
                        params.put("fcm_token",token);

                        return params;
                    }
                };

                MySingleton.getmInsatnce(MainActivity.this).addToRequestque(stringRequest);
            }
        });



        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.activity_main);
        mToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.open, R.string.close);

        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        mFragmentManager = getFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(this);

        mToolbar.setNavigationOnClickListener(navigationClick);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavManager nm = NavManager.getInstance();
        nm.setDependencies(this, mDrawer, mNavigationView, R.id.dynamic_group);
        //nm.addItem( new InterventionActivity());

        //nm.showDefaultFragment();

        //MockData.writeAll();

    }


    View.OnClickListener navigationClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (getFragmentManager().getBackStackEntryCount() == 0) {
                mDrawer.openDrawer(GravityCompat.START);
            } else {
                onBackPressed();
            }
        }
    };

    @Override
    public void onOrganizationFetched(Organization organization) {
        organization.save();
        System.out.println(organization.getName());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //NavManager.getInstance().selectNavigationItem(item);
        //return true;

        int id = item.getItemId();

        //to prevent current item select over and over
        if (item.isChecked()) {
            mDrawer.closeDrawer(GravityCompat.START);
            return false;
        }
        if (id == R.id.int_activity) {
            // Handle the camera action
            startActivity(new Intent(getApplicationContext(), InterventionActivity.class));
        } else if (id == R.id.members_activity) {
            startActivity(new Intent(getApplicationContext(), MembersActivity.class));
        } else if (id == R.id.equipment_activity){
            startActivity(new Intent(getApplicationContext(), EquipmentActivity.class));
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackStackChanged() {
        mToggle.setDrawerIndicatorEnabled(mFragmentManager.getBackStackEntryCount() == 0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(mFragmentManager.getBackStackEntryCount() > 0);
        mToggle.syncState();
    }


    @Override
    public void onBackPressed() {
        if (mFragmentManager.getBackStackEntryCount() != 0) {
            if (mDrawer.isDrawerOpen(GravityCompat.START)) {
                mDrawer.closeDrawer(GravityCompat.START);
            } else {
                mFragmentManager.popBackStack();
            }
        } else {
            if (mDrawer.isDrawerOpen(GravityCompat.START)) {
                mDrawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

}
