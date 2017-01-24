package hr.foi.air.webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.squareup.okhttp.OkHttpClient;

import hr.foi.air.database.database.entities.User;
import hr.foi.air.webservice.Responses.InterventionResponse;
import hr.foi.air.webservice.Responses.LoginResponse;
import hr.foi.air.webservice.Responses.MembersResponse;
import hr.foi.air.webservice.Responses.OrganizationResponse;
import hr.foi.air.webservice.listeners.InterventionClickListener;
import hr.foi.air.webservice.listeners.LoginListener;
import hr.foi.air.webservice.listeners.MembersReceivedListener;
import hr.foi.air.webservice.listeners.OrganizationReceivedListener;
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
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

    public void getInterventions(String oib, final InterventionClickListener listener) {
        Call<InterventionResponse> call = webService.getInterventions(oib);

        call.enqueue(new Callback<InterventionResponse>() {
            @Override
            public void onResponse(retrofit.Response<InterventionResponse> response, Retrofit retrofit) {
                listener.onInterventionsFetched(response.body().getInterventionList());
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
            public void onFailure(Throwable t) {listener.onError(t.getMessage());}
        });
    }
}
