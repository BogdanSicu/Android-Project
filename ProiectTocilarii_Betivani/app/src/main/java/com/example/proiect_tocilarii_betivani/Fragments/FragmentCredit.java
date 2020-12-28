package com.example.proiect_tocilarii_betivani.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.proiect_tocilarii_betivani.R;
import com.example.proiect_tocilarii_betivani.Util.Acount;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class FragmentCredit extends Fragment {
    public static final String CREDIT_ACCOUNT_KEY = "credit_account_key";

    TextView holder;
    TextView iban;
    TextView balance;
    TextView rate;
    TextView create;
    TextView expire;
    TextInputEditText amount;
    Button pay;

    Acount acount;
    public FragmentCredit() {
        // Required empty public constructor
    }

    public static FragmentCredit newInstance(Acount acount) {
        FragmentCredit fragment = new FragmentCredit();
        Bundle args = new Bundle();
        args.putParcelable(CREDIT_ACCOUNT_KEY, acount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_credit, container, false);
        initComponents(view);
        populateComponents();
        pay.setOnClickListener(peyClickLitener());
        return view;
    }

    private View.OnClickListener peyClickLitener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo
            }
        };

    }

    private void populateComponents() {
        holder.setText(acount.getHolder());
        iban.setText(acount.getIBAN());
        balance.setText(new DecimalFormat("##.##").format(acount.getBalance()));
        rate.setText(new DecimalFormat("##.##").format(acount.getRate()));
        create.setText(acount.getCreateDate());
        expire.setText(String.valueOf(acount.getPeriod()));
    }

    private void initComponents(View view) {
        holder = view.findViewById(R.id.credit_holder_tv);
        iban = view.findViewById(R.id.credit_iban_tv);
        balance = view.findViewById(R.id.credit_balance_tv);
        rate = view.findViewById(R.id.credit_rate_tv);
        create = view.findViewById(R.id.credit_create_tv);
        expire = view.findViewById(R.id.credit_expiration_tv);
        amount = view.findViewById(R.id.credit_sum_tiet);
        pay  =view.findViewById(R.id.credit_pay_button);

        if(getArguments()!=null){
            acount = getArguments().getParcelable(CREDIT_ACCOUNT_KEY);
        }
    }
}