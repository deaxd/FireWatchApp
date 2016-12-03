package hr.foi.air.webservice;

import hr.foi.air.webservice.Responses.InterventionResponse;
import hr.foi.air.webservice.Responses.LoginResponse;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Denis on 30.11.2016..
 */

public interface WebService {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("getInterventions.php")
    Call<InterventionResponse> getInterventions(@Field("oib") String oib);
}