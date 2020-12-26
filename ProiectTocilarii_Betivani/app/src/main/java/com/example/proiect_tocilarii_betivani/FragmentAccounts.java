package com.example.proiect_tocilarii_betivani;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.proiect_tocilarii_betivani.LocalDataBase.Services.AccountService;
import com.example.proiect_tocilarii_betivani.Util.AccountType;
import com.example.proiect_tocilarii_betivani.Util.Acount;
import com.example.proiect_tocilarii_betivani.Util.ListaAdapter;
import com.example.proiect_tocilarii_betivani.asyncTask.Callback;

import java.util.ArrayList;
import java.util.List;

public class FragmentAccounts extends Fragment {
    private ListView lv_acounts;
    private List<Acount> acounts = new ArrayList<>();
    private TextView listTypeAccounts;
    private AccountService accountService;
    private ListaAdapter adapter;

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
        listTypeAccounts = rootView.findViewById(R.id.accounts_textview);

        lv_acounts = rootView.findViewById(R.id.accounts_fragment_listview);
        adapter = new ListaAdapter(getContext().getApplicationContext(), R.layout.lv_row_view, acounts, inflater);


        accountService = new AccountService(rootView.getContext());



        initComponents();

        return rootView;
    }

    private Callback<List<Acount>> getAllAccountsFromDB(){
        return new Callback<List<Acount>>() {
            @Override
            public void runResultOnUiThread(List<Acount> result) {
                if(result != null){
                    acounts.clear();
                    acounts.addAll(result);
                    lv_acounts.setAdapter(adapter);
                }
            }
        };
    }



    private void initComponents() {

        if(getArguments()!=null){
            if(getArguments().getInt("ACOUNTS_KEY")==100){
                listTypeAccounts.setText("Your accounts");
                accountService.getAllAccounts(getAllAccountsFromDB());
            }
            if(getArguments().getInt("ACOUNTS_KEY")==101){
                listTypeAccounts.setText("Your credit accounts");
                accountService.getAllAccounts(getAllAccountsFromDB());
            }
            if(getArguments().getInt("ACOUNTS_KEY")==102){
                listTypeAccounts.setText("Your deposit accounts");
                accountService.getAllAccounts(getAllAccountsFromDB());
            }
        }

    }
}