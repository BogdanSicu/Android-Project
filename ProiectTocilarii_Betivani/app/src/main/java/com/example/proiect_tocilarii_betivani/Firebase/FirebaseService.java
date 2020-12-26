package com.example.proiect_tocilarii_betivani.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.proiect_tocilarii_betivani.Util.Expences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseService {
    private static final String EXPENCE_TABLE = "Expences";

    private DatabaseReference database;
    private  static FirebaseService firebaseService;

    private FirebaseService(){
        database = FirebaseDatabase.getInstance().getReference(EXPENCE_TABLE);
    }

    public static FirebaseService getInstance(){
        if(firebaseService == null){
            synchronized (FirebaseService.class){
                if(firebaseService == null){
                    firebaseService = new FirebaseService();
                }
            }
        }
        return firebaseService;
    }

    public void attachDataChangeEventListener(final CallBack<List<Expences>> callBack){
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Expences> expencesList = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren()){
                    Expences expences = data.getValue(Expences.class);
                    if (expences != null){
                        expencesList.add(expences);
                    }
                }
                callBack.runResultOnUiThread(expencesList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("FirebaseService", "Data is not Aveilable");
            }
        });
    }
}
