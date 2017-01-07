package hr.foi.air.webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.squareup.okhttp.OkHttpClient;

import hr.foi.air.database.database.entities.Intervention;
import hr.foi.air.database.database.entities.User;
import hr.foi.air.webservice.Responses.InterventionResponse;
import hr.foi.air.webservice.Responses.LoginResponse;
import hr.foi.air.webservice.Responses.MembersResponse;
import hr.foi.air.webservice.listeners.MembersReceivedListener;
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

    WebServiceHandler webServiceHandler;

    private final String baseUrl = "http://firewatch.esy.es/";

    private WebService webService;

    public WebServiceCaller(WebServiceHandler webServiceHandler) {
        this.webServiceHandler = webServiceHandler;
    }

    public WebServiceCaller() {

        OkHttpClient client = new OkHttpClient();

        this.retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build();

        webService = retrofit.create(WebService.class);
    }

    public void login(String username, String password) {
        Call<LoginResponse> call = webService.login(username, password);

        if (call != null) {
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(retrofit.Response<LoginResponse> response, Retrofit retrofit) {
                    try {
                        if (response.isSuccess()) {
                            handleLogin(response);

                        } else {
                            System.out.println("Wrong operation");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();

                }
            });
        }
    }

    private void handleLogin(retrofit.Response<LoginResponse> response) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

        if (response.body().getValid()) {

            User user = new User();

            user.setUserOib(gson.fromJson(response.body().getUser().getUserOib(), String.class));
            user.setUserName(gson.fromJson(response.body().getUser().getUserName(), String.class));
            user.setUserSurname(gson.fromJson(response.body().getUser().getUserSurname(), String.class));
            user.setUserUsername(gson.fromJson(response.body().getUser().getUserUsername(), String.class));
            user.setUserPassword(gson.fromJson(response.body().getUser().getUserPassword(), String.class));
            user.setUserOrganization(gson.fromJson(response.body().getUser().getUserOrganization(), String.class));
            user.setUserLieutenant(gson.fromJson(response.body().getUser().getUserLieutenant(), String.class));
            user.save();

            if (webServiceHandler != null) {
                webServiceHandler.onDataArrived(user, true);
            }

        }

    }


    public void getInterventions(String oib) {
        Call<InterventionResponse> call = webService.getInterventions(oib);

        if (call != null) {
            call.enqueue(new Callback<InterventionResponse>() {
                @Override
                public void onResponse(retrofit.Response<InterventionResponse> response, Retrofit retrofit) {
                    try {
                        if (response.isSuccess()) {
                            handleInterventions(response);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    public void handleInterventions(retrofit.Response<InterventionResponse> response) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

        Intervention[] interventionList = gson.fromJson(response.body().getIntervention().toString(), Intervention[].class);
        System.out.println(response.body().getIntervention());

        if (webServiceHandler != null) {
            webServiceHandler.onDataArrived(interventionList, true);
        }
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
}
