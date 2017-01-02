package foi.hr.firewatchapp;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.database.database.entities.User;
import hr.foi.air.webservice.WebServiceCaller;

public class LoginActivity extends AppCompatActivity {

    private static EditText username;

    private static EditText password;

    private static Button login_button;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());

        long login;
        login = SQLite.select().from(User.class).query().getCount();
        //zbog lakšeg provjeravanje aplikacije logira se direktno
        if (login > 0) {
            //startActivity(new Intent(getBaseContext(), MainActivity.class));
        }

        //startActivity(new Intent(getBaseContext(), MainActivity.class));
        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        login_button = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebServiceCaller webServiceCaller = new WebServiceCaller();
                webServiceCaller.login(username.getText().toString(), password.getText().toString());
                long login;
                login = SQLite.select().from(User.class).query().getCount();
                if(login >0)  startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), RegistrationActivity.class));
            }
        });

    }
}
