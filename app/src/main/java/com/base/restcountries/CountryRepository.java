package com.base.restcountries;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryRepository {
    private CountryDao countryDao;
    private CountryApiService countryApiService;
    Context context;

    public CountryRepository(CountryDao countryDao, CountryApiService countryApiService, Context context) {
        this.countryDao = countryDao;
        this.countryApiService = countryApiService;
        this.context = context;
    }

    public LiveData<List<CountryEntity>> getAllCountries() {
        return countryDao.getAllCountries();
    }

    public void refreshCountries() {
        Call<List<CountryInfo>> call = countryApiService.getCountries();
        Log.d("CountryRepository", "Requesting countries from the API.");

        call.enqueue(new Callback<List<CountryInfo>>() {
            @Override
            public void onResponse(Call<List<CountryInfo>> call, Response<List<CountryInfo>> response) {
                if (response.isSuccessful()) {
                    List<CountryInfo> countryInfos = response.body();
                    if (countryInfos != null && !countryInfos.isEmpty()) {
                        List<CountryEntity> countryEntities = new ArrayList<>();
                        for (CountryInfo info : countryInfos) {
                            if (info.getName() != null && info.getFlags() != null) {
                                String common = info.getName().getCommon();
                                CountryInfo.FlagsInfo flags = info.getFlags();
                                String png = flags.getPng(); // Extract the 'png' value from flags object
                                List<String> capitals = info.getCapital();

                                if (common != null && png != null && capitals != null && !capitals.isEmpty()) {
                                    String capital = capitals.get(0);
                                    countryEntities.add(new CountryEntity(common, png, capital));
                                } else {
                                    Log.e("CountryRepository", "Invalid data for a country. Skipping.");
                                }
                            } else {
                                Log.e("CountryRepository", "Invalid data for a country. Skipping.");
                            }
                        }

                        insertCountries(countryEntities);
                        Log.d("CountryRepository", "Received " + countryEntities.size() + " countries from the API.");
                    } else {
                        Log.d("CountryRepository", "Received an empty response from the API.");
                    }
                } else {
                    Log.e("CountryRepository", "API request failed with response code: " + response.code());
                }
            }


            @Override
            public void onFailure(Call<List<CountryInfo>> call, Throwable t) {
                Toast.makeText(context, "Network request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("CountryRepository", "Network request failed: " + t.getMessage());
            }
        });
    }



    private void insertCountries(List<CountryEntity> countryEntities) {
        Log.d("CountryRepository", "Inserting " + countryEntities.size() + " countries into the database.");
        new Thread(() -> {
            countryDao.insertCountry(countryEntities);


        }).start();
    }
}

