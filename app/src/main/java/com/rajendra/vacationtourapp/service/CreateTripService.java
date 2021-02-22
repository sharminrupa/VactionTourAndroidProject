package com.rajendra.vacationtourapp.service;

import com.rajendra.vacationtourapp.model.CreateTrip;
import com.rajendra.vacationtourapp.model.TripBook;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CreateTripService {

    @POST("/create/add")
    public Call<CreateTrip> addBooking(@Body CreateTrip createTrip);

    @GET("/create/list")
    public Call<List<CreateTrip>> getAll();

    @GET("/create/one/{id}")
    public Call<CreateTrip> getById(@Path("id") long id);

    @GET("/create/delete/{id}")
    public Call<CreateTrip> deleteById(@Path("id") long id);

}
