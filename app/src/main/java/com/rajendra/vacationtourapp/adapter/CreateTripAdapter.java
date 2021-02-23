package com.rajendra.vacationtourapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rajendra.vacationtourapp.LoginActivity;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.TripBookActivity;
import com.rajendra.vacationtourapp.TripDetailsActivity;
import com.rajendra.vacationtourapp.model.CreateTrip;
import com.rajendra.vacationtourapp.retrofit.RetrofitConfig;
import com.rajendra.vacationtourapp.service.CreateTripService;

import java.util.List;

public class CreateTripAdapter extends ArrayAdapter<CreateTrip> {
    Activity context;
    List<CreateTrip> list;
    CreateTripService createTripService;
    Button VDetails;

    public CreateTripAdapter(Activity context , List<CreateTrip> crList) {
        super(context, R.layout.create_trip_list, crList);
        createTripService = RetrofitConfig.createService(CreateTripService.class);
        this.context = context;
        this.list = crList;
    }

    @NonNull
    @Override
    public View getView(int position , @Nullable View convertView , @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        CreateTrip crtList = list.get(position);

        View rowView =  inflater.inflate(R.layout.create_trip_list, null, true);
        TextView limg = rowView.findViewById(R.id.loimg);
        TextView lname = rowView.findViewById(R.id.loname);
        TextView lamount = rowView.findViewById(R.id.loamount);
        TextView ldate = rowView.findViewById(R.id.lodate);
        TextView lperson = rowView.findViewById(R.id.loperson);

        lname.setText(crtList.getLocation());

        VDetails = rowView.findViewById(R.id.loDetils);

        VDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewDetiels();
            }
        });

        return super.getView(position , convertView , parent);
    }

    public void viewDetiels(){

      /*  Intent intent = new Intent(this , TripDetailsActivity.class);
        startActivity(intent);*/

    }
}
