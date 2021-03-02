package com.rajendra.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.rajendra.vacationtourapp.adapter.BookingViewAdapter;
import com.rajendra.vacationtourapp.adapter.CreateTripAdapter;
import com.rajendra.vacationtourapp.model.CreateTrip;
import com.rajendra.vacationtourapp.model.TripBook;
import com.rajendra.vacationtourapp.retrofit.RetrofitConfig;
import com.rajendra.vacationtourapp.service.CreateTripService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllTripActivity extends AppCompatActivity {

    CreateTripService createTripService;
    ListView listViewtrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_trip);

        //Image slider start
      /*  ImageSlider imageSlider = findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://blog.flyticket.com.bd/wp-content/uploads/2019/06/st-martin-island-1-1.jpg"));
        slideModels.add(new SlideModel("https://miro.medium.com/max/640/1*CQZBw5UhPbelehPeJ1TP9w.jpeg"));
        slideModels.add(new SlideModel("https://nijhoom.com/wp-content/uploads/2019/06/puthia-gobinda-temple-rajshahi-bangladesh-768-o.jpg"));
        imageSlider.setImageList(slideModels , true);*/
        //Image slider end

        createTripService = RetrofitConfig.createService(CreateTripService.class);
        listViewtrip = findViewById(R.id.creatTriplist);



        Call<List<CreateTrip>> tvlist = createTripService.getAll();
        tvlist.enqueue(new Callback<List<CreateTrip>>() {
            @Override
            public void onResponse(Call<List<CreateTrip>> call , Response<List<CreateTrip>> response) {
                List<CreateTrip> ctList = response.body();
                if(ctList != null && ctList.size() > 0){
                    viewAllTrip(ctList);
                }else{
                    System.out.println("Booking not found");
                }
            }




            @Override
            public void onFailure(Call<List<CreateTrip>> call , Throwable t) {

            }
        });



    }




    public void viewAllTrip(List<CreateTrip> ctList) {
        CreateTripAdapter createTripAdapter = new CreateTripAdapter(this, ctList);
        listViewtrip.setAdapter(createTripAdapter);
    }

}