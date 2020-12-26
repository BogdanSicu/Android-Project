package com.example.proiect_tocilarii_betivani.LocalDataBase.Services;

import android.content.Context;

import com.example.proiect_tocilarii_betivani.LocalDataBase.Dao.RatesDao;
import com.example.proiect_tocilarii_betivani.LocalDataBase.LocalDbManager;
import com.example.proiect_tocilarii_betivani.Util.Acount;
import com.example.proiect_tocilarii_betivani.Util.Rates;
import com.example.proiect_tocilarii_betivani.asyncTask.AsyncTaskRunner;
import com.example.proiect_tocilarii_betivani.asyncTask.Callback;

import java.util.List;
import java.util.concurrent.Callable;

public class RatesService {
    private final RatesDao ratesDao;
    private final AsyncTaskRunner asyncTaskRunner;


    public RatesService(Context context) {
        this.ratesDao = LocalDbManager.getInstance(context).getRatesDao();
        this.asyncTaskRunner = new AsyncTaskRunner();
    }

    //select
    public void getAllRates(Callback<List<Rates>> callback){ //suprscriem ceea ce trimitem catre baza de date
        //callable este trimis catre baza de date
        Callable<List<Rates>> callable = new Callable<List<Rates>>() {
            @Override
            public List<Rates> call() throws Exception {
                return ratesDao.getAllRates();
            }
        };
        //callback este intors catre activitatea/fragmentul care a facut cererea
        //ceea ce inseamna si ca tot de acolo vine
        asyncTaskRunner.executeAsync(callable, callback);
    }

    //insert
    public void insertAccount(Callback<Rates> callback,final Rates rates){
        Callable<Rates> callable = new Callable<Rates>() {
            @Override
            public Rates call() throws Exception {
                if(rates == null){
                    return null;
                }
                long id = ratesDao.insertAccount(rates);
                if(id==-1){
                    return null;
                }
                rates.setId(id);
                return rates;
            }
        };
        asyncTaskRunner.executeAsync(callable, callback);
    }
}
