package foi.hr.firewatchapp;

/**
 * Created by Matija on 11/11/2016.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private static EditText username;
    private static EditText password;
    private static Button login_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginButton();
    }

    public void LoginButton() {
        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        login_button = (Button) findViewById(R.id.btn_login);

        
        login_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO provjeravati podatke iz baze i zamjeniti dummy provjeru
                        if (username.getText().toString().equals("korisnik") &
                                password.getText().toString().equals("lozinka")) {
                            Toast.makeText(Login.this, "Korisničko ime i lozinka su ispravni",
                                    Toast.LENGTH_SHORT).show();
                            //TODO preusmjeravati na main activity

                        } else {
                            Toast.makeText(Login.this, "Korisničko ime i lozinka su neispravni",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}