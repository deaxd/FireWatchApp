package com.hfad.core;

import hr.foi.air.database.database.entities.User;
import hr.foi.air.webservice.WebServiceHandler;

/**
 * Created by Denis on 3.12.2016..
 */

public class WebDataLoader extends DataLoader {
    private boolean userArrived = false;

    @Override
    public void loadData(DataLoadedListener dataLoadedListener){
        super.loadData(dataLoadedListener);

        //WebServiceCaller userWeb = new WebServiceCaller()
    }



    WebServiceHandler userHandler = new WebServiceHandler() {
        @Override
        public void onDataArrived(Object result, boolean ok) {
            if(ok){
                User user = (User) result;
                user.save();
                userArrived = true;
                checkDataArrival();

            }
        }
    };

    private void checkDataArrival(){
        if(userArrived){
            mDataLoadedListener.onUserLoaded((User) user.getUser());
        }
    }
}
