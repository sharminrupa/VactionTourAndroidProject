package com.rajendra.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetailsActivity extends AppCompatActivity {

    Button btntrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        btntrip = findViewById(R.id.buttontrip);
        btntrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inserttrip();
            }
        });

    }

    public void inserttrip() {

        Intent i = new Intent(this, TripBookActivity.class);
        startActivity(i);
    }


}
