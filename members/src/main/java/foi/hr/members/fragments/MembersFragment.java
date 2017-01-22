package foi.hr.members.fragments;

import com.afollestad.materialdialogs.MaterialDialog;
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

import foi.hr.members.R;
import foi.hr.members.adapters.MembersAdapter;
import foi.hr.members.listeners.FragmentActionListener;
import foi.hr.members.listeners.MemberClickListener;
import hr.foi.air.database.database.entities.Fireman;
import hr.foi.air.database.database.entities.User;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.listeners.MembersReceivedListener;

public class MembersFragment extends Fragment implements MembersReceivedListener, MemberClickListener {

    private RecyclerView recyclerView;

    private FragmentActionListener fragmentActionListener;

    private MaterialDialog progressDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentActionListener = (FragmentActionListener) context;
    }

    public MembersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_members, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_members);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        WebServiceCaller wsc = new WebServiceCaller();
        User user = SQLite.select().from(User.class).querySingle();
        if (user != null) {
            wsc.getMembers(user.getUserOib(), this);
            showProgress();
        }
        return view;
    }

    @Override
    public void onMembersFetched(List<Fireman> firemanList) {
        hideProgress();
        recyclerView.setAdapter(new MembersAdapter(firemanList, getActivity(), this));
    }

    @Override
    public void onError(String error) {
        showMessage(error);
    }

    @Override
    public void onMemberClicked(Fireman fireman) {
        fragmentActionListener.onMemberClicked(fireman);
    }

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
                    .content(R.string.please_wait)
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
