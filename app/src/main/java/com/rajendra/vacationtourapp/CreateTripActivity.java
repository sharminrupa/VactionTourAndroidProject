package com.rajendra.vacationtourapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.rajendra.vacationtourapp.imageupload.ImageManager;
import com.rajendra.vacationtourapp.imageupload.ImageUploadResponse;
import com.rajendra.vacationtourapp.model.CreateTrip;
import com.rajendra.vacationtourapp.retrofit.RetrofitConfig;
import com.rajendra.vacationtourapp.service.CreateTripService;
import com.rajendra.vacationtourapp.service.ImageUploadService;
import com.rajendra.vacationtourapp.service.UserService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    EditText ctperson;

    EditText ctdetails;
    Context mtx;
    ImageView imgBtn, imgShow;
    String s;
    CreateTripService createTripService;
    Button CnButton;

    ImageUploadService imageUploadService;
    Uri imageUri;
    ImageManager imageManager;
    private static final int PICK_IMAGE = 100;
    ImageUploadResponse imageUploadResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        mtx = this;
        imageUploadResponse = new ImageUploadResponse();
        createTripService = RetrofitConfig.createService(CreateTripService.class);
        imageManager = new ImageManager(this);
        ctlocation = findViewById(R.id.clocation);
        imageUploadService = RetrofitConfig.createService(ImageUploadService.class);
        ctday = findViewById(R.id.cday);
        ctamount = findViewById(R.id.camount);
        ctrecive = findViewById(R.id.crecive);
        ctdate = findViewById(R.id.cdate);
        ctperson = findViewById(R.id.maxpeople);
        imgBtn = findViewById(R.id.imgUpload);
        imgShow = findViewById(R.id.imgShow);

        ctdetails = findViewById(R.id.cdetails);

        CnButton = findViewById(R.id.CreateCancel);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageUri = imageManager.pickImage();
            }
        });


        /*--------Date show start-------*/

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

        /*--------Date show end-------*/


        CnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CancelTrip();
            }

        });

        imageManager.checkPermission();

    }

    /*--------Image show start-------*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imgShow.setImageURI(imageUri);
        }
    }

    /*--------Image show end-------*/


    private void CancelTrip() {
        Intent intent = new Intent(this , CreateTripActivity.class);
        startActivity(intent);
    }


    public void createTrip(View view) {
        uploadImage(imageUri);
    }


    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        ctdate.setText(sdf.format(myCalendar.getTime()));
    }
    
    
    
    
  //experiment



    public void uploadImage(Uri uri){
        File file = new File(getPath(uri));
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part imageFile = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        Call<ImageUploadResponse> res = imageUploadService.imageUpload(imageFile);
        res.enqueue(new Callback<ImageUploadResponse>() {
            @Override
            public void onResponse(Call<ImageUploadResponse> call, Response<ImageUploadResponse> response) {
                imageUploadResponse = response.body();

                //experiment

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
                String crdate = ctdate.getText().toString();
                String crperson = ctperson.getText().toString();

                String crdetails = ctdetails.getText().toString();

                CreateTrip crtrip = new CreateTrip();
                crtrip.setLocation(crlocation);
                crtrip.setDay(crday);
                crtrip.setCost(cramount);
                crtrip.setRecivingaddress(crrecive);
                crtrip.setDate(crdate);
                crtrip.setPerson(crperson);
                crtrip.setDetails(crdetails);

                if(imageUri != null){
                    String imgName = imageUploadResponse.getFileName();
                    System.out.println("=================="+imgName);
                    crtrip.setImage(imgName);
                }else{
                    crtrip.setImage("default.jpeg");
                }
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

            @Override
            public void onFailure(Call<ImageUploadResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public String getPath(Uri uri)
    {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        //added for cursor bound exception
        if( cursor != null && cursor.moveToFirst() ){
            s=cursor.getString(column_index);
            cursor.close();
        }

        return s;
    }
}

