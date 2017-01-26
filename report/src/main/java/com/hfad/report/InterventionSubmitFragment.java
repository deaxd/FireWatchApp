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

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.rengwuxian.materialedittext.MaterialEditText;



import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import hr.foi.air.database.database.entities.Intervention;
import hr.foi.air.webservice.Responses.NewInterventionRequest;
import hr.foi.air.webservice.WebServiceCaller;
import hr.foi.air.webservice.listeners.InterventionClickListener;


public class InterventionSubmitFragment extends Fragment {

    protected LocationManager locationManager;

    private MaterialEditText kindOfInt;
    private MaterialEditText adress;
    private String initTime =getDateTime();
    private MaterialEditText duration;
    private MaterialEditText description;
    private MaterialEditText members;
    boolean isGPSenabled;
    boolean isNETWORKenabled;
    Location location;
    double latitude;
    double longitude;

    private Button btnSave;



    Context mcontext= getContext();


    private InterventionClickListener interventionClickListener;

    public InterventionSubmitFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        interventionClickListener = (InterventionClickListener) context;
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

        Intervention intervention = SQLite.select().from(Intervention.class).querySingle();


        WebServiceCaller webServiceCaller = new WebServiceCaller();
        webServiceCaller.insertIntervention(getKindOfIntervention().toString()
                ,.getText().toString(), etName.getText().toString(),
                etSurname.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString(), checked);



        return v;


    }

    private void bindViews(View v) {
       kindOfInt = (MaterialEditText) v.findViewById(R.id.mt_kindoint);
        adress = (MaterialEditText) v.findViewById(R.id.mt_adress);
        duration = (MaterialEditText) v.findViewById(R.id.mt_duration);
        description = (MaterialEditText) v.findViewById(R.id.mt_description);
        members = (MaterialEditText) v.findViewById(R.id.mt_members);
    }

    private void onSaveClicked() {
        validateInput();
        swapLayouts();
    }



    private void validateInput(){
        NewInterventionRequest nir = new NewInterventionRequest();

        if (!TextUtils.isEmpty(kindOfInt.getText())) {
            nir.setKindOfInt(kindOfInt.getText().toString());
        }

        if (!TextUtils.isEmpty(adress.getText())) {
            nir.setAdress(adress.getText().toString());
        }

        if (!TextUtils.isEmpty(duration.getText())) {
            nir.setDuration(duration.getText().toString());
        }

        if (!TextUtils.isEmpty(description.getText())) {
            nir.setDescription(description.getText().toString());
        }

        if (!TextUtils.isEmpty(members.getText())) {
            nir.setMembers(members.getText().toString());





            nir.setLatitude(latitude);
            nir.setLongitude(longitude);
            nir.setInitTime(initTime);

        }
    }


    private void geoLoc(){
        isGPSenabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNETWORKenabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (isGPSenabled || isNETWORKenabled) {
            if (ActivityCompat.checkSelfPermission(mcontext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mcontext, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
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
             interventionClickListener.onNewInterventionAdded();
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
