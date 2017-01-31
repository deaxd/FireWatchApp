package hr.foi.air.webservice.listeners;

/**
 * Created by Denis on 29.1.2017..
 */

public interface StatisticReceivedListener {
    void onStatisticReceived(int numberMembers, int numberInterventions, int numberIntThisYear, double numberIntAvg, int numberVehicles);
}
