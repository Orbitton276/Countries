package com.countries.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.countries.model.Country;


public class CountryDetailsViewModel extends AndroidViewModel {

    private final MutableLiveData<Country> mCountry;
    private static final String TAG = "CountryDetailsViewModel";

    public CountryDetailsViewModel(@NonNull Application application, Country country) {
        super(application);
        mCountry = new MutableLiveData<>();
        mCountry.setValue(country);
        Log.e(TAG, " constructor");
    }

    public MutableLiveData<Country> getCountry() {
        return mCountry;
    }
}
