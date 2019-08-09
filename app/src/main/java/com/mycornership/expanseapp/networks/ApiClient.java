package com.mycornership.expanseapp.networks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private final static String BASE_URL = "http://data.fixer.io";
    private static Retrofit instance;

    public static Retrofit getInstance(){
        if(instance == null){
            instance = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

}
