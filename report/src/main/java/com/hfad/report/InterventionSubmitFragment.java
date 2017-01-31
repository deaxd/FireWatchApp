package com.hfad.report;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hfad.report.listeners.InterventionAddedListener;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.rengwuxian.materialedittext.MaterialEditText;



import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import hr.foi.air.database.database.entities.User;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.listeners.InterventionClickListener;


public class InterventionSubmitFragment extends Fragment {



    private MaterialEditText kindOfInt;
    private MaterialEditText adress;
    private String initTime =getDateTime();
    private MaterialEditText duration;
    private MaterialEditText description;
    private MaterialEditText members;
    private MaterialEditText alertNumber;
    boolean isGPSenabled=false;
    boolean isNETWORKenabled=false;
    Location location;
    double latitude;
    double longitude;

    private Button btnSave;



    Context mcontext= getContext();

    protected LocationManager locationManager;

    private InterventionClickListener interventionClickListener;
    private InterventionAddedListener interventionAddedListener;

    public InterventionSubmitFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interventionClickListener = (InterventionClickListener) context;
        interventionAddedListener = (InterventionAddedListener) context;


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_intervention_submit, container, false);
        bindViews(v);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveClicked();
            }
        });

        geoLoc();


        return v;

    }

    private void bindViews(View v) {
       kindOfInt = (MaterialEditText) v.findViewById(R.id.mt_kindoint);
        adress = (MaterialEditText) v.findViewById(R.id.mt_adress);
        duration = (MaterialEditText) v.findViewById(R.id.mt_duration);
        description = (MaterialEditText) v.findViewById(R.id.mt_description);
        members = (MaterialEditText) v.findViewById(R.id.mt_members);
        alertNumber= (MaterialEditText) v.findViewById(R.id.mt_alertNumber);
        btnSave = (Button) v.findViewById(R.id.btn_save);
    }

    private void onSaveClicked() {
        if (validateInput()) {
            swapLayouts();

            User user = SQLite.select().from(User.class).querySingle();


            WebServiceCaller webServiceCaller = new WebServiceCaller();
            webServiceCaller.insertIntervention(user.getUserOib(), alertNumber.getText().toString(), kindOfInt.getText().toString(), adress.getText().toString(),
                    initTime, duration.getText().toString(), description.getText().toString(), latitude, longitude, members.getText().toString());
        }
    }



    private boolean validateInput(){
        NewInterventionRequest nir = new NewInterventionRequest();

        if (!TextUtils.isEmpty(kindOfInt.getText()) && kindOfInt.getText().length()!=0) {
            nir.setKindOfInt(kindOfInt.getText().toString());
        } else {
            Toast toast= Toast.makeText(getContext(),"Niste unijeli vrstu intervencije",Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        if (!TextUtils.isEmpty(adress.getText()) && adress.getText().length()!=0) {
            nir.setAdress(adress.getText().toString());
        }
        else {
            Toast toast= Toast.makeText(getContext(),"Niste unijeli adresu intervencije",Toast.LENGTH_LONG);
            toast.show();
            return false;
        }


        if (!TextUtils.isEmpty(duration.getText()) && duration.getText().length()!=0) {

            try{
                Integer.parseInt(duration.getText().toString());
                nir.setDuration(duration.getText().toString());
            }
                catch (NumberFormatException e){
                    Toast toast = Toast.makeText(getContext(), "Niste unijeli ispravno trajanje intervencije", Toast.LENGTH_LONG);
                    toast.show();
                    return false;
                }

        }    else {
            Toast toast = Toast.makeText(getContext(), "Niste unijeli trajanje intervencije", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        if (!TextUtils.isEmpty(description.getText())&& description.getText().length()!=0) {
            nir.setDescription(description.getText().toString());
        }   else {
            Toast toast = Toast.makeText(getContext(), "Niste unijeli opis intervencije", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        if (!TextUtils.isEmpty(members.getText()) && members.getText().length()!=0) {
            nir.setMembers(members.getText().toString());
        } else {
            Toast toast = Toast.makeText(getContext(), "Niste unijeli članove koji su sudjelovali na intervenciji", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }

        if (!TextUtils.isEmpty(alertNumber.getText())&& alertNumber.getText().length()!=0) {

            try{
                Integer.parseInt(alertNumber.getText().toString());
                nir.setMembers(alertNumber.getText().toString());
            } catch (NumberFormatException e) {
                Toast toast = Toast.makeText(getContext(), "Niste unijeli ispravan broj intervencije", Toast.LENGTH_LONG);
                toast.show();
                return false;
            }
            } else {
            Toast toast = Toast.makeText(getContext(), "Niste unijelibroj intervencije", Toast.LENGTH_LONG);
            toast.show();
            return false;
            }





            nir.setLatitude(latitude);
            nir.setLongitude(longitude);
            nir.setInitTime(initTime);

        return true;
    }


    private void geoLoc(){
        //isGPSenabled =locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //isNETWORKenabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (isGPSenabled || isNETWORKenabled) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                Toast.makeText(mcontext,"Prvo uključite GPS ili mrežni promet",Toast.LENGTH_LONG).show();

            }
            if(isGPSenabled){
                location = locationManager
                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
            else if (isNETWORKenabled){
                location = locationManager
                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
        }
        else {
            latitude=0.0;
            longitude=0.0;
        }
    }


         private void swapLayouts(){
            interventionAddedListener.onNewInterventionAdded();
         }






    @Override
    public void onDetach() {
        super.onDetach();
        interventionClickListener = null;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
