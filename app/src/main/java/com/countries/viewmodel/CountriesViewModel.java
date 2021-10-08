package com.countries.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.countries.model.Country;
import com.cuntries.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountriesViewModel extends AndroidViewModel {

    private final Gson gson = new Gson();
    private MutableLiveData<List<Country>> mCountries;
    private static final String TAG = "CountriesViewModel";

    public CountriesViewModel(@NonNull Application application) {
        super(application);
        Log.e(TAG, "constructor");
    }

    public LiveData<List<Country>> getCountries() {
        if (mCountries == null) {
            try {
                List<Country> list = getCountriesFromJson();
                mCountries = new MutableLiveData<>();
                mCountries.setValue(list);
            } catch (IOException e) {
                Log.e(TAG, "Failed to load data");
            }
        }
        return mCountries;
    }

    public List<Country> getCountriesFromJson() throws IOException {
        Country[] countries = gson.fromJson(getCountriesJson(), Country[].class);
        return new ArrayList<>(Arrays.asList(countries));
    }

    private String getCountriesJson() {
        //read the data from the json file
        InputStream is = getApplication().getResources().openRawResource(R.raw.countries);
        int size = 0;
        String retVal = null;
        try {
            size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            retVal = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retVal;
    }
}
