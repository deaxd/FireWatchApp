package hr.foi.air.webservice.listeners;

import hr.foi.air.database.database.entities.User;

/**
 * Created by Denis on 19.1.2017..
 */

public interface LoginListener {
    void onLogin(User user);
}
