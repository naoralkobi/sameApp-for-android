package com.example.sameapp.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebServiceAPI {
    @GET("api/contacts")
    Call<List<apiContact>> getContacts(@Query("username") String username);

    @GET("api/users")
    Call<List<apiUser>> getUsers();

    @GET("api/contacts/{id}/messages")
    Call<List<apiMessage>> getMessages(@Path("id") String id, @Query("username") String username);

    @Headers("Content-Type: application/json")
    @POST("api/contacts")
    Call<Void> createContact(@Body apiContact contact);

    @Headers("Content-Type: application/json")
    @POST("api/users")
    Call<Void> createUser(@Body apiUser user);

    @Headers("Content-Type: application/json")
    @POST("api/Invitations")
    Call<Void> invitationUser(@Body apiInvitation apiInvitation);

    @Headers("Content-Type: application/json")
    @POST("api/users/login")
    Call<Void> login(@Body apiUser apiUser);

    @Headers("Content-Type: application/json")
    @POST("api/contacts/{id}/messages")
    Call<Void> createMessage(@Body apiMessage message, @Path("id") String id);

    @Headers("Content-Type: application/json")
    @POST("api/transfer")
    Call<Void> transferMessage(@Body apiTransfer apiTransfer);

    @Headers("Content-Type: application/json")
    @POST("api/users/token")
    Call<Void> sendToken(@Query("username") String username, @Body String token);

    @DELETE("contacts/{id}")
    Call<Void> deleteContact(@Path("id") int id);
}