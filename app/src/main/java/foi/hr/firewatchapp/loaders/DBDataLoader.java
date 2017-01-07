package foi.hr.firewatchapp.loaders;

/**
 * Created by Matija on 24/11/2016.
 */

import com.hfad.core.DataLoadedListener;
import com.hfad.core.DataLoader;

import java.util.ArrayList;

import hr.foi.air.database.database.entities.Intervention;

public class DBDataLoader extends DataLoader {
    @Override
    public void loadData(DataLoadedListener dataLoadedListener){
         super.loadData(dataLoadedListener);
         try {
             interventions=(ArrayList<Intervention>) Intervention.getAll();

             mDataLoadedListener.onInterventionLoaded(interventions);
         } catch (NullPointerException e){
             e.printStackTrace();
         }
     }
}
