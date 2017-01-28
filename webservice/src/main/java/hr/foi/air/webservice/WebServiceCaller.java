package hr.foi.air.webservice;


import com.squareup.okhttp.OkHttpClient;

import hr.foi.air.database.database.entities.Equipment;
import hr.foi.air.webservice.Responses.EquipmentResponse;
import hr.foi.air.webservice.Responses.InterventionResponse;
import hr.foi.air.webservice.Responses.LoginResponse;
import hr.foi.air.webservice.Responses.MembersResponse;
import hr.foi.air.webservice.Responses.OrganizationResponse;
import hr.foi.air.webservice.Responses.VehiclesResponse;
import hr.foi.air.webservice.listeners.EquipmentReceivedListener;
import hr.foi.air.webservice.listeners.InterventionClickListener;
import hr.foi.air.webservice.listeners.LoginListener;
import hr.foi.air.webservice.listeners.MembersReceivedListener;
import hr.foi.air.webservice.listeners.OrganizationReceivedListener;
import hr.foi.air.webservice.listeners.VehicleReceivedListener;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Denis on 30.11.2016..
 */

public class WebServiceCaller {

    Retrofit retrofit;
    private final String baseUrl = "http://firewatch.esy.es/";
    private WebService webService;


    public WebServiceCaller() {

        OkHttpClient client = new OkHttpClient();

        this.retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build();

        webService = retrofit.create(WebService.class);
    }

    public void login(String username, String password, final LoginListener listener) {
        Call<LoginResponse> call = webService.login(username, password);

        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(retrofit.Response<LoginResponse> response, Retrofit retrofit) {
                listener.onLogin(response.body().getUser());
                System.out.println("2");
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getInterventions(String oib, final InterventionClickListener listener) {
        Call<InterventionResponse> call = webService.getInterventions(oib);

        call.enqueue(new Callback<InterventionResponse>() {
            @Override
            public void onResponse(retrofit.Response<InterventionResponse> response, Retrofit retrofit) {
                listener.onInterventionsFetched(response.body().getInterventionList());
                listener.onNumberOfInterventionFetched(response.body().getNumberOfInterventions());
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onError(t.getMessage());
                }
        });
    }


    public void getMembers(String oib, final MembersReceivedListener listener) {
        Call<MembersResponse> call = webService.getMembers(oib);

        call.enqueue(new Callback<MembersResponse>() {
            @Override
            public void onResponse(Response<MembersResponse> response, Retrofit retrofit) {
                listener.onMembersFetched(response.body().getFiremenList());
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public void getOrganization(String oib, final OrganizationReceivedListener listener){
        Call<OrganizationResponse> call = webService.getOrganization(oib);

        call.enqueue(new Callback<OrganizationResponse>() {
            @Override
            public void onResponse(Response<OrganizationResponse> response, Retrofit retrofit) {
                listener.onOrganizationFetched(response.body().getOrganization());
            }

            @Override
            public void onFailure(Throwable t) {t.printStackTrace();}
        });
    }

    public void updateMember(String oib, String name, String surname, String username, String password, Boolean lieutenant){

        Call<Void> call = webService.updateMember(oib, name, surname, username, password, lieutenant);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {}

            @Override
            public void onFailure(Throwable t) {}
        });
    }

    public void insertMember(String userOib , String oib, String name, String surname, String username, String password, Boolean lieutenant){

        Call<Void> call = webService.insertMember(userOib, oib, name, surname, username, password, lieutenant);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {}

            @Override
            public void onFailure(Throwable t) {}
        });
    }

    public void insertIntervention(String oib, String alertNumber, String kindOfIntervention , String address, String initialTime , String duration, String description, double latitude, double longitude, String members){

        Call<Void> call = webService.insertIntervention(oib, alertNumber, kindOfIntervention, address, initialTime, duration, description, latitude, longitude,members);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {
}
        });
    }

    public void getEquipment(int organizationId, final EquipmentReceivedListener listener) {


        Call<EquipmentResponse> call = webService.getEquipment(organizationId);

        call.enqueue(new Callback<EquipmentResponse>() {
            @Override
            public void onResponse(Response<EquipmentResponse> response, Retrofit retrofit) {
                listener.onEquipmentFetched(response.body().getEquipmentList());
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void insertEquipment(String name, int quantity, int organizationId){

        Call<Void> call = webService.insertEquipment(name, quantity, organizationId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    public void getVehicles(int organizationId, final VehicleReceivedListener listener) {

        Call<VehiclesResponse> call = webService.getVehicles(organizationId);

        call.enqueue(new Callback<VehiclesResponse>() {
            @Override
            public void onResponse(Response<VehiclesResponse> response, Retrofit retrofit) {
                listener.onVehiclesFetched(response.body().getVehicleList());
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void insertVehicle(String name, int seatNumber, int waterVolume, String kindOfVehicle, int organizationId){

        Call<Void> call = webService.insertVehicle(name, seatNumber, waterVolume, kindOfVehicle, organizationId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }




    
}
