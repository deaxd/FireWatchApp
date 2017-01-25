package hr.foi.air.webservice.listeners;

import hr.foi.air.database.database.entities.Organization;

/**
 * Created by Denis on 19.1.2017..
 */

public interface OrganizationReceivedListener {

    void onOrganizationFetched(Organization organization);
}
