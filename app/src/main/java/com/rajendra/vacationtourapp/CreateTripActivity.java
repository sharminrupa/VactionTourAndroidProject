package com.rajendra.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rajendra.vacationtourapp.model.CreateTrip;
import com.rajendra.vacationtourapp.service.CreateTripService;
import com.rajendra.vacationtourapp.service.UserService;

public class CreateTripActivity extends AppCompatActivity {

    private Intent i;
    EditText sname;
    EditText smobile;
    EditText semail;
    EditText spass;
    EditText sconpass;
    Context mtx;
    CreateTripService createTripService;


    Button CnButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);

        CnButton = findViewById(R.id.CreateCancel);

        CnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CancelTrip();
            }

        });


    }

    private void CancelTrip() {
        Intent intent = new Intent(this , CreateTripActivity.class);
        startActivity(intent);
    }


}