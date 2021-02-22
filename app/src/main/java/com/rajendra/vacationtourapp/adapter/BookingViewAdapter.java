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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rajendra.vacationtourapp.EditTripBookingActivity;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.model.TripBook;
import com.rajendra.vacationtourapp.retrofit.RetrofitConfig;
import com.rajendra.vacationtourapp.service.TripBookService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingViewAdapter extends ArrayAdapter<TripBook> {
    Activity context;
    List<TripBook> list;
    TripBookService tripBookService;

    public BookingViewAdapter(Activity context, List<TripBook> list) {
        super(context , R.layout.activity_view_booking, list);
        tripBookService = RetrofitConfig.createService(TripBookService.class);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(final int position , @Nullable View convertView , @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
         TripBook trips = list.get(position);

        View rowView = inflater.inflate(R.layout.activity_view_booking, null, true);
        TextView tname = rowView.findViewById(R.id.tvname);
        TextView tmobile = rowView.findViewById(R.id.tvmobile);
        TextView temail = rowView.findViewById(R.id.tvemail);
        TextView taddress = rowView.findViewById(R.id.tvaddress);
        TextView tpercon = rowView.findViewById(R.id.tvperson);
        TextView tlocation = rowView.findViewById(R.id.tvlocation);
        TextView tmesssage = rowView.findViewById(R.id.tvmessage);


        tname.setText(trips.getName());
        tmobile.setText(trips.getMobile());
        temail.setText(trips.getEmail());
        taddress.setText(trips.getAddress());
        tpercon.setText(trips.getPerson());
        tlocation.setText(trips.getLocation());
        tmesssage.setText(trips.getMessage());

        Button tupdate = rowView.findViewById(R.id.tvupdate);
        Button tdelete = rowView.findViewById(R.id.tvdelete);

      /*  tupdate.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                TripBook tbook = list.get(position);
                Intent i = new Intent(context, EditTripBookingActivity.class);
                i.putExtra("id", trips.getId());
                context.startActivity(i);
            }

        });*/

       /* tdelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                TripBook tripBook = list.get(position);
                Call<TripBook> tb = tripBookService.deleteById(trips.getId());
                tb.enqueue(new Callback<TripBook>() {
                    @Override
                    public void onResponse(Call<TripBook> call , Response<TripBook> response) {
                        list.remove(position);
                        notifyDataSetChanged();
                        setNotifyOnChange(true);
                        Toast.makeText(context, "Delete Successful", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<TripBook> call , Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });*/

        return rowView;
    }
}
