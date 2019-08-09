package com.mycornership.expanseapp.database;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CurrencyRate.class}, version = 1)
public abstract class CurrencyDB extends RoomDatabase {

    public abstract CurrencyDAO currencyDAO();
    private static CurrencyDB instance;

    public static CurrencyDB getInstance(Application app){

        if(instance == null){
            instance = Room.databaseBuilder(app, CurrencyDB.class, "currency_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
