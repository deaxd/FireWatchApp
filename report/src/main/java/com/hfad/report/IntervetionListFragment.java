package com.hfad.report;


import com.afollestad.materialdialogs.MaterialDialog;
import com.hfad.report.adapters.InterventionAdapter;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hr.foi.air.database.database.entities.Intervention;
import hr.foi.air.database.database.entities.User;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.listeners.InterventionClickListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntervetionListFragment extends Fragment implements InterventionClickListener {

    private RecyclerView recyclerView;
    private MaterialDialog progressDialog;

   private InterventionClickListener interventionClickListener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interventionClickListener = (InterventionClickListener) context;
    }

    public IntervetionListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intervetion_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_intervention);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        WebServiceCaller wsc = new WebServiceCaller();
        User user = SQLite.select().from(User.class).querySingle();
        if (user != null) {
            wsc.getInterventions(user.getUserOib(), this);
            showProgress();
        }
        return view;

    }

    /**
     * Interface method used for setting InterventionAdapter triggered by intervention fetched event
     * @param interventionList
     */
    @Override
    public void onInterventionsFetched(List<Intervention> interventionList) {
        hideProgress();
        recyclerView.setAdapter(new InterventionAdapter(interventionList, getActivity(), this));
    }

    @Override
    public void onInterventionClicked(Intervention intervention) {
        interventionClickListener.onInterventionClicked(intervention);
    }


    @Override
    public void onError(String error) {showMessage(error);}

    private void showMessage(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.app_name)
                .setMessage(message)
                .create();
        alertDialog.show();
    }

    private void showProgress() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new MaterialDialog.Builder(getActivity())
                    .title(R.string.app_name)
                    .content("Please Wait....")
                    .progress(true, 0)
                    .build();
            progressDialog.setCanceledOnTouchOutside(false);
        }
        if (!getActivity().isFinishing()) {
            progressDialog.show();
        }
    }

    private void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing() && !getActivity().isFinishing()) {
            progressDialog.dismiss();
        }
    }

}