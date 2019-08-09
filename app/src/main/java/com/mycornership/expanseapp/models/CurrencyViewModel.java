package com.mycornership.expanseapp.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mycornership.expanseapp.CurrncyRepository;
import com.mycornership.expanseapp.database.CurrencyRate;

import java.util.List;

public class CurrencyViewModel  extends AndroidViewModel {
    private CurrncyRepository repository;
    private LiveData<List<CurrencyRate>> rates = null;

    public CurrencyViewModel(@NonNull Application application) {
        super(application);
        repository = new CurrncyRepository(application);
    }

    public void fetchRateData(){
        repository.fetchRates("EUR,GBP,NGN,AMD,ARS,JPY,BBD,AFN,JEP,CAD,PHP,USD,RUB,CHF");
    }

    public LiveData<List<CurrencyRate>> getSymbols(){
        rates = repository.getSymbols();
        return rates;
    }
}
