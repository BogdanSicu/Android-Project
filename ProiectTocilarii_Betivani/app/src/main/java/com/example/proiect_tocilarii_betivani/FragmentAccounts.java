package com.example.proiect_tocilarii_betivani;

import android.accounts.Account;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.proiect_tocilarii_betivani.Util.AccountType;
import com.example.proiect_tocilarii_betivani.Util.Acount;
import com.example.proiect_tocilarii_betivani.Util.ListaAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentAccounts extends Fragment {
    private ListView lv_acounts;
    private List<Acount> acounts;

    public FragmentAccounts(){}

    public static FragmentAccounts newInstance(int type_key) {
        Bundle bundle = new Bundle();
        FragmentAccounts fragment = new FragmentAccounts();
        bundle.putInt("ACOUNTS_KEY", type_key);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_accounts, container, false);
        initComponents(rootView, inflater);
        return rootView;
    }

    private void initComponents(View rootView, LayoutInflater inflater) {
        lv_acounts = rootView.findViewById(R.id.accounts_fragment_listview);
        if(getArguments()!=null){
            if(getArguments().getInt("ACOUNTS_KEY")==100){
                acounts = new ArrayList<>();
                acounts.add(new Acount(AccountType.valueOf("Deposit"), "BCRO 0111 0222 3333 4444",
                        14954.4f, 55.5f,  "24/7/2020","24/12/2021" , "Alex", "BCR"));
                acounts.add(new Acount(AccountType.valueOf("Credit"), "BCRO 0111 0222 3223 4444",
                        22954.4f,-44.5f, "25/3/2018","31/12/2020", "Alex321", "BNR"));
            }
            if(getArguments().getInt("ACOUNTS_KEY")==101){
                acounts = new ArrayList<>();
                acounts.add(new Acount(AccountType.valueOf("Credit"), "BCRO 0111 0222 3223 4444",
                        22954.4f,-44.5f, "25/3/2018","31/12/2020", "Alex321", "BNR"));
            }
            if(getArguments().getInt("ACOUNTS_KEY")==102){
                acounts = new ArrayList<>();
                acounts.add(new Acount(AccountType.valueOf("Deposit"), "BCRO 0111 0222 3333 4444",
                        14954.4f, 55.5f,  "24/7/2020","24/12/2021" , "Alex", "BCR"));
            }
        }
        if(getContext()!=null){
            ListaAdapter adapter = new ListaAdapter(getContext().getApplicationContext(), R.layout.lv_row_view, acounts, inflater);
            lv_acounts.setAdapter(adapter);
        }
    }
}