package com.example.nativeapplication;

import com.example.nativeapplication.model.Customer;
import com.example.nativeapplication.model.ServiceProfessional;
import com.example.nativeapplication.model.TimeSlot;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @Headers("Accept-Language: en-SE") // needed to add this otherwise request returns 400 Bad Request
    @GET("freeTimeSlots")
    Call<List<TimeSlot>> getFreeTimeSlots(@Query("date") String date,
                                             @Query("serviceProfessionalId") Integer serviceProfessionalId);

    @GET("serviceProfessionals")
    Call<ServiceProfessional> getServiceProfessionalFromId(@Query("id") Integer id);

    @POST("customers")
    Call<Customer> createCustomer(@Body Customer customer);

}