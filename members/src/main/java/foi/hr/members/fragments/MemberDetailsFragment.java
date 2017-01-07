package foi.hr.members.fragments;


import com.rengwuxian.materialedittext.MaterialEditText;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import foi.hr.members.R;
import foi.hr.members.listeners.FragmentActionListener;
import hr.foi.air.database.database.entities.Fireman;


public class MemberDetailsFragment extends Fragment {

    public static final String EXTRA_FIREMAN = "EXTRA_FIREMAN";

    public static final int DELAY_MILLIS = 1500;

    private MaterialEditText etOib;

    private MaterialEditText etName;

    private MaterialEditText etSurname;

    private MaterialEditText etUsername;

    private MaterialEditText etPassword;

    private Switch swtLieu;

    private Button btnSave;

    private FragmentActionListener fragmentActionListener;

    private Fireman fireman;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentActionListener = (FragmentActionListener) context;
    }

    public static MemberDetailsFragment newInstance(Fireman fireman) {
        MemberDetailsFragment memberDetailsFragment = new MemberDetailsFragment();

        Bundle args = new Bundle();
        args.putSerializable(EXTRA_FIREMAN, fireman);
        memberDetailsFragment.setArguments(args);

        return memberDetailsFragment;
    }

    public MemberDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_member_details, container, false);

        fireman = (Fireman) getArguments().getSerializable(EXTRA_FIREMAN);

        if (fireman == null) {
            Toast.makeText(getActivity(), "Invalid fireman loaded", Toast.LENGTH_SHORT).show();

            //Delays the action for 1500ms, after 1500ms runs out, it triggers the invalidMemberLoaded() method of fragmentActionListener
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    fragmentActionListener.invalidMemberLoaded();
                }
            }, DELAY_MILLIS);
        }

        bindViews(v);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillData();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveClicked();
            }
        });

    }

    private void bindViews(View v) {
        etOib = (MaterialEditText) v.findViewById(R.id.et_oib);
        etName = (MaterialEditText) v.findViewById(R.id.et_name);
        etSurname = (MaterialEditText) v.findViewById(R.id.et_surname);
        etUsername = (MaterialEditText) v.findViewById(R.id.et_username);
        etPassword = (MaterialEditText) v.findViewById(R.id.et_password);
        swtLieu = (Switch) v.findViewById(R.id.swt_lieutenant);
        btnSave = (Button) v.findViewById(R.id.btn_save);
    }

    private void fillData() {
        etOib.setText(fireman.getOib());
        etName.setText(fireman.getName());
        etSurname.setText(fireman.getSurname());
        etUsername.setText(fireman.getUsername());
        etPassword.setText(fireman.getPassword());
        if (fireman.isLieutenant()) {
            swtLieu.setActivated(true);
        } else {
            swtLieu.setActivated(false);
        }
    }

    private void onSaveClicked() {
        //TODO request call here to update the member
        fragmentActionListener.memberUpdateFinished();
    }
}
