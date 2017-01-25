package hr.foi.air.webservice;

import hr.foi.air.webservice.Responses.AddResponse;
import hr.foi.air.webservice.Responses.InterventionResponse;
import hr.foi.air.webservice.Responses.LoginResponse;
import hr.foi.air.webservice.Responses.MembersResponse;
import hr.foi.air.webservice.Responses.OrganizationResponse;
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

    @FormUrlEncoded
    @POST("getMembers.php")
    Call<MembersResponse> getMembers(@Field("oib") String oib);

    @FormUrlEncoded
    @POST("getOrganization.php")
    Call<OrganizationResponse> getOrganization(@Field("oib") String oib);

    @FormUrlEncoded
    @POST("updateMember.php")
    Call<AddResponse> updateMember(@Field("oib") String oib, @Field("name") String name, @Field("surname") String surname,
                                   @Field("username") String username, @Field("password") String password, @Field("lieutenant") Boolean lieutenant);

    @FormUrlEncoded
    @POST("insertMember.php")
    Call<AddResponse> insertMember(@Field("userOib") String userOib, @Field("oib") String oib, @Field("name") String name, @Field("surname") String surname,
                                   @Field("username") String username, @Field("password") String password, @Field("lieutenant") Boolean lieutenant);



}