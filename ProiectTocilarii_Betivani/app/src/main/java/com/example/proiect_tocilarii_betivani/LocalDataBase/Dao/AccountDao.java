package com.example.proiect_tocilarii_betivani.LocalDataBase.Dao;

import android.accounts.Account;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proiect_tocilarii_betivani.Util.Acount;
import com.example.proiect_tocilarii_betivani.Util.Rates;

import java.util.List;

//aici introducem metodele pentru baza de date catre tabela Account
@Dao
public interface AccountDao {
    @Query("select * from accounts")
    List<Acount> getAllAccounts();

    @Insert
    long insertAccount(Acount account);

    @Update
    int update(Acount account);

    @Delete
    int delete(Acount acount);
}
