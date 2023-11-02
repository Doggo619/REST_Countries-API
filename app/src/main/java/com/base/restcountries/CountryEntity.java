package com.base.restcountries;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "countries")
public class CountryEntity {
    @PrimaryKey
    @NonNull
    public String name;
    public String flags;
    public String capital;

    public CountryEntity(@NonNull String name, String flags, String capital) {
        this.name = name;
        this.flags = flags;
        this.capital = capital;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
