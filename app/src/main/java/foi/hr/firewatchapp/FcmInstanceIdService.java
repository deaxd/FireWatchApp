package foi.hr.firewatchapp;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Matija on 24/01/2017.
 */

/**
 * Class used for getting firebase token
 */

public class FcmInstanceIdService extends FirebaseInstanceIdService {
    /**
     * Method that saves token into shared preferences when token is refreshed
     */
    @Override
    public void onTokenRefresh() {

        String recent_token =FirebaseInstanceId.getInstance().getToken();
        android.preference.PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                .edit()
                .putString(getString(R.string.FCM_TOKEN), recent_token)
                .commit();
        //SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor= sharedPreferences.edit();
        //editor.putString(getString(R.string.FCM_TOKEN),recent_token);
        //editor.commit();
    }
}
