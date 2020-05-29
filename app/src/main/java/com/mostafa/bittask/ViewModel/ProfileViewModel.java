package com.mostafa.bittask.ViewModel;

import android.app.Application;

import com.mostafa.bittask.API.ApiManager;
import com.mostafa.bittask.Model.Info.Counts;
import com.mostafa.bittask.Model.Info.ResponseProfile;
import com.mostafa.bittask.Model.Photos.DataItem;
import com.mostafa.bittask.Model.Photos.ResponseHome;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends AndroidViewModel {

    protected MutableLiveData<ResponseProfile> profileLiveData;
    protected MutableLiveData<Counts> countsLiveData;
    protected MutableLiveData<List<DataItem>> photosLiveData;
    protected MutableLiveData<String> errorMassage;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        errorMassage = new MutableLiveData<>();
        profileLiveData = new MutableLiveData<>();
        countsLiveData = new MutableLiveData<>();
        photosLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<DataItem>> getPhotosLiveData() {
        return photosLiveData;
    }

    public void setPhotosLiveData(MutableLiveData<List<DataItem>> photosLiveData) {
        this.photosLiveData = photosLiveData;
    }

    public MutableLiveData<ResponseProfile> getProfileLiveData() {
        return profileLiveData;
    }

    public void setProfileLiveData(MutableLiveData<ResponseProfile> profileLiveData) {
        this.profileLiveData = profileLiveData;
    }

    public MutableLiveData<Counts> getCountsLiveData() {
        return countsLiveData;
    }

    public void setCountsLiveData(MutableLiveData<Counts> countsLiveData) {
        this.countsLiveData = countsLiveData;
    }

    public MutableLiveData<String> getErrorMassage() {
        return errorMassage;
    }

    public void setErrorMassage(MutableLiveData<String> errorMassage) {
        this.errorMassage = errorMassage;
    }

    public void ShowProfile(int id) {
        ApiManager.getAPIs().getAllInfo(id).enqueue(new Callback<ResponseProfile>() {
            @Override
            public void onResponse(Call<ResponseProfile> call, Response<ResponseProfile> response) {
                if (response.isSuccessful()) {

                    if ("true".equals(String.valueOf(response.body().isStatus()))) {
                        profileLiveData.postValue(response.body());
                    } else {
                        profileLiveData.postValue(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseProfile> call, Throwable t) {
                errorMassage.postValue(t.getLocalizedMessage());
            }
        });

    }

    ////////////
    public void ShowPhotos() {
        ApiManager.getAPIs().getAllPhoto().enqueue(new Callback<ResponseHome>() {
            @Override
            public void onResponse(Call<ResponseHome> call, Response<ResponseHome> response) {
                if (response.isSuccessful()) {

                    if ("true".equals(String.valueOf(response.body().isStatus()))) {
                        photosLiveData.postValue(response.body().getData());
                    } else {
                        photosLiveData.postValue(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseHome> call, Throwable t) {
                errorMassage.postValue(t.getLocalizedMessage());
            }
        });

    }
}
