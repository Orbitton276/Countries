package com.countries.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.countries.model.Country;
import com.countries.viewmodel.CountryDetailsViewModel;

public class CountryDetailsViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {
    /**
     * Creates a {@code AndroidViewModelFactory}
     *
     * @param application an application to pass in {@link AndroidViewModel}
     */

    Country country;
    Application application;

    public CountryDetailsViewModelFactory(@NonNull Application application, Country country) {
        super(application);
        this.country = country;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CountryDetailsViewModel(application, country);
    }
}
