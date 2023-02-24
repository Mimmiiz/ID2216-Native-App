package com.example.nativeapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Checkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Bundle extras = getIntent().getExtras();
        String[] leftPage = {"Details:", "", extras.getString("service"), "Time"+extras.getString("bookedTime"), "", "", "", "", "Total:"};
        String[] rightPage = {"Contact information:", "", extras.getString("name"), extras.getString("phoneNumber"), "", "", "", "", extras.getString("price")};

        ConstraintLayout c_layout = (ConstraintLayout) findViewById(R.id.mainConstraint);

        TextView description = findViewById(R.id.textView1);
        populate(description, leftPage);
        TextView description1 = findViewById(R.id.textView9);
        populate(description1, rightPage);

        Button checkoutBtn = findViewById(R.id.confbutton);
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate(c_layout))
                    startActivity(new Intent(Checkout.this, OrderConfirmation.class));
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
                et.setBackgroundResource(R.drawable.edittextbackgrounderror);
                validation = false;
            }

        return validation;
    }

    private void populate(TextView description, String[] lines) {
        description.setText(String.join("\n", lines));
    }
}