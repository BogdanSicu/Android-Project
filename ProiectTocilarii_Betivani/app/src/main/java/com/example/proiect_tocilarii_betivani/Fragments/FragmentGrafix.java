package com.example.proiect_tocilarii_betivani.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proiect_tocilarii_betivani.LocalDataBase.Services.RatesService;
import com.example.proiect_tocilarii_betivani.Util.GrafixView;
import com.example.proiect_tocilarii_betivani.Util.Rates;
import com.example.proiect_tocilarii_betivani.asyncTask.Callback;

import java.util.ArrayList;
import java.util.List;


public class FragmentGrafix extends Fragment {


    public FragmentGrafix() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentGrafix newInstance() {
        FragmentGrafix fragment = new FragmentGrafix();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return new GrafixView(getContext().getApplicationContext());
    }





}