package com.rajendra.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.rajendra.vacationtourapp.adapter.BookingViewAdapter;
import com.rajendra.vacationtourapp.model.TripBook;
import com.rajendra.vacationtourapp.retrofit.RetrofitConfig;
import com.rajendra.vacationtourapp.service.TripBookService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewBookingActivity extends AppCompatActivity {
    private Intent i;
    TripBookService tripBookService;
    ListView listViewtripbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_booking);
        tripBookService = RetrofitConfig.createService(TripBookService.class);
        listViewtripbook = findViewById(R.id.trpListView);

        Call<List<TripBook>> tvlist = tripBookService.getAll();
        tvlist.enqueue(new Callback<List<TripBook>>() {
            @Override
            public void onResponse(Call<List<TripBook>> call , Response<List<TripBook>> response) {
                List<TripBook> blist = response.body();
                if(blist != null && blist.size() > 0){
                    showTripList(blist);
                }else{
                    System.out.println("Booking not found");
                }
            }

            @Override
            public void onFailure(Call<List<TripBook>> call , Throwable t) {

            }
        });

    }

    private void showTripList(List<TripBook> list) {
        BookingViewAdapter bookingViewAdapter = new BookingViewAdapter(this, list);
        listViewtripbook.setAdapter(bookingViewAdapter);
    }

}