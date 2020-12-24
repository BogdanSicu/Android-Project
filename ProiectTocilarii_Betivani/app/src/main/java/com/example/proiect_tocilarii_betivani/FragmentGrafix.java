package com.example.proiect_tocilarii_betivani;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proiect_tocilarii_betivani.Util.GrafixView;
import com.example.proiect_tocilarii_betivani.Util.Rates;

import java.util.ArrayList;
import java.util.List;


public class FragmentGrafix extends Fragment {
    private List<Rates> rates;

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
        initList();
        return new GrafixView(getContext().getApplicationContext(), rates);
    }

    private void initList() {
        rates = new ArrayList<>();
        rates.add(new Rates("BCR", 3, 0.6f));
        rates.add(new Rates("BRD", 3, 0.6f));
        rates.add(new Rates("BNR", 3, 0.6f));
    }
}