package com.mycornership.expanseapp;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.mycornership.expanseapp.database.CurrencyDAO;
import com.mycornership.expanseapp.database.CurrencyDB;
import com.mycornership.expanseapp.database.CurrencyRate;
import com.mycornership.expanseapp.models.CurrencyResponse;
import com.mycornership.expanseapp.networks.ApiClient;
import com.mycornership.expanseapp.networks.ClientService;
import com.mycornership.expanseapp.networks.NetworkCheck;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrncyRepository {
    private Context c;
    private ClientService apiService;
    private CurrencyDAO dao;
    List<CurrencyRate> rates;

    public CurrncyRepository(Application application){
        this.c = application;
        apiService = ApiClient.getInstance().create(ClientService.class);
        //create database instance
        CurrencyDB db = CurrencyDB.getInstance(application);
        dao = db.currencyDAO();
    }

    public void fetchRates(String query){

        if(NetworkCheck.isConnected(c)){
            final Call<CurrencyResponse> dataCall = apiService.getCurrencyData(query);
            dataCall.enqueue(new Callback<CurrencyResponse>() {
                CurrencyResponse fetchedData;
                @Override
                public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {
                    if(response.isSuccessful()){
                        fetchedData = response.body();
                        commitData(fetchedData);
                    }
                }
                @Override
                public void onFailure(Call<CurrencyResponse> call, Throwable t) {
                }
            });

        }
    }

    private void commitData(CurrencyResponse fetchedData){
        rates = new ArrayList<>();
        Map<String, String> rateData = fetchedData.getRates();
        for(Map.Entry<String, String> entries: rateData.entrySet()){
            String key = entries.getKey();
            String val = entries.getValue();
            double rt = Double.parseDouble(val);

            rates.add(new CurrencyRate(key, rt));
        }
            //input data into the database
            new insertAsynTask(dao).execute(rates);

    }

    public static class insertAsynTask extends AsyncTask<List<CurrencyRate>, Void, Void> {
        private CurrencyDAO currencyDAO;

        private insertAsynTask(CurrencyDAO dao){
            this.currencyDAO = dao;
        }

        @Override
        protected Void doInBackground(List<CurrencyRate>... lists) {
            currencyDAO.insertAll(lists[0]);
            return null;
        }
    }

    public LiveData<List<CurrencyRate>> getSymbols(){
        return dao.getAllSymbol();
    }

}
