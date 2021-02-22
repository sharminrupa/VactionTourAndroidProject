package com.rajendra.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rajendra.vacationtourapp.model.TripBook;
import com.rajendra.vacationtourapp.retrofit.RetrofitConfig;
import com.rajendra.vacationtourapp.service.TripBookService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTripBookingActivity extends AppCompatActivity {

    TripBookService tripBookService;
    EditText eid;
    EditText ename;
    EditText emobile;
    EditText eemail;
    EditText eaddress;
    EditText eperson;
    EditText elocation;
    EditText emessage;
    Context mtx;
    Intent i;

    Button etCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip_booking);
        tripBookService = RetrofitConfig.createService(TripBookService.class);
        mtx = this;

        eid = findViewById(R.id.teid);
        ename = findViewById(R.id.tename);
        emobile = findViewById(R.id.temobile);
        eemail = findViewById(R.id.teemail);
        eaddress = findViewById(R.id.teaddress);
        eperson = findViewById(R.id.teperson);
       // elocation = findViewById(R.id.teselect);
        emessage = findViewById(R.id.temessage);
        etCancel = findViewById(R.id.cancelEdit);

        etCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editCancel();
            }

        });


        i = getIntent();
        long id = i.getLongExtra("id", 0);

        Call<TripBook> tebook = tripBookService.getById(id);
        tebook.enqueue(new Callback<TripBook>() {
            @Override
            public void onResponse(Call<TripBook> call , Response<TripBook> response) {
                TripBook tebook = response.body();
                eid.setText(String.valueOf(tebook.getId()));
                ename.setText(tebook.getName());
                emobile.setText(tebook.getMobile());
                eemail.setText(tebook.getEmail());
                eaddress.setText(tebook.getAddress());
                eperson.setText(tebook.getPerson());
                //elocation.setText(tebook.getLocation());
                emessage.setText(tebook.getMessage());
            }

            @Override
            public void onFailure(Call<TripBook> call , Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void update(View view){

        String etid= eid.getText().toString();
        String etname = ename.getText().toString();
        String etmobile = emobile.getText().toString();
        String etemail = eemail.getText().toString();
        String etaddress = eaddress.getText().toString();
        String etperson = eperson.getText().toString();
        String etmessage = emessage.getText().toString();
        TripBook tripBook = new TripBook();
        tripBook.setId(Long.parseLong(etid));
        tripBook.setName(etname);
        tripBook.setMobile(etmobile);
        tripBook.setEmail(etemail);
        tripBook.setAddress(etaddress);
        tripBook.setPerson(etperson);
        tripBook.setMessage(etmessage);
        Call<TripBook> tebook = tripBookService.addBooking(tripBook);
        tebook.enqueue(new Callback<TripBook>() {
            @Override
            public void onResponse(Call<TripBook> call , Response<TripBook> response) {
                TripBook tebook = response.body();
                startActivity(new Intent(mtx, ViewBookingActivity.class));
            }

            @Override
            public void onFailure(Call<TripBook> call , Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void editCancel() {
        Intent i = new Intent(this, ViewBookingActivity.class);
        startActivity(i);
    }

}