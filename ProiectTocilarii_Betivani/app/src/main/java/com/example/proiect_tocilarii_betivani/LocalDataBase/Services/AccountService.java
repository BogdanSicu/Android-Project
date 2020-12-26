package com.example.proiect_tocilarii_betivani.LocalDataBase.Services;

import android.content.Context;

import com.example.proiect_tocilarii_betivani.LocalDataBase.Dao.AccountDao;
import com.example.proiect_tocilarii_betivani.LocalDataBase.LocalDbManager;
import com.example.proiect_tocilarii_betivani.Util.Acount;
import com.example.proiect_tocilarii_betivani.asyncTask.AsyncTaskRunner;
import com.example.proiect_tocilarii_betivani.asyncTask.Callback;

import java.util.List;
import java.util.concurrent.Callable;

public class AccountService {
    private final AccountDao accountDao;
    private final AsyncTaskRunner asyncTaskRunner;


    public AccountService(Context context) {
        this.accountDao = LocalDbManager.getInstance(context).getAccountDao();
        this.asyncTaskRunner = new AsyncTaskRunner();
    }

    //select
    public void getAllAccounts(Callback<List<Acount>> callback){ //suprscriem ceea ce trimitem catre baza de date
        //callable este trimis catre baza de date
        Callable<List<Acount>> callable = new Callable<List<Acount>>() {
            @Override
            public List<Acount> call() throws Exception {
                return accountDao.getAllAccounts();
            }
        };
        //callback este intors catre activitatea/fragmentul care a facut cererea
        //ceea ce inseamna si ca tot de acolo vine
        asyncTaskRunner.executeAsync(callable, callback);
    }

    //insert
    public void insertAccount(Callback<Acount> callback,final Acount account){
        Callable<Acount> callable = new Callable<Acount>() {
            @Override
            public Acount call() throws Exception {
                if(account == null){
                    return null;
                }
                long id = accountDao.insertAccount(account);
                if(id==-1){
                    return null;
                }
                account.setId(id);
                return account;
            }
        };
        asyncTaskRunner.executeAsync(callable, callback);
    }
}
