package com.base.restcountries;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {
    private CountryRepository countryRepository;
    private LiveData<List<CountryEntity>> allCountries;
    public CountryRepository getCountryRepository() {
        return countryRepository;
    }

    public CountryViewModel(@NonNull Application application) {
        super(application);
        CountryDatabase db = CountryDatabase.getDatabase(application);
        CountryDao countryDao = db.countryDao();
        CountryApiService countryApiService = CountryApiService.create();
        countryRepository = new CountryRepository(countryDao, countryApiService, application.getApplicationContext());
        allCountries = countryRepository.getAllCountries();
    }

    public LiveData<List<CountryEntity>> getAllCountries() {
        return allCountries;
    }
}

