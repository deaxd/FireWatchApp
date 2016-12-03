package foi.hr.firewatchapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.hfad.core.DataLoadedListener;
import com.hfad.core.DataLoader;
import com.hfad.core.NavItem;
import com.hfad.core.ReadyForDataListener;

import java.util.ArrayList;

import foi.hr.firewatchapp.loaders.DBDataLoader;
import hr.foi.air.database.database.entities.Intervention;
import hr.foi.air.database.database.entities.User;


/**
 * Created by Matija on 25/11/2016.
 */

public class NavManager implements DataLoadedListener, ReadyForDataListener {

    private static NavManager instance;
    private Activity mHandlerActivity;
    public ArrayList<NavItem> navigationItems;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private int mItemGroupID;
    private ArrayList<Intervention> interventions;
    private boolean dataLoadedFlag = false;
    private boolean moduleReadyForDataFlag = false;
    private NavItem readyForDataModule;

    private NavManager() {
        navigationItems = new ArrayList<NavItem>();
        interventions = new ArrayList<Intervention>();
        requestForData();
    }

    public static NavManager getInstance() {
        if (instance == null) {
            instance = new NavManager();}

            return instance;

    }


    public void setDependencies(Activity handlerActivity, DrawerLayout drawerLayout, NavigationView navigationView, int ItemGroupID){
        this.mHandlerActivity=handlerActivity;
        this.mDrawerLayout= drawerLayout;
        this.mNavigationView=navigationView;
        this.mItemGroupID=ItemGroupID;
    }



    public void selectNavigationItem(MenuItem menuItem){
        if(!menuItem.isChecked()){
            menuItem.setChecked(true);
            mDrawerLayout.closeDrawer(GravityCompat.START, true);
            NavItem clickedItem = navigationItems.get(menuItem.getItemId());
            displayFragment(clickedItem);
        }
    }

    public void addItem(NavItem newItem) {
        newItem.setPosition(navigationItems.size());
        navigationItems.add(newItem);
        mNavigationView.getMenu().add(mItemGroupID, newItem.getPosition(), newItem.getPosition() + 1, newItem.getItemName())
                .setIcon(newItem.getIcon(mHandlerActivity))
                .setCheckable(true);
    }

    private void displayFragment(NavItem clickedItem){
        this.moduleReadyForDataFlag = false;
        this.readyForDataModule = null;
        clickedItem.setReadyForDataListener(this);

        FragmentManager fragmentManager = mHandlerActivity.getFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager.beginTransaction()
                .replace(R.id.activity_main, clickedItem.getFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }



    @Override
    public void onInterventionLoaded(ArrayList<Intervention> interventions) {
        this.interventions=interventions;
        this.dataLoadedFlag=true;
    }

    public void onUserLoaded(User user){
        
    }

    @Override
    public void onReadyForData(NavItem navigationItem) {
            this.readyForDataModule=navigationItem;
            this.moduleReadyForDataFlag = true;

    }
    private void synchronizeAndSendData()
    {
        if (this.dataLoadedFlag == true && this.moduleReadyForDataFlag == true)
        {
            readyForDataModule.loadData(interventions);
        }
    }

    public void showDefaultFragment(NavItem preferredItem) {

        displayFragment(preferredItem);
        mNavigationView.getMenu().getItem(preferredItem.getPosition()).setChecked(true);
    }

    public void showDefaultFragment()
    {
        if (navigationItems.size() > 0)
        {
            showDefaultFragment(navigationItems.get(0));
        }
    }

    public void requestForData(){
        DataLoader dataLoader;
            System.out.println("Loading local data");
            dataLoader = new DBDataLoader();
        dataLoader.loadData(this);
    }
}
