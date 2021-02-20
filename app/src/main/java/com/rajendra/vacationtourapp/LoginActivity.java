package com.rajendra.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
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

public class LoginActivity extends AppCompatActivity {
    private Intent i;
    UserService userService;
    EditText lemal, lpass;
    Button b1, b2;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userService = RetrofitConfig.createService(UserService.class);
        lemal = (EditText) findViewById(R.id.email);
        lpass = (EditText) findViewById(R.id.loginPass);
        b1 = (Button) findViewById(R.id.loginbtn);
        b2 = (Button) findViewById(R.id.signbtn);
        /*t1 = (TextView) findViewById(R.id.registation);

        t1.setMovementMethod(LinkMovementMethod.getInstance());
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });*/

        /*b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });*/
    }


    public void navigateSignup(View view){
        i = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(i);
    }

    public void loginbtn(View view){
        String uEmail = lemal.getText().toString();
        String uPass = lpass.getText().toString();
        UserModel userModel  = new UserModel();
        userModel.setEmail(uEmail);
        userModel.setPass(uPass);
        Call<UserModel> user = userService.login(userModel);
        user.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel userModel1 = response.body();
                if(userModel1 != null){
                    if(userModel1.getEmail() != null){
                        i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(getApplicationContext(), "Please check usrname/password", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    /*private void login() {

        String user = ed1.getText().toString();
        String pass = ed2.getText().toString();

        if (user.equals("") || pass.equals("")) {
            Toast.makeText(this , "User or Password Blank" , Toast.LENGTH_LONG).show();
        } else if (null != checkUser(user,pass)) {

            String userdb = checkUser(user,pass);

            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            i.putExtra("uname", userdb);
            startActivity(i);

        }
        else {
            Toast.makeText(this, "User name or Password not match", Toast.LENGTH_LONG).show();
            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();
        }
    }

    private String checkUser(String user , String pass) {

        SQLiteDatabase db = openOrCreateDatabase("toy", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("select id,user,pass from user where user = ? and pass = ? ", new String[]{user,pass});
        if (cursor.getCount() > 0){
            cursor.moveToNext();
            String username = cursor.getString(1);
            String loginPass = cursor.getString(2);
            SharedPreferences.Editor sp = getSharedPreferences("username", MODE_PRIVATE).edit();
            sp.putString("uname", username);
            sp.putString("loginPass", loginPass);
            sp.apply();
            cursor.close();
            return username;
        }
        return null;

    }*/
}
