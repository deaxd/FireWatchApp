package foi.hr.members.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import foi.hr.members.R;
import foi.hr.members.adapters.MembersAdapter;
import hr.foi.air.database.database.entities.Fireman;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.listeners.MembersReceivedListener;

public class MembersFragment extends Fragment implements MembersReceivedListener {

    private RecyclerView recyclerView;

    public MembersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_members, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_members);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        WebServiceCaller wsc = new WebServiceCaller();

        wsc.getMembers("35468413516", this);
        return view;
    }

    @Override
    public void onMembersFetched(List<Fireman> firemanList) {
        recyclerView.setAdapter(new MembersAdapter(firemanList, getActivity()));
    }

    @Override
    public void onError(String error) {
        showMessage(error);
    }

    private void showMessage(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.app_name)
                .setMessage(message)
                .create();
        alertDialog.show();
    }
}
