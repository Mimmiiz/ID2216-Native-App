package com.example.nativeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PostYourBusiness extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_your_business);

        //Post you business spinner shows list of services to select
        Spinner spinner = findViewById(R.id.serviceSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.service_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Validation check to handel filling of mandatory fields and highlighting them
        Button myButton = findViewById(R.id.button2);
        myButton.setOnClickListener(v -> {
            EditText eT1 = findViewById(R.id.textView2);
            EditText eT2 = findViewById(R.id.textView6);
            EditText eT3 = findViewById(R.id.textView12);
            EditText eT4 = findViewById(R.id.textView13);
            EditText eT5 = findViewById(R.id.textView15);
            EditText eT6 = findViewById(R.id.textView16);
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
                Intent i = new Intent(PostYourBusiness.this, OrderConfirmation.class);
                startActivity(i);
            }
        });

    }
}