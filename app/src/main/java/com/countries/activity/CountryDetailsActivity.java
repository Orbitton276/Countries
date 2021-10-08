package com.countries.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.countries.viewmodel.CountryDetailsViewModel;
import com.countries.viewmodel.CountryDetailsViewModelFactory;
import com.countries.model.Country;
import com.cuntries.R;

public class CountryDetailsActivity extends AppCompatActivity {
    private static final String TAG = "CountryDetailsActivity";

    CountryDetailsViewModel countriesViewModel;
    View contentView, errorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);
        boolean extraCountryAvailable = getIntent().hasExtra("extra_country");
        initViews();
        if (extraCountryAvailable) {
            initViewModel();
            errorView.setVisibility(View.GONE);
            contentView.setVisibility(View.VISIBLE);
        } else {
            errorView.setVisibility(View.VISIBLE);
            contentView.setVisibility(View.GONE);
        }
    }

    private void initViewModel() {
        Country country = (Country) getIntent().getSerializableExtra("extra_country");
        // inject the country data to the view model using view model factory
        countriesViewModel = ViewModelProviders.of(this, new CountryDetailsViewModelFactory(getApplication(), country)).get(CountryDetailsViewModel.class);
        countriesViewModel.getCountry().observe(this, new Observer<Country>() {
            @Override
            public void onChanged(Country country) {
                Log.e(TAG, "onChanged");
                populateViews(country);
            }
        });
    }

    private void initViews() {
        errorView = findViewById(R.id.tv_error);
        contentView = findViewById(R.id.main_layout);
        ((ImageView) findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // back button - finish the activity
                finish();
            }
        });

    }


    private void populateViews(Country country){
        ((TextView) findViewById(R.id.tv_country_name)).setText(country.getName());
        ((TextView) findViewById(R.id.tv_capital_text)).setText(country.getCapital());
        ((TextView) findViewById(R.id.tv_region_text)).setText(country.getRegion());
        ((TextView) findViewById(R.id.tv_alpha2_text)).setText(country.getAlpha2Code());
        ((TextView) findViewById(R.id.tv_alpha3_text)).setText(country.getAlpha3Code());
    }
}