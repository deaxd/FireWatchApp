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
import hr.foi.air.webservice.listeners.LoginListener;

/**
 * Activity used for initial login to the application
 */
public class LoginActivity extends AppCompatActivity implements LoginListener{

    private static EditText username;

    private static EditText password;

    LoginListener listenerLogin;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());


        /**
         *  long login is used to check if user is already registered in local database and grants him access to application if is
         */
        long login;
        login = SQLite.select().from(User.class).query().getCount();
        if (login > 0) {
            startActivity(new Intent(getBaseContext(), MainActivity.class));
        }

        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);

        listenerLogin = this;


        /**
         * Login button listener
         */
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebServiceCaller webServiceCaller = new WebServiceCaller();
                webServiceCaller.login(username.getText().toString(), password.getText().toString(), listenerLogin );

            }
        });

    }

    /**
     * Interface method that receives user data and saves user to the local database
     * @param user
     */
    @Override
    public void onLogin(User user) {

        if(user.getUserUsername() == null)
        {}
        else if (user.getUserUsername().equals(username.getText().toString())) {

            user.save();
            startActivity(new Intent(getBaseContext(), MainActivity.class));
        }
    }
}
