package com.example.nativeapplication;

import com.example.nativeapplication.model.TimeSlot;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* A singleton class */
public class ApiManager {
    private static ApiService apiService;
    private static ApiManager apiManager;

    public interface ApiCallback<T> {
        void onSuccess(T response);
        void onFailure(Throwable t);
    }

    private ApiManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://snap-app.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static ApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new ApiManager();
        }
        return apiManager;
    }

    public void getTimeSlotsInRange(final ApiCallback<List<TimeSlot>> callback, String startDate, String endDate, Integer serviceProfessionalId) {
        Call<List<TimeSlot>> call = apiService.getTimeSlotsInRange(startDate, endDate, serviceProfessionalId);
        call.enqueue(new Callback<List<TimeSlot>>() {
            @Override
            public void onResponse(Call<List<TimeSlot>> call, Response<List<TimeSlot>> response) {
                if (response.isSuccessful()) {
                    List<TimeSlot> timeSlots = response.body();
                    callback.onSuccess(timeSlots);
                } else {
                    callback.onFailure(new Exception(response.message()));
                }
            }

            @Override
            public void onFailure(Call<List<TimeSlot>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
