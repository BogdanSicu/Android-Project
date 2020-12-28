package com.example.proiect_tocilarii_betivani.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proiect_tocilarii_betivani.R;

import java.util.Date;
import java.util.List;

public class ListaAdapter extends ArrayAdapter<Acount> {
    private Context context;
    private int resource;
    private List<Acount> acounts;
    private LayoutInflater inflater;

    public ListaAdapter(@NonNull Context context, int resource, @NonNull List<Acount> objects, LayoutInflater inflater) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.acounts = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Acount acount = acounts.get(position);
        if(acount != null){
            addAcountType(view, acount.getAccountType());
            addDate(view, acount.getPeriod());
            addbalance(view, acount.getBalance());
            addBankName(view, acount.getBank());
            addIBAN(view, acount.getIBAN());
        }
        return view;
    }

    private void addAcountType(View view, AccountType accountType) {
        TextView textView = view.findViewById(R.id.lv_row_type);
        String aux = accountType.toString();
        if(aux == null || aux.isEmpty()){
            textView.setText(R.string.lv_row_empty);
        }
        else{
            textView.setText(aux);
        }
    }

    private void addDate(View view, int period) {
        TextView textView = view.findViewById(R.id.lv_row_date);
        String aux = String.valueOf(period);
        if(aux == null || aux.isEmpty()){
            textView.setText(R.string.lv_row_empty);
        }
        else{
            textView.setText(aux);
        }
    }

    private void addbalance(View view, float balance) {
        TextView textView = view.findViewById(R.id.lv_row_balance);
        String aux = String.valueOf(balance);
        if(aux == null || aux.isEmpty()){
            textView.setText(R.string.lv_row_empty);
        }
        else{
            textView.setText(aux);
        }
    }

    private void addBankName(View view, String bank) {
        TextView textView = view.findViewById(R.id.lv_row_bank_name);
        if(bank == null || bank.isEmpty()){
            textView.setText(R.string.lv_row_empty);
        }
        else{
            textView.setText(bank);
        }
    }

    private void addIBAN(View view, String iban) {
        TextView textView = view.findViewById(R.id.lv_row_iban);
        if(iban == null || iban.isEmpty()){
            textView.setText(R.string.lv_row_empty);
        }
        else{
            textView.setText(iban);
        }
    }
}
