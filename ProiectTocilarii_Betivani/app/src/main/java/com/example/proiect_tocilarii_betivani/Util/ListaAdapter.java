package com.example.proiect_tocilarii_betivani.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

//pentru tematica --------------------------------------------------
    private static final String aSmallPriceToPayForSalvation = "preferinte";
    private static final String prefferedTheme = "theme";
    private String loadTheme;

    private ImageView accountTypeIcon;
    private ImageView balanceIcon;
    private ImageView bankNameIcon;
    private ImageView ibanIcon;
    private ImageView expireDateIcon;

//    --------------------------------------------------

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



//        pentru tematica --------------------------------------------------
            initIcons(view);

            SharedPreferences preferinte =getContext().getSharedPreferences(aSmallPriceToPayForSalvation, Context.MODE_PRIVATE);
            loadTheme = preferinte.getString(prefferedTheme, "");

            if(loadTheme.equals("") || loadTheme.equals("Turquoise&Green gradient") || loadTheme.equals("Blue&Green gradient")){
                accountTypeIcon.setImageResource(R.drawable.icon_flow_blue);
                balanceIcon.setImageResource(R.drawable.icon_balance_blue);
                bankNameIcon.setImageResource(R.drawable.icon_bank_blue);
                expireDateIcon.setImageResource(R.drawable.icon_expire_blue);
                ibanIcon.setImageResource(R.drawable.icon_card_blue);
            }
            else if(loadTheme.equals("Purple&Blue gradient") || loadTheme.equals("Dark&Green gradient") || loadTheme.equals("Dark&Blue gradient")){
                accountTypeIcon.setImageResource(R.drawable.icon_flow_color);
                balanceIcon.setImageResource(R.drawable.icon_balance_color);
                bankNameIcon.setImageResource(R.drawable.icon_bank_color);
                expireDateIcon.setImageResource(R.drawable.icon_expire_color);
                ibanIcon.setImageResource(R.drawable.icon_card_color);

            } else if( loadTheme.equals("Pink-gradient")){
                accountTypeIcon.setImageResource(R.drawable.icon_flow_black_pls);
                balanceIcon.setImageResource(R.drawable.icon_balance_black);
                bankNameIcon.setImageResource(R.drawable.icon_bank_black);
                expireDateIcon.setImageResource(R.drawable.icon_expire_black);
                ibanIcon.setImageResource(R.drawable.icon_card_black);
            }
//        --------------------------------------------------

        }
        return view;
    }


    private void initIcons(View view){
        accountTypeIcon = view.findViewById(R.id.lv_row_typeIcon);
        balanceIcon = view.findViewById(R.id.lv_row_balanceIcon);
        bankNameIcon = view.findViewById(R.id.lv_row_bankIcon);
        expireDateIcon = view.findViewById(R.id.lv_row_dateIcon);
        ibanIcon = view.findViewById(R.id.lv_row_ibanIcon);
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
