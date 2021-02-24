package com.rajendra.vacationtourapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.rajendra.vacationtourapp.adapter.RecentsAdapter;
import com.rajendra.vacationtourapp.adapter.TopPlacesAdapter;
import com.rajendra.vacationtourapp.model.RecentsData;
import com.rajendra.vacationtourapp.model.TopPlacesData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class MainActivity extends AppCompatActivity {

    Button sign;
    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    TopPlacesAdapter topPlacesAdapter;
    SearchView searchView;
    ArrayAdapter<String> adapter;
    ListView myList;
    ArrayList<String> list;
    ImageView navs, Crtetrip, VBooking;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sign = findViewById(R.id.signin);
        searchView = findViewById(R.id.search);
        navs = findViewById(R.id.menu);
        Crtetrip = findViewById(R.id.cretrip);
        VBooking = findViewById(R.id.vBooking);



        Crtetrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Creatrip();
            }
        });

        VBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookingList();
            }


        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }

        });

        // Now here we will add some dummy data in our model class

        List<RecentsData> recentsDataList = new ArrayList<>();
        recentsDataList.add(new RecentsData("AM Lake" , "India" , "From $200" , R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Nilgiri Hills" , "India" , "From $300" , R.drawable.recentimage2));
        recentsDataList.add(new RecentsData("AM Lake" , "India" , "From $200" , R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Nilgiri Hills" , "India" , "From $300" , R.drawable.recentimage2));
        recentsDataList.add(new RecentsData("AM Lake" , "India" , "From $200" , R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Nilgiri Hills" , "India" , "From $300" , R.drawable.recentimage2));

        setRecentRecycler(recentsDataList);

        List<TopPlacesData> topPlacesDataList = new ArrayList<TopPlacesData>();
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill" , "India" , "$200 - $500" , R.drawable.topplaces));
        topPlacesDataList.add(new TopPlacesData("Tajmohol" , "India" , "$200 - $500" , R.drawable.topplaces));
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill" , "India" , "$200 - $500" , R.drawable.topplaces));
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill" , "India" , "$200 - $500" , R.drawable.topplaces));
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill" , "India" , "$200 - $500" , R.drawable.topplaces));

/*
        adapter = new ArrayAdapter<>(this,  android.R.layout.simple_list_item_1);
        myList.setAdapter(adapter);*/


        setTopPlacesRecycler(topPlacesDataList);

       /* searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });*/
    }

    public void BookingList() {
        Intent i = new Intent(this, ViewBookingActivity.class);
        startActivity(i);
    }

    public void insert() {

        Intent intent = new Intent(this , LoginActivity.class);
        startActivity(intent);
    }


    public void Creatrip() {

        Intent intent = new Intent(this , CreateTripActivity.class);
        startActivity(intent);
    }


    private void setRecentRecycler(List<RecentsData> recentsDataList) {

        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this , RecyclerView.HORIZONTAL , false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this , recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);

    }

    private void setTopPlacesRecycler(List<TopPlacesData> topPlacesDataList) {

        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this , RecyclerView.VERTICAL , false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new TopPlacesAdapter(this , topPlacesDataList);
        topPlacesRecycler.setAdapter(topPlacesAdapter);
    }




    // Hi all, today we are going to make a holiday tour app.
    // so lets see i will show you what we are going to build
    // so lets get started.
    // before getting started make sure to subscribe and hit the bell i con to get notified
    // everytime i post a video.
    // lets first import image assets
    // Now we will add a recycler view for recents data.
    // lets make a model class first.
    // now we create a adapter class for holding data
    // our adapter class is ready now we will setup recyclerview

    // So we have setup recents items recyclerview
    //Now we do same setup for top places
    // lets do it fast.
    // Now i will add a bottom navigation
    // now we will set the details activity
    // when user click on any of places details activity will open
    // So this app is done.
    // Please like share and subscribe
    // if any question plz do comment
    // Thanks for watching see you in the next tutorial


}
