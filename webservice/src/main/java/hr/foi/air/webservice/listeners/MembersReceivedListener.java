package hr.foi.air.webservice.listeners;


import java.util.List;

import hr.foi.air.database.database.entities.Fireman;

public interface MembersReceivedListener {

    void onMembersFetched(List<Fireman> firemanList);
    void onError(String error);

}
