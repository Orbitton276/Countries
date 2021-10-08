package com.countries.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.countries.adapter.CountriesRecyclerViewAdapter;
import com.countries.viewmodel.CountriesViewModel;
import com.countries.model.Country;
import com.cuntries.R;

import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CountriesRecyclerViewAdapter.OnItemClickListener {

    private static final String TAG = "MainActivity";
    private CountriesViewModel countriesViewModel;
    private CountriesRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Log.e(TAG, "init");
        initSortButtons();
        initViewModel();
        initRecycler();
    }

    private void initViewModel() {
        countriesViewModel = ViewModelProviders.of(this).get(CountriesViewModel.class);
        countriesViewModel.getCountries().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                Log.e(TAG, "onChanged");
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initSortButtons() {
        Button nameAZ = findViewById(R.id.btn_name_az);
        Button nameZA = findViewById(R.id.btn_name_za);
        nameAZ.setOnClickListener(this);
        nameZA.setOnClickListener(this);
    }

    private void initRecycler() {
        RecyclerView recyclerView = findViewById(R.id.rv_countries);
        adapter = new CountriesRecyclerViewAdapter(countriesViewModel.getCountries().getValue(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        //select the comparator to sort the list
        Comparator comparator = Country.CountryAZComparator;
        switch (view.getId()) {
            case R.id.btn_name_az:
                comparator = Country.CountryAZComparator;
                break;
            case R.id.btn_name_za:
                comparator = Country.CountryZAComparator;
                break;
        }
        adapter.sort(comparator);
    }

    @Override
    public void onItemClick(Country item) {
        //open the details activity with the corresponding country data
        Log.e(TAG, "onItemClick item: " + item.getName());
        Intent intent = new Intent(this, CountryDetailsActivity.class);
        intent.putExtra("extra_country", item);
        startActivity(intent);
    }
}