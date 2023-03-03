package com.example.nativeapplication.retrofit;

import android.util.Log;

import com.example.nativeapplication.model.ServiceProfessional;
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
    private static ServiceprofessionalApi serviceProfessionalApi;

    public interface ApiCallback<T> {
        void onSuccess(T response);
        void onFailure(Throwable t);
    }

    private ApiManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://snap-app.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        serviceProfessionalApi = retrofit.create(ServiceprofessionalApi.class);
    }

    public static ApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new ApiManager();
        }
        return apiManager;
    }

    public void getTimeSlotsInRange(final ApiCallback<List<TimeSlot>> callback, String date, Integer serviceProfessionalId) {
        Call<List<TimeSlot>> call = apiService.getFreeTimeSlots(date, serviceProfessionalId);
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

    public void getServiceProfessionalFromId(final ApiCallback<ServiceProfessional> callback, Integer id) {
        Call<ServiceProfessional> call = apiService.getServiceProfessionalFromId(id);
        call.enqueue(new Callback<ServiceProfessional>() {
            @Override
            public void onResponse(Call<ServiceProfessional> call, Response<ServiceProfessional> response) {
                if (response.isSuccessful()) {
                    ServiceProfessional serviceProfessionals = response.body();
                    callback.onSuccess(serviceProfessionals);
                } else {
                    callback.onFailure(new Exception(response.message()));
                }
            }

            @Override
            public void onFailure(Call<ServiceProfessional> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }


    public void saveServiceProfessional(final ApiCallback<Object> callback, ServiceProfessional serviceProfessional) {
        Call<Object> call = serviceProfessionalApi.save(serviceProfessional);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.d("RESPONSE", response.code() + " ");
                if (response.code()==200) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception(response.message()));
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
