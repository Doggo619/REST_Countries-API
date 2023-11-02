package com.base.restcountries;

import android.app.Application;

public class MyApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CountryDatabase.getDatabase(this);
    }
}
