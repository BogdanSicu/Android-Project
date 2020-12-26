package com.example.proiect_tocilarii_betivani.asyncTask;

public interface Callback<R> {

    void runResultOnUiThread(R result);
}
