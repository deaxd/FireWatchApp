package hr.foi.air.webservice;

import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import hr.foi.air.database.database.entities.User;
import hr.foi.air.webservice.Responses.LoginResponse;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Denis on 30.11.2016..
 */

public class WebServiceCaller {

    Retrofit retrofit;

    WebServiceHandler webServiceHandler;
    private final String baseUrl = "http://firewatch.esy.es/";

    public WebServiceCaller(WebServiceHandler webServiceHandler) {
        this.webServiceHandler = webServiceHandler;
    }

    public WebServiceCaller() {

        OkHttpClient client = new OkHttpClient();

        this.retrofit = new Retrofit.Builder().baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
            .client(client).build();
}

    public void login(String username, String password) {
        WebService service = retrofit.create(WebService.class);
        Call<LoginResponse> call = service.login(username, password);

        if(call != null){
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(retrofit.Response<LoginResponse> response, Retrofit retrofit) {
                    try {
                        if (response.isSuccess()) {
                            handleLogin(response);

                        }
                        else{
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

        if (response.body().getValid()){

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

}
