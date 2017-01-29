package hr.foi.air.webservice;

import javax.xml.transform.sax.SAXResult;

import hr.foi.air.database.database.entities.Vehicle;
import hr.foi.air.webservice.Responses.EquipmentResponse;
import hr.foi.air.webservice.Responses.InterventionResponse;
import hr.foi.air.webservice.Responses.LoginResponse;
import hr.foi.air.webservice.Responses.MembersResponse;
import hr.foi.air.webservice.Responses.OrganizationResponse;
import hr.foi.air.webservice.Responses.StatisticsResponse;
import hr.foi.air.webservice.Responses.VehiclesResponse;
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
    Call<Void> updateMember(@Field("oib") String oib, @Field("name") String name, @Field("surname") String surname,
                                   @Field("username") String username, @Field("password") String password, @Field("lieutenant") Boolean lieutenant);

    @FormUrlEncoded
    @POST("insertMember.php")
    Call<Void> insertMember(@Field("userOib") String userOib, @Field("oib") String oib, @Field("name") String name, @Field("surname") String surname,
                                   @Field("username") String username, @Field("password") String password, @Field("lieutenant") Boolean lieutenant);


    @FormUrlEncoded
    @POST("insertIntervention.php")
    Call<Void> insertIntervention(@Field("oib") String oib, @Field("alertNumber") String alertNumber, @Field("kindOfIntervention") String kindOfIntervention ,
                                  @Field("address") String address, @Field("initialTime") String initialTime , @Field("duration") String duration,
                                  @Field("description") String description, @Field("latitude") double latitude, @Field("longitude") double longitude, @Field("members") String members);

    @FormUrlEncoded
    @POST("getEquipment.php")
    Call<EquipmentResponse> getEquipment(@Field("organizationId") int organizationId);

    @FormUrlEncoded
    @POST("insertEquipment.php")
    Call<Void> insertEquipment (@Field("name") String name, @Field("quantity") int quantity, @Field("organizationId") int organizationId);

    @FormUrlEncoded
    @POST("getVehicles.php")
    Call<VehiclesResponse> getVehicles(@Field("organizationId") int organizationId);

    @FormUrlEncoded
    @POST("insertVehicle.php")
    Call<Void> insertVehicle(@Field("name") String name, @Field("seatNumber") int seatNumber, @Field("waterVolume") int waterVolume,
                             @Field("kindOfVehicle") String kindOfVehicle, @Field("organizationId")int organizationId);

    @FormUrlEncoded
    @POST("getStatistics.php")
    Call<StatisticsResponse> getStatistics(@Field("oib") String oib);




}