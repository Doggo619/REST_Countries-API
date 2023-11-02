package com.base.restcountries;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCountry(List<CountryEntity> country);

    @Query("SELECT * FROM countries")
    LiveData<List<CountryEntity>> getAllCountries();
}


