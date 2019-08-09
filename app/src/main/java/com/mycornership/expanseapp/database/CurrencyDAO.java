package com.mycornership.expanseapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CurrencyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<CurrencyRate> rateList);

    @Update
    void updateAll(List<CurrencyRate> rates);

    @Query("SELECT * FROM CurrencyRate ORDER BY symbol")
    LiveData<List<CurrencyRate>> getAllSymbol();

    @Query("SELECT rate FROM CurrencyRate WHERE symbol =:label")
    double getRate(String label);
}
