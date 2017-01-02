package foi.hr.firewatchapp;

import com.hfad.core.CurrentActivity;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import android.app.FragmentManager;
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

import butterknife.ButterKnife;
import foi.hr.firewatchapp.helper.MockData;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener,NavigationView.OnNavigationItemSelectedListener,FragmentManager.OnBackStackChangedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private FragmentManager mFragmentManager;
    private SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CurrentActivity.setActivity(this);

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
        //nm.addItem( new IntervetionListFragment());

        //nm.showDefaultFragment();

        MockData.writeAll();
    }



    View.OnClickListener navigationClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(getFragmentManager().getBackStackEntryCount() == 0) {
                mDrawer.openDrawer(GravityCompat.START);
            }
            else{
                onBackPressed();
            }
        }
    };



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        NavManager.getInstance().selectNavigationItem(item);
        return true;
    }

    @Override
    public void onBackStackChanged() {
        mToggle.setDrawerIndicatorEnabled(mFragmentManager.getBackStackEntryCount()==0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(mFragmentManager.getBackStackEntryCount()>0);
        mToggle.syncState();
    }


    @Override
    public void onBackPressed(){
        if(mFragmentManager.getBackStackEntryCount() != 0){
            if(mDrawer.isDrawerOpen(GravityCompat.START)){
                mDrawer.closeDrawer(GravityCompat.START);
            }else{
                mFragmentManager.popBackStack();
            }
        }else{
            if(mDrawer.isDrawerOpen(GravityCompat.START)){
                mDrawer.closeDrawer(GravityCompat.START);
            }else{
                super.onBackPressed();
            }
        }

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}
