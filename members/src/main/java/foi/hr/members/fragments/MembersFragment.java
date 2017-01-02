package foi.hr.members.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import foi.hr.members.R;

public class MembersFragment extends Fragment {

    private RecyclerView recyclerView;

    public MembersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_members, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_members);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        
        return view;
    }

}
