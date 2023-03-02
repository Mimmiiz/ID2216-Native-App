package com.example.nativeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nativeapplication.model.ServiceProfessional;
import com.example.nativeapplication.retrofit.RetrofitService;
import com.example.nativeapplication.retrofit.ServiceprofessionalApi;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostYourBusiness extends AppCompatActivity {
    String text7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_your_business);

        //Post you business spinner shows list of services to select
        Spinner spinner = findViewById(R.id.serviceSpinner);
//        String text7 = spinner.getSelectedItem().toString();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.service_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text7 = parent.getItemAtPosition(position).toString();
                System.out.println(text7);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("Please select an item");
            }

        });

        RetrofitService retrofitService = new RetrofitService();
        ServiceprofessionalApi serviceprofessionalApi = retrofitService.getRetrofit().create(ServiceprofessionalApi.class);

        //Validation check to handel filling of mandatory fields and highlighting them
        Button myButton = findViewById(R.id.button2);
        myButton.setOnClickListener(v -> {
            EditText eT1 = findViewById(R.id.textView2);
            String text1 = eT1.getText().toString();
            EditText eT2 = findViewById(R.id.textView6);
            String text2 = eT2.getText().toString();
            EditText eT3 = findViewById(R.id.textView12);
            String text3 = eT3.getText().toString();
            EditText eT4 = findViewById(R.id.textView13);
            String text4 = eT4.getText().toString();
            EditText eT5 = findViewById(R.id.textView15);
            String text5 = eT5.getText().toString();
            EditText eT6 = findViewById(R.id.textView16);
            String text6 = eT6.getText().toString();
            if (eT1.getText().toString().isEmpty() || eT2.getText().toString().isEmpty() ||
                eT3.getText().toString().isEmpty() || eT4.getText().toString().isEmpty() ||
                eT5.getText().toString().isEmpty() || eT6.getText().toString().isEmpty()) {
                Toast.makeText(PostYourBusiness.this, "Please fill in all mandatory fields.", Toast.LENGTH_SHORT).show();
                if (eT1.getText().toString().isEmpty()) {
                    eT1.setBackgroundResource(R.drawable.edittext_mandatory);
                } else {
                    eT1.setBackgroundResource(R.drawable.edittext_filled);
                }
                if (eT2.getText().toString().isEmpty()) {
                    eT2.setBackgroundResource(R.drawable.edittext_mandatory);
                } else {
                    eT2.setBackgroundResource(R.drawable.edittext_filled);
                }
                if (eT3.getText().toString().isEmpty()) {
                    eT3.setBackgroundResource(R.drawable.edittext_mandatory);
                } else {
                    eT3.setBackgroundResource(R.drawable.edittext_filled);
                }
                if (eT4.getText().toString().isEmpty()) {
                    eT4.setBackgroundResource(R.drawable.edittext_mandatory);
                } else {
                    eT4.setBackgroundResource(R.drawable.edittext_filled);
                }
                if (eT5.getText().toString().isEmpty()) {
                    eT5.setBackgroundResource(R.drawable.edittext_mandatory);
                } else {
                    eT5.setBackgroundResource(R.drawable.edittext_filled);
                }
                if (eT6.getText().toString().isEmpty()) {
                    eT6.setBackgroundResource(R.drawable.edittext_mandatory);
                } else {
                    eT6.setBackgroundResource(R.drawable.edittext_filled);
                }
            } else {
                //Functionality to redirect the page to the next page - Order confirmation
                ServiceProfessional serviceprofessional = new ServiceProfessional();
                serviceprofessional.setServiceDescription(text1);
                serviceprofessional.setName(text2);
                serviceprofessional.setAddress(text3);
                serviceprofessional.setPhoneNumber(text4);
                serviceprofessional.setEmail(text5);
                serviceprofessional.setPrice(Double.valueOf(text6));
                serviceprofessional.setServiceSubcategory(text7);
//                serviceprofessional.setRating(0f);

                serviceprofessionalApi.save(serviceprofessional)
                        .enqueue(new Callback<ServiceProfessional>() {
                            @Override
                            public void onResponse(Call<ServiceProfessional> call, Response<ServiceProfessional> response) {
//                                Toast.makeText(PostYourBusiness.this, "Service Professional Details Save Successfuly", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(PostYourBusiness.this, OrderConfirmation.class);
                                startActivity(i);
                            }

                            @Override
                            public void onFailure(Call<ServiceProfessional> call, Throwable t) {
                                Toast.makeText(PostYourBusiness.this, "Service Professional Details Save Failed", Toast.LENGTH_SHORT).show();
                                Logger.getLogger(PostYourBusiness.class.getName()).log(Level.SEVERE, "Error Occurred", t);
                            }
                        });
            }
        });

    }
}