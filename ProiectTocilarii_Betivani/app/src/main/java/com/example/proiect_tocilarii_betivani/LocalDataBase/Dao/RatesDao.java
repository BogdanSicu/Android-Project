package com.example.proiect_tocilarii_betivani.LocalDataBase.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proiect_tocilarii_betivani.Util.Acount;
import com.example.proiect_tocilarii_betivani.Util.Rates;

import java.util.List;

@Dao
public interface RatesDao {
    @Query("select * from rates")
    List<Rates> getAllRates();

    @Insert
    long insertAccount(Rates rates);

    @Update
    int update(Rates rates);

    @Delete
    int delete(Rates rates);
}
