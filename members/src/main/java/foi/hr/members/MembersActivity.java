package foi.hr.members;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import foi.hr.members.fragments.MemberDetailsFragment;
import foi.hr.members.fragments.MembersFragment;
import foi.hr.members.fragments.NewMemberFragment;

public class MembersActivity extends AppCompatActivity {

    private FrameLayout fragmentContainer;

    private FloatingActionButton fab;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

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
        ft.commit();
    }

    private void onFabClicked() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragmentList.get(1));
        ft.commit();

        fab.setVisibility(View.GONE);
    }

    private void addFragmentsToList() {
        MembersFragment membersFragment = new MembersFragment();
        NewMemberFragment newMemberFragment = new NewMemberFragment();
        MemberDetailsFragment memberDetailsFragment = new MemberDetailsFragment();

        fragmentList.add(membersFragment);
        fragmentList.add(newMemberFragment);
        fragmentList.add(memberDetailsFragment);
    }


}
