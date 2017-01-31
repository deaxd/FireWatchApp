package foi.hr.members;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


import android.view.View;
import android.widget.FrameLayout;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import foi.hr.members.fragments.MemberDetailsFragment;
import foi.hr.members.fragments.MembersFragment;
import foi.hr.members.fragments.NewMemberFragment;
import foi.hr.members.listeners.FragmentActionListener;
import hr.foi.air.database.database.entities.Fireman;

public class MembersActivity extends AppCompatActivity implements FragmentActionListener {

    private FrameLayout fragmentContainer;

    private Toolbar memToolbar;

    private FloatingActionButton fab;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);


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
     * Method used for handling actions ove floating action button. When clicked, MemberFragment is replaced with NewMemberFragment
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
        MembersFragment membersFragment = new MembersFragment();
        NewMemberFragment newMemberFragment = new NewMemberFragment();

        fragmentList.add(membersFragment);
        fragmentList.add(newMemberFragment);
    }

    /**
     * Interface method used for handling new member added event. When adding is finished NewMemberFragment is replaced with MemberFragment
     */
    @Override
    public void newMemberAddingFinished() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragmentList.get(0));
        ft.commit();

        fab.setVisibility(View.VISIBLE);
    }

    /**
     * Interface method used for handling on member click event. Replaces MemberFragment with MemberDetailsFragment using new instance of fireman data
     */

    @Override
    public void onMemberClicked(Fireman fireman) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, MemberDetailsFragment.newInstance(fireman));
        ft.addToBackStack(null);
        ft.commit();

        fab.setVisibility(View.GONE);
    }

    /**
     * Interface method used for handling invalid member event
     */
    @Override
    public void invalidMemberLoaded() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragmentList.get(0));
        ft.commit();

        fab.setVisibility(View.VISIBLE);
    }

    /**
     * Interface method used for handling member update event
     */

    @Override
    public void memberUpdateFinished() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragmentList.get(0));
        ft.commit();

        fab.setVisibility(View.VISIBLE);
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
}
