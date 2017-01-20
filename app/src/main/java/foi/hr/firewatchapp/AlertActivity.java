package foi.hr.firewatchapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Matija on 19/01/2017.
 */

public class AlertActivity extends AppCompatActivity {

    private static Button alert_button;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);


        final Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyFirebaseMessagingService MFCB = new MyFirebaseMessagingService();

                MFCB.sendNotification("ALERT");
            }


        });

    }
}