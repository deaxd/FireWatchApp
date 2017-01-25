package foi.hr.firewatchapp.helper;

import hr.foi.air.database.database.entities.Fireman;
import hr.foi.air.database.database.entities.Intervention;
import hr.foi.air.database.database.entities.Organization;
import hr.foi.air.database.database.entities.User;

/**
 * Created by Denis on 18.11.2016..
 */

public class MockData {

    public static void writeAll() {

        User user = new User();
        user.setUserOib("12345678910");
        user.setUserUsername("dsafaric");
        user.setUserPassword("lozinka1");
        user.save();
    }
}
