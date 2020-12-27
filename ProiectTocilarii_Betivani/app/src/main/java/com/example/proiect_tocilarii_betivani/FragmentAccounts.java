package com.example.proiect_tocilarii_betivani;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
        lv_acounts.setOnItemClickListener(itemSelectedListener());
        adapter = new ListaAdapter(getContext().getApplicationContext(), R.layout.lv_row_view, acounts, inflater);

        accountService = new AccountService(rootView.getContext());
        initComponents();

        return rootView;
    }

    private AdapterView.OnItemClickListener itemSelectedListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Acount acount = acounts.get(position);
                if(acount!=null){
                    if(acount.getAccountType() == AccountType.Credit){
                        Fragment fragment = FragmentCredit.newInstance(acount);
                        openFragment(fragment);
                    }
                    else{
                        Fragment fragment = FragmentDeposit.newInstance(acount);
                        openFragment(fragment);
                    }
                }
            }
        };
    }

    private void openFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()//incepe tranzactia pentru adaugarea fragmentului
                .replace(R.id.main_frame_container, fragment).addToBackStack("PrevFragment")//se inlocuieste FrameLayout din content_main.xml cu fisierul xml a fragmentul initializat
                .commit();//se confirma schimbarea
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