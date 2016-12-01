package hr.foi.air.webservice;

import android.content.Intent;

import com.squareup.okhttp.OkHttpClient;

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
    private final String baseUrl = "http://firewatch.esy.es/";
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
                            System.out.println("Login ok");

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
}
