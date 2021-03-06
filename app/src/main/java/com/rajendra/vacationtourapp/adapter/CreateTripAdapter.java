package com.rajendra.vacationtourapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rajendra.vacationtourapp.DetailsActivity;
import com.rajendra.vacationtourapp.LoginActivity;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.TripBookActivity;
import com.rajendra.vacationtourapp.TripDetailsActivity;
import com.rajendra.vacationtourapp.imageupload.ImageManager;
import com.rajendra.vacationtourapp.imageupload.ImageUploadResponse;
import com.rajendra.vacationtourapp.model.CreateTrip;
import com.rajendra.vacationtourapp.model.TripBook;
import com.rajendra.vacationtourapp.retrofit.RetrofitConfig;
import com.rajendra.vacationtourapp.service.CreateTripService;
import com.rajendra.vacationtourapp.service.ImageUploadService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class CreateTripAdapter extends ArrayAdapter<CreateTrip> {
    Activity context;
    List<CreateTrip> list;
    CreateTripService createTripService;
    Button VDetails, VDelete;

    ImageView limg;

    public CreateTripAdapter(Activity context , List<CreateTrip> crList) {
        super(context, R.layout.create_trip_list, crList);
        createTripService = RetrofitConfig.createService(CreateTripService.class);
        this.context = context;
        this.list = crList;
    }

    @NonNull
    @Override
    public View getView(final int position , @Nullable View convertView , @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        //-----------image view start
        CreateTrip crtList = list.get(position);
        View rowView =  inflater.inflate(R.layout.create_trip_list, null, true);
        CreateTrip post = list.get(position);


        //imageUri = Uri.parse(post.getImage());
        //-----------image view end

        limg = rowView.findViewById(R.id.loimg);
        TextView lname = rowView.findViewById(R.id.loname);
        TextView lamount = rowView.findViewById(R.id.loamount);
        TextView ldate = rowView.findViewById(R.id.lodate);
        TextView lperson = rowView.findViewById(R.id.loperson);

        Picasso.get().load("http://192.168.0.103:8080/downloadFile/".concat(crtList.getImage())).into(limg);
        lname.setText(crtList.getLocation());
        lamount.setText(crtList.getCost());
        ldate.setText(crtList.getDate());
        lperson.setText(crtList.getPerson());

        VDetails = rowView.findViewById(R.id.loDetils);
        VDelete = rowView.findViewById(R.id.lodelete);

        VDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewDetiels(list.get(position).getId());
            }
        });

        VDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("dkjkjdkj");
                CreateTrip createTrip = list.get(position);
                Call<CreateTrip> tb = createTripService.deleteById(createTrip.getId());
                tb.enqueue(new Callback<CreateTrip>() {
                    @Override
                    public void onResponse(Call<CreateTrip> call , Response<CreateTrip> response) {
                        list.remove(position);
                        notifyDataSetChanged();
                        setNotifyOnChange(true);
                        Toast.makeText(context, "Delete Successful", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<CreateTrip> call , Throwable t) {
                        t.printStackTrace();
                    }
                });
            }


        });

        return rowView;
    }

     /*--------Image show start-------*/

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            limg.setImageURI(imageUri);
        }
    }*/

    /*--------Image show end-------*/

    public void viewDetiels(long id){
      Intent intent = new Intent(context , DetailsActivity.class);
      intent.putExtra("tripId", id);
        context.startActivity(intent);

    }



}
