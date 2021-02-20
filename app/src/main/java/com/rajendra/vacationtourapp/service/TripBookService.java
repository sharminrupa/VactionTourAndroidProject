package com.rajendra.vacationtourapp.service;

import com.rajendra.vacationtourapp.model.TripBook;
import com.rajendra.vacationtourapp.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TripBookService {

    @POST("/book/add")
    public Call<TripBook> addBooking(@Body TripBook tripBook);

    @GET("/book/list")
    public Call<List<TripBook>> getAll();

    @GET("/book/one/{id}")
    public Call<TripBook> getById(@Path("id") long id);

    @GET("/book/delete/{id}")
    public Call<TripBook> deleteById(@Path("id") long id);

    @POST("/book/login")
    public Call<TripBook> login(@Body TripBook tripBook);

}
