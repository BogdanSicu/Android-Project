package com.example.proiect_tocilarii_betivani.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.proiect_tocilarii_betivani.LocalDataBase.Services.AccountService;
import com.example.proiect_tocilarii_betivani.R;
import com.example.proiect_tocilarii_betivani.Util.AccountType;
import com.example.proiect_tocilarii_betivani.Util.Acount;
import com.example.proiect_tocilarii_betivani.asyncTask.Callback;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class FragmentAddAccount extends Fragment {
    private TextInputEditText holder;
    private TextInputEditText bank;
    private TextInputEditText iban;
    private TextInputEditText rate;
    private TextInputEditText createDate;
    private TextInputEditText period;
    private TextInputEditText balance;
    private RadioGroup accountType;
    private Button add;

    AccountService accountService;

    //pentru tematica --------------------------------------
    private static final String aSmallPriceToPayForSalvation = "preferinte";
    private static final String prefferedTheme = "theme";
    private String loadTheme;
// --------------------------------------

    public FragmentAddAccount() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentAddAccount newInstance() {
        FragmentAddAccount fragment = new FragmentAddAccount();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_account, container, false);
        initComponents(view);
        //pentru tematica --------------------------------------------------
        SharedPreferences preferinte =getContext().getSharedPreferences(aSmallPriceToPayForSalvation, Context.MODE_PRIVATE);
        loadTheme = preferinte.getString(prefferedTheme, "");

        if(loadTheme.equals("")){
            add.setBackgroundResource(R.drawable.button_turquoise_green);

        }else if(loadTheme.equals("Turquoise&Green gradient")){
            add.setBackgroundResource(R.drawable.button_turquoise_green);
        }
        else if(loadTheme.equals("Dark&Green gradient")){
            add.setBackgroundResource(R.drawable.button_dark_green);

        } else if(loadTheme.equals("Dark&Blue gradient")){
            add.setBackgroundResource(R.drawable.button_dark_blue);

        } else if(loadTheme.equals("Blue&Green gradient")){
            add.setBackgroundResource(R.drawable.button_blue_green);

        } else if(loadTheme.equals("Purple&Blue gradient")){
            add.setBackgroundResource(R.drawable.button_purple_blue);

        } else if(loadTheme.equals("Pink-gradient")){
            add.setBackgroundResource(R.drawable.button_pink);
        }
//        --------------------------------------------------
        accountService = new AccountService(getContext().getApplicationContext());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valid()){
                    Acount acount = createFromView();
                    accountService.insertAccount(InsertOneAccount(), acount);
                }
            }
        });
        return view;
    }

    private Callback<Acount> InsertOneAccount(){
        return new Callback<Acount>() {
            @Override
            public void runResultOnUiThread(Acount result) {
                if(result != null) {
                    Toast.makeText(getContext().getApplicationContext(),
                            "Account added", Toast.LENGTH_LONG).show();
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        };
    }

    private boolean valid() {
        if(holder.getText()==null || (holder.getText()!=null && String.valueOf(holder.getText()).trim().isEmpty())){
            Toast.makeText(getContext().getApplicationContext(),
                    "Please enter holder name", Toast.LENGTH_LONG).show();
            return false;
        }
        if(bank.getText()==null || (bank.getText()!=null && String.valueOf(bank.getText()).trim().isEmpty())){
            Toast.makeText(getContext().getApplicationContext(),
                    "Please enter bank", Toast.LENGTH_LONG).show();
            return false;
        }
        if(iban.getText()==null || (iban.getText()!=null && String.valueOf(iban.getText()).trim().isEmpty())){
            Toast.makeText(getContext().getApplicationContext(),
                    "Please enter IBAN", Toast.LENGTH_LONG).show();
            return false;
        }
        if(rate.getText()==null ||
                (rate.getText()!=null && !String.valueOf(rate.getText()).trim().isEmpty() && (Double.parseDouble(String.valueOf(rate.getText())) == 0))
                || rate.getText()!=null && String.valueOf(rate.getText()).trim().isEmpty()){

            Toast.makeText(getContext().getApplicationContext(),
                    "Please enter valid rate", Toast.LENGTH_LONG).show();
            return false;
        }
        if(createDate.getText()==null || (createDate.getText()!=null && String.valueOf(createDate.getText()).trim().isEmpty())){
            Toast.makeText(getContext().getApplicationContext(),
                    "Please enter the create date", Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            try{
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                Date date = format.parse(String.valueOf(createDate.getText()));
            } catch (Exception e) {
                Toast.makeText(getContext().getApplicationContext(),
                        "Please enter a valid date", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        if(period.getText()==null ||
                (period.getText()!=null && !String.valueOf(period.getText()).trim().isEmpty() && (Double.parseDouble(String.valueOf(period.getText())) == 0))
                || period.getText()!=null && String.valueOf(period.getText()).trim().isEmpty()){
            Toast.makeText(getContext().getApplicationContext(),
                    "Please enter valid period", Toast.LENGTH_LONG).show();
            return false;
        }
        if(balance.getText()==null ||
                (rate.getText()!=null && !String.valueOf(balance.getText()).trim().isEmpty() && (Double.parseDouble(String.valueOf(balance.getText())) == 0))
                || balance.getText()!=null && String.valueOf(balance.getText()).trim().isEmpty()){
            Toast.makeText(getContext().getApplicationContext(),
                    "Please enter valid balance", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private Acount createFromView() {
        Acount acount = new  Acount();
        acount.setHolder(String.valueOf(holder.getText()));
        acount.setBank(String.valueOf(bank.getText()));
        acount.setIBAN(String.valueOf(iban.getText()));
        acount.setRate(Float.parseFloat(String.valueOf(rate.getText())));
        acount.setCreateDate(String.valueOf(createDate.getText()));
        acount.setPeriod(Integer.parseInt(String.valueOf(period.getText())));
        acount.setBalance(Float.parseFloat(String.valueOf(balance.getText())));
        if(accountType.getCheckedRadioButtonId() == R.id.add_type_credit){
            acount.setAccountType(AccountType.Credit);
            acount.setRate((-1)*acount.getRate());
        }
        else{
            acount.setAccountType(AccountType.Deposit);
        }
        return acount;
    }

    private void initComponents(View view) {
        holder = view.findViewById(R.id.add_name_edit);
        bank = view.findViewById(R.id.add_bank_edit);
        iban = view.findViewById(R.id.add_iban_edit);
        rate = view.findViewById(R.id.add_rate_edit);
        createDate = view.findViewById(R.id.add_create_edit);
        period = view.findViewById(R.id.add_period_edit);
        balance = view.findViewById(R.id.add_balance_edit);
        accountType = view.findViewById(R.id.add_type);
        add = view.findViewById(R.id.add_Add);
    }
}