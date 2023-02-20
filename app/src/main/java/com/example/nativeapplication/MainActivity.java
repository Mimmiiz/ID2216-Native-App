package com.example.nativeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ib = (Button) findViewById(R.id.button2);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value= "Men's Salon,Women's Salon,SPA & Massage,Facial & Clean-ups";
                Intent i = new Intent(MainActivity.this, ServicesSubcategories.class);
                i.putExtra("category", value);

                startActivity(i);
            }
        });






    }


}