package foi.hr.firewatchapp;

/**
 * Created by Matija on 30/01/2017.
 */


import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import foi.hr.members.MembersActivity;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricTestRunner.class)
public class ClassUnitTest {
    private LoginActivity activity;


    @Before
    public void setup() {

        activity = Robolectric.setupActivity(LoginActivity.class);
    }

   
    @Test
    public void validateButton() {

        Button login = (Button) activity.findViewById(R.id.btn_login);

        assertNotNull("Button could not be found", login);
    }
}