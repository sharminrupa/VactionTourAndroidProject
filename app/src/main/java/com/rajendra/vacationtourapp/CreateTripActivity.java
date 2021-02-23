package com.rajendra.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.rajendra.vacationtourapp.model.CreateTrip;
import com.rajendra.vacationtourapp.retrofit.RetrofitConfig;
import com.rajendra.vacationtourapp.service.CreateTripService;
import com.rajendra.vacationtourapp.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTripActivity extends AppCompatActivity {
    final Calendar myCalendar = Calendar.getInstance();
    private Intent i;
    EditText ctlocation;
    EditText ctday;
    EditText ctamount;
    EditText ctrecive;
    EditText ctdate;
    EditText ctback;
    EditText ctdetails;
    Context mtx;

    CreateTripService createTripService;
    Button CnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        mtx = this;
        createTripService = RetrofitConfig.createService(CreateTripService.class);

        ctlocation = findViewById(R.id.clocation);
        ctday = findViewById(R.id.cday);
        ctamount = findViewById(R.id.camount);
        ctrecive = findViewById(R.id.crecive);
        ctdate = findViewById(R.id.cdate);
        ctback = findViewById(R.id.cbacktime);
        ctdetails = findViewById(R.id.cdetails);

        CnButton = findViewById(R.id.CreateCancel);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        ctdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateTripActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

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


    public void createTrip(View view) {

        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        DatePickerDialog picker = new DatePickerDialog(CreateTripActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        ctdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);

        String crlocation = ctlocation.getText().toString();
        String crday = ctday.getText().toString();
        String cramount = ctamount.getText().toString();
        String crrecive = ctrecive.getText().toString();
        //String crdate = ctdate.setOnDateChangedListener().getText().toString();
        String crback = ctback.getText().toString();
        String crdetails = ctdetails.getText().toString();

        CreateTrip crtrip = new CreateTrip();
        crtrip.setLocation(crlocation);
        crtrip.setDay(crday);
        crtrip.setCost(cramount);
        crtrip.setRecivingaddress(crrecive);
        crtrip.setBacktime(crback);
        crtrip.setDetails(crdetails);

        Call<CreateTrip> ct = createTripService.createTrip(crtrip);
        ct.enqueue(new Callback<CreateTrip>() {
            @Override
            public void onResponse(Call<CreateTrip> call , Response<CreateTrip> response) {

                CreateTrip crt = response.body();
                startActivity(new Intent(mtx, ViewAllTripActivity.class));
            }

            @Override
            public void onFailure(Call<CreateTrip> call , Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        ctdate.setText(sdf.format(myCalendar.getTime()));
    }

}

