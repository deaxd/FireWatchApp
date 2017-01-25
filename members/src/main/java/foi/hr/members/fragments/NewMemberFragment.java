package foi.hr.members.fragments;


import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.rengwuxian.materialedittext.MaterialEditText;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import foi.hr.members.R;
import foi.hr.members.listeners.FragmentActionListener;
import hr.foi.air.database.database.entities.User;
import hr.foi.air.webservice.Responses.NewMemberRequest;
import hr.foi.air.webservice.WebService;
import hr.foi.air.webservice.WebServiceCaller;

public class NewMemberFragment extends Fragment {

    private MaterialEditText etOib;

    private MaterialEditText etName;

    private MaterialEditText etSurname;

    private MaterialEditText etUsername;

    private MaterialEditText etPassword;

    private Switch swtLieu;

    private Button btnSave;

    private FragmentActionListener fragmentActionListener;

    boolean checked = false;

    public NewMemberFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentActionListener = (FragmentActionListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_member, container, false);
        bindViews(v);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveClicked();
            }
        });

        return v;
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

    private void onSaveClicked() {
        validateInput();
        swapLayouts();

        User user = SQLite.select().from(User.class).querySingle();


        WebServiceCaller webServiceCaller = new WebServiceCaller();
        webServiceCaller.insertMember(user.getUserOib() ,etOib.getText().toString(), etName.getText().toString(),
                etSurname.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString(), checked);


    }

    private void validateInput() {
        NewMemberRequest nmr = new NewMemberRequest();
        if (!TextUtils.isEmpty(etOib.getText()) && etOib.getText().length() == 11) {
            nmr.setOib(etOib.getText().toString());
        }

        if (!TextUtils.isEmpty(etName.getText())) {
            nmr.setName(etName.getText().toString());
        }

        if (!TextUtils.isEmpty(etSurname.getText())) {
            nmr.setSurname(etSurname.getText().toString());
        }

        if (!TextUtils.isEmpty(etUsername.getText())) {
            nmr.setUsername(etUsername.getText().toString());
        }

        if (!TextUtils.isEmpty(etPassword.getText())) {
            nmr.setPassword(etPassword.getText().toString());
        }

        if (swtLieu.isChecked()) {
            nmr.setLieutenant(true);
            checked = true;
        } else {
            nmr.setLieutenant(false);
        }

    }

    private void swapLayouts() {
        //After the request is completed
        fragmentActionListener.newMemberAddingFinished();
    }

}
