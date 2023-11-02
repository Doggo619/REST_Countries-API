package com.base.restcountries;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {
    CountryViewModel countryViewModel;
    CountryRepository countryRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        CountryAdapter adapter = new CountryAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);
        countryRepository = countryViewModel.getCountryRepository();
        countryRepository.refreshCountries();

        countryViewModel.getAllCountries().observe(this, countries -> {
            adapter.setCountries(countries);
        });

    }
}
