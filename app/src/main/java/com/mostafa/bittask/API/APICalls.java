package com.mostafa.bittask.API;


import com.mostafa.bittask.Model.Photos.ResponseHome;
import com.mostafa.bittask.Model.Info.ResponseProfile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APICalls {

    @GET("profile")
    Call<ResponseProfile> getAllInfo(@Query("id") int id);

    @GET("home")
    Call<ResponseHome> getAllPhoto();

}
