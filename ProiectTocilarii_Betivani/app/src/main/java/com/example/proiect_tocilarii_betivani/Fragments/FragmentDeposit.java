package com.example.proiect_tocilarii_betivani.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.proiect_tocilarii_betivani.R;
import com.example.proiect_tocilarii_betivani.Util.Acount;

import java.text.DecimalFormat;

public class FragmentDeposit extends Fragment {
    public static final String DEPOSIT_ACCOUNT_KEY = "deposit_account_key";

    TextView holder;
    TextView iban;
    TextView balance;
    TextView rate;
    TextView create;
    TextView expire;
    TextView maxValue;
    ProgressBar progressBar;

    Acount acount;
    public FragmentDeposit() {
        // Required empty public constructor
    }

    public static FragmentDeposit newInstance(Acount acount) {
        FragmentDeposit fragment = new FragmentDeposit();
        Bundle args = new Bundle();
        args.putParcelable(DEPOSIT_ACCOUNT_KEY, acount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deposit, container, false);
        initComponents(view);
        populateComponents();
        return view;
    }

    private void populateComponents() {
        holder.setText(acount.getHolder());
        iban.setText(acount.getIBAN());
        balance.setText(new DecimalFormat("##.##").format(acount.getBalance()));
        rate.setText(new DecimalFormat("##.##").format(acount.getRate()));
        create.setText(acount.getCreateDate());
        expire.setText(String.valueOf(acount.getPeriod()));
        maxValue.setText(new DecimalFormat("##.##").format(10000));

        progressBar.setMax(30000);
        progressBar.setProgress(acount.getBalance()>10000 ? 10000:(int)acount.getBalance());
    }


    private void initComponents(View view) {
        holder = view.findViewById(R.id.deposit_holder_tv);
        iban = view.findViewById(R.id.deposit_iban_tv);
        balance = view.findViewById(R.id.deposit_balance_tv);
        rate = view.findViewById(R.id.deposit_rate_tv);
        create = view.findViewById(R.id.deposit_create_tv);
        expire = view.findViewById(R.id.deposit_expiration_tv);
        maxValue = view.findViewById(R.id.deposit_maxValue);
        progressBar = view.findViewById(R.id.deposit_progressBar);

        if(getArguments()!=null){
            acount = getArguments().getParcelable(DEPOSIT_ACCOUNT_KEY);
        }
    }
}