package com.rajendra.vacationtourapp.service;



import com.rajendra.vacationtourapp.imageupload.ImageUploadResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ImageUploadService {
    @Multipart
    @POST("/uploadFile")
    public Call<ImageUploadResponse> imageUpload(@Part MultipartBody.Part file);

    @GET("/downloadFile/{name}")
    public Call<ImageUploadResponse> getImageByName(@Path("name") String name);
}
