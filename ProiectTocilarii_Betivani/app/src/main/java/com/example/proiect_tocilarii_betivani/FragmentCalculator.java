package com.example.proiect_tocilarii_betivani;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proiect_tocilarii_betivani.Firebase.CallBack;
import com.example.proiect_tocilarii_betivani.Firebase.FirebaseService;
import com.example.proiect_tocilarii_betivani.Util.Expences;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FragmentCalculator extends Fragment {

    private Spinner bank;
    private RadioGroup accountType;
    private TextInputEditText amount;
    private TextInputEditText period;
    private Button calculate;
    private TextView conclusion;
    private TextView monthly;
    private TextView yearly;
    private TextView total;

    List<Expences> expences;
    Expences curentExpence;
    
    private FirebaseService firebaseService;

    public FragmentCalculator() {
        // Required empty public constructor
    }

    public static FragmentCalculator newInstance() {
        FragmentCalculator fragment = new FragmentCalculator();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        initComponents(view);
        firebaseService = FirebaseService.getInstance();
        firebaseService.attachDataChangeEventListener(dataChangeCallback());
        calculate.setOnClickListener(calculateEvent());
        return view;
    }

    private View.OnClickListener calculateEvent() {
        return  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valid()){
                    if(accountType.getCheckedRadioButtonId() == R.id.calculator_rb_credit){
                        populateForCredit();
                    }
                    else {
                        populateForDeposit();
                    }
                }
            }
        };
    }

    private CallBack<List<Expences>> dataChangeCallback() {
        return new CallBack<List<Expences>>() {
            @Override
            public void runResultOnUiThread(List<Expences> result) {
                if (result != null) {
                    expences.clear();
                    expences.addAll(result);
                    populateSpinner();
                }
            }
        };
    }

    private void populateSpinner() {
        ArrayAdapter<Expences> adapter = new ArrayAdapter<Expences>(getContext().getApplicationContext(),
                android.R.layout.simple_spinner_item, expences);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bank.setAdapter(adapter);
    }

    private void populateForDeposit() {
        double am = Double.parseDouble(amount.getText().toString().trim());
        double per = Double.parseDouble(period.getText().toString().trim());
        double perMon = am*curentExpence.getDepositRate()/100 - curentExpence.getDepositCommission();

        conclusion.setText("The profit you will have for that amount:");
        monthly.setText(new DecimalFormat("##.##").format(perMon));
        yearly.setText(new DecimalFormat("##.##").format(perMon*12));
        total.setText(new DecimalFormat("##.##").format(perMon*per));
    }

    private void populateForCredit() {
        double am = Double.parseDouble(amount.getText().toString().trim());
        double per = Double.parseDouble(period.getText().toString().trim());
        double fixPerMonth = am/per;
        double totPerMon = fixPerMonth + fixPerMonth*curentExpence.getCreditRate()/100 + curentExpence.getCreditCommission();

        conclusion.setText("The amount you will have to pay back:");
        monthly.setText(new DecimalFormat("###.##").format(totPerMon));
        yearly.setText(new DecimalFormat("###.##").format(totPerMon*12));
        total.setText(new DecimalFormat("###.##").format(totPerMon*per));
    }

    private boolean valid() {
        if(bank.getSelectedItemPosition() == -1){
            Toast.makeText(getContext().getApplicationContext(), R.string.toaster_noBank, Toast.LENGTH_LONG).show();
            return false;
        }else{
            curentExpence = expences.get(bank.getSelectedItemPosition());
        }
        if(accountType.getCheckedRadioButtonId() == R.id.calculator_rb_credit){
            if(amount.getText() == null || Double.parseDouble(amount.getText().toString().trim()) < curentExpence.getMinCreditAmount()){
                Toast.makeText(getContext().getApplicationContext(),
                        getContext().getApplicationContext().getString(R.string.toaster_invalid_amount, curentExpence.getMinCreditAmount()),
                        Toast.LENGTH_LONG).show();
                return false;
            }
            if(period.getText() == null || Double.parseDouble(period.getText().toString().trim()) < curentExpence.getMinCreditPeriod()){
                Toast.makeText(getContext().getApplicationContext(),
                        getContext().getApplicationContext().getString(R.string.toaster_invalid_period, curentExpence.getMinCreditPeriod()),
                        Toast.LENGTH_LONG).show();
                return false;
            }
        }
        else {
            if(amount.getText() == null || Double.parseDouble(amount.getText().toString().trim()) < curentExpence.getMinDepositAmount()){
                Toast.makeText(getContext().getApplicationContext(),
                        getContext().getApplicationContext().getString(R.string.toaster_invalid_amount, curentExpence.getMinDepositAmount()),
                        Toast.LENGTH_LONG).show();
                return false;
            }
            if(period.getText() == null || Double.parseDouble(period.getText().toString().trim()) < curentExpence.getMinDepositPeriod()){
                Toast.makeText(getContext().getApplicationContext(),
                        getContext().getApplicationContext().getString(R.string.toaster_invalid_period, curentExpence.getMinDepositPeriod()),
                        Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }

    private void initComponents(View view) {
        bank = view.findViewById(R.id.calculator_bank_spinner);
        accountType = view.findViewById(R.id.calculator_type_rg);
        amount = view.findViewById(R.id.calculator_amount_tiet);
        period = view.findViewById(R.id.calculator_period_tiet);
        calculate = view.findViewById(R.id.calculator_calculate_btn);
        conclusion = view.findViewById(R.id.calculator_conclusion_tv);
        monthly = view.findViewById(R.id.calculator_monthlyV_tv);
        yearly = view.findViewById(R.id.calculator_yearlyV_tv);
        total = view.findViewById(R.id.calculator_totalV_tv);
        expences = new ArrayList<>();
    }
}