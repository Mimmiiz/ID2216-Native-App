package com.example.nativeapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Checkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        ConstraintLayout c_layout = (ConstraintLayout) findViewById(R.id.mainConstraint);


        Button checkoutBtn = findViewById(R.id.confbutton);
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate(c_layout)) {
                    Intent i = new Intent(Checkout.this, OrderConfirmation.class);
                    //i.putExtra("type", btn1.getText());

                    startActivity(i);
                }

            }
        });

    }

    private boolean validate(ConstraintLayout layout) {
        ArrayList<EditText> editTextList = new ArrayList<EditText>();

        boolean validation = true;

        for (int i=0; i<layout.getChildCount(); i++ )
            if (layout.getChildAt(i) instanceof EditText)
                editTextList.add((EditText) layout.getChildAt(i));

        for (EditText et : editTextList)
            if (et.getText().toString().isEmpty()) {
                et.setBackgroundResource(R.drawable.editTextBackgroundError);
                validation = false;
            }

        return validation;
    }
}