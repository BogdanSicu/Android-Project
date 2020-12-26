package com.example.proiect_tocilarii_betivani.LocalDataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.proiect_tocilarii_betivani.LocalDataBase.Dao.AccountDao;
import com.example.proiect_tocilarii_betivani.LocalDataBase.Dao.RatesDao;
import com.example.proiect_tocilarii_betivani.Util.AccountTypeConverter;
import com.example.proiect_tocilarii_betivani.Util.Acount;
import com.example.proiect_tocilarii_betivani.Util.Rates;

//entities sunt tabelele formate din clasele cu notatii speciale
@Database(entities = {Acount.class, Rates.class}, exportSchema = false, version = 1)
@TypeConverters(AccountTypeConverter.class)
public abstract class LocalDbManager extends RoomDatabase {

    //denumirea conexiunii la baza de date
    private static final String localDB = "localDB";
    private static LocalDbManager localDbManager;

    public static LocalDbManager getInstance(Context context){
        //sincronizam threadurile
        //!Nu putem face mai multe instante de conexiune la baza de date!
        if(localDbManager == null){
            synchronized (LocalDbManager.class){
                if(localDbManager == null){
                    //daca conexiunea este nula, o cream
                    localDbManager = Room.databaseBuilder(context, LocalDbManager.class,localDB)
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        //returnam conexiunea la baza de date
        return localDbManager;
    }

    public abstract AccountDao getAccountDao();
    public abstract RatesDao getRatesDao();

}
