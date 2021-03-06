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
import android.widget.Toast;

import foi.hr.members.R;
import foi.hr.members.listeners.FragmentActionListener;
import hr.foi.air.database.database.entities.User;
import foi.hr.members.NewMemberRequest;
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
        if(validateInput()) {
            swapLayouts();

            User user = SQLite.select().from(User.class).querySingle();


            WebServiceCaller webServiceCaller = new WebServiceCaller();
            webServiceCaller.insertMember(user.getUserOib(), etOib.getText().toString(), etName.getText().toString(),
                    etSurname.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString(), checked);
        }
    }

    /**
     * Method used for validating input data. It returns true if input is valid and false if invalid
     * @return
     */
    private boolean validateInput() {
        NewMemberRequest nmr = new NewMemberRequest();
        if (!TextUtils.isEmpty(etOib.getText()) && etOib.getText().length() == 11) {
            nmr.setOib(etOib.getText().toString());
        } else {
            Toast toast= Toast.makeText(getContext(),"Niste unijeli ispravan OIB",Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        if (!TextUtils.isEmpty(etName.getText()) && etName.getText().length()!=0) {
            nmr.setName(etName.getText().toString());
        } else {
            Toast toast = Toast.makeText(getContext(), "Niste unijeli ispravno ime", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        if (!TextUtils.isEmpty(etSurname.getText()) && etSurname.getText().length()!=0) {
            nmr.setSurname(etSurname.getText().toString());
        }   else {
            Toast toast = Toast.makeText(getContext(), "Niste unijeli ispravno prezime", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        if (!TextUtils.isEmpty(etUsername.getText())&& etUsername.getText().length()!=0) {
            nmr.setUsername(etUsername.getText().toString());
        } else {
            Toast toast = Toast.makeText(getContext(), "Niste unijeli ispravno korisničko ime", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        if (!TextUtils.isEmpty(etPassword.getText())&& etPassword.getText().length()!=0) {
            nmr.setPassword(etPassword.getText().toString());
        }   else {
            Toast toast = Toast.makeText(getContext(), "Niste unijeli ispravnu lozinku", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        if (swtLieu.isChecked()) {
            nmr.setLieutenant(true);
            checked = true;
        } else {
            nmr.setLieutenant(false);
        }
        return true;
    }

    /**
     * Method used for swapping layouts when new  member is addded using newMemberAddingFinished() method
     */
    private void swapLayouts() {
        //After the request is completed
        fragmentActionListener.newMemberAddingFinished();
    }

}
