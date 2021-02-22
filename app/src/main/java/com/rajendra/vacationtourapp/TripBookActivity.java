package com.rajendra.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.rajendra.vacationtourapp.model.TripBook;
import com.rajendra.vacationtourapp.retrofit.RetrofitConfig;
import com.rajendra.vacationtourapp.service.TripBookService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripBookActivity extends AppCompatActivity {
    private Intent i;
    EditText bname;
    EditText bmobile;
    EditText bemail;
    EditText baddress;
    EditText bperson;
    EditText blocation;
    EditText bmessage;
    Context mtx;
    Button CnSubmit;

    TripBookService tripBookService;
   // Button tripSubmit;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_book);
        locationSelect();
        mtx = this;
        tripBookService = RetrofitConfig.createService(TripBookService.class);

        bname = findViewById(R.id.tpbname);
        bmobile = findViewById(R.id.tpbmobile);
        bemail = findViewById(R.id.tpbemail);
        baddress = findViewById(R.id.tpbaddress);
        bperson = findViewById(R.id.tpbperson);
        spinner = findViewById(R.id.tpbselect);
        bmessage = findViewById(R.id.tpbtextarea);
        CnSubmit = findViewById(R.id.cancelSubmit);


        //tripSubmit = findViewById(R.id.tripSubmit);


        bperson.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view , boolean b) {
                System.out.println("sharmin NCLC");
            }
        });

        CnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tripSubmit();
            }


        });

    }

    private void locationSelect() {

        spinner = (Spinner) findViewById(R.id.tpbselect);
        List<String> list = new ArrayList<String>();
        list.add("cox bazar");
        list.add("Sazek valley");
        list.add("Rangamati");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private void tripSubmit() {
        System.out.println("sdshdfhbs");
        i = new Intent(getApplicationContext(), TripBookActivity.class);
        startActivity(i);
    }


    public void tripSubmitInsert(View view){
        String name = bname.getText().toString();
        String mobile = bmobile.getText().toString();
        String email = bemail.getText().toString();
        String address = baddress.getText().toString();
        String person = bperson.getText().toString();

        String message = bmessage.getText().toString();

        TripBook tripBook = new TripBook();

        tripBook.setName(name);
        tripBook.setMobile(mobile);
        tripBook.setEmail(email);
        tripBook.setAddress(address);
        tripBook.setPerson(person);

        tripBook.setMessage(message);

        Call<TripBook> res = tripBookService.addBooking(tripBook);
        res.enqueue(new Callback<TripBook>() {
            @Override
            public void onResponse(Call<TripBook> call, Response<TripBook> response) {
                TripBook user =  response.body();
                startActivity(new Intent(mtx, ViewBookingActivity.class));
            }

            @Override
            public void onFailure(Call<TripBook> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}