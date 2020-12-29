package com.example.proiect_tocilarii_betivani.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proiect_tocilarii_betivani.LocalDataBase.Services.AccountService;
import com.example.proiect_tocilarii_betivani.R;
import com.example.proiect_tocilarii_betivani.Util.Acount;
import com.example.proiect_tocilarii_betivani.asyncTask.Callback;
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
    TextView amount;
    TextView bank;
    Button pay;

    Acount acount;

    private AccountService accountService;
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
        accountService = new AccountService(view.getContext());
        pay.setOnClickListener(peyClickListener());
        return view;
    }

    private View.OnClickListener peyClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(acount.getPeriod() > 0){
                    float sumPerMonth = acount.getBalance()/acount.getPeriod();
                    float totalPerMonth = sumPerMonth - sumPerMonth*acount.getRate()/100;

                    acount.setBalance(totalPerMonth>acount.getBalance() ? 0:acount.getBalance() - totalPerMonth);
                    acount.setPeriod(acount.getPeriod() - 1);
                    accountService.update(updateCallBack(), acount);
                    Toast.makeText(getContext().getApplicationContext(), "Loan payed", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getContext().getApplicationContext(), "You do not have to pay any more", Toast.LENGTH_LONG).show();
                }
            }
        };

    }

    private Callback<Acount> updateCallBack() {
        return  new Callback<Acount>() {
            @Override
            public void runResultOnUiThread(Acount result) {
                if(result != null){
                    populateComponents();
                }
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
        bank.setText(acount.getBank());

        if(acount.getBalance() == 0 || acount.getPeriod() == 0){
            amount.setText(String.valueOf(0));
        }
        else {
            double sumPerMonth = acount.getBalance()/acount.getPeriod();
            double totalPerMonth = sumPerMonth - sumPerMonth*acount.getRate()/100;

            amount.setText(new DecimalFormat("##.##").format(totalPerMonth));
        }
    }

    private void initComponents(View view) {
        holder = view.findViewById(R.id.credit_holder_tv);
        iban = view.findViewById(R.id.credit_iban_tv);
        balance = view.findViewById(R.id.credit_balance_tv);
        rate = view.findViewById(R.id.credit_rate_tv);
        create = view.findViewById(R.id.credit_create_tv);
        expire = view.findViewById(R.id.credit_expiration_tv);
        amount = view.findViewById(R.id.credit_sum_tv);
        pay = view.findViewById(R.id.credit_pay_button);
        bank = view.findViewById(R.id.credit_bank_tv);


        if(getArguments()!=null){
            acount = getArguments().getParcelable(CREDIT_ACCOUNT_KEY);
        }
    }
}