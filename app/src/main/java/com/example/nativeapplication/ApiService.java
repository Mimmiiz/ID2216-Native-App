package com.example.nativeapplication;

import com.example.nativeapplication.model.TimeSlot;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    @Headers("Accept-Language: en-SE") // needed to add this otherwise request returns 400 Bad Request
    @GET("timeSlots")
    Call<List<TimeSlot>> getTimeSlotsInRange(@Query("startDate") String startDate,
                                             @Query("endDate") String endDate,
                                             @Query("serviceProfessionalId") Integer serviceProfessionalId);
}