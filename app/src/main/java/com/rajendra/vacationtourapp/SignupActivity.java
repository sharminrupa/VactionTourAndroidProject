package com.rajendra.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rajendra.vacationtourapp.model.UserModel;
import com.rajendra.vacationtourapp.retrofit.RetrofitConfig;
import com.rajendra.vacationtourapp.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private Intent i;
    EditText sname;
    EditText smobile;
    EditText semail;
    EditText spass;
    EditText sconpass;
    Context mtx;
    UserService userService;


    //EditText ed1, ed2, ed3;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        mtx = this;
        userService = RetrofitConfig.createService(UserService.class);

        sname = findViewById(R.id.name);
        smobile = findViewById(R.id.mobile);
        semail = findViewById(R.id.email);
        spass = findViewById(R.id.pass);
        sconpass = findViewById(R.id.conpass);

        b1 = findViewById(R.id.addbtn);
        b2 = findViewById(R.id.cancelbtn);


    }

    /*public void insert() {
        Intent intent = new Intent(this , LoginActivity.class);
        startActivity(intent);

    }*/

    public void navigateLogin(View view){
        i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }


    public void add(View view){
        String name = sname.getText().toString();
        String mobile = smobile.getText().toString();
        String email = semail.getText().toString();
        String pass = spass.getText().toString();
        String conpass = sconpass.getText().toString();
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setMobile(mobile);
        userModel.setEmail(email);
        userModel.setPass(pass);
        userModel.setConpass(conpass);
        Call<UserModel> res = userService.add(userModel);
        res.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel user =  response.body();
                startActivity(new Intent(mtx, LoginActivity.class));
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}