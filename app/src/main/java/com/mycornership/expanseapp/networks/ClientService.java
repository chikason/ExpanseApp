package com.mycornership.expanseapp.networks;

import com.mycornership.expanseapp.models.CurrencyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ClientService {

    @GET("api/latest?access_key=8124da859a9eb609a353e5af585dcae0&base=EUR")
    Call<CurrencyResponse> getCurrencyData(@Query("symbols")String label);
}
