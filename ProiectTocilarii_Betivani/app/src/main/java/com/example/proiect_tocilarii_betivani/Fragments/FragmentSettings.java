package com.example.proiect_tocilarii_betivani.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.proiect_tocilarii_betivani.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


public class FragmentSettings extends Fragment {

    private Spinner settingTheme;
    private Button settingSave;
    private List<String> listThemes;
    private TextInputEditText password;
    private TextInputEditText maxCredit;
    private static final String aSmallPriceToPayForSalvation = "preferinte";
    private static final String prefferedPassword = "password";
    private static final String prefferedTheme = "theme";
    private static final String prefferedMaxCredit = "max_credit";


    public static FragmentSettings newInstance() {
        FragmentSettings fragment = new FragmentSettings();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        InitComponents(view);
        populateSpinners();
        settingSave.setOnClickListener(settingsSave());
        return view;
    }

    private View.OnClickListener settingsSave(){
        return  new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //aparent in fragmente tre sa preiei si contextul aplicatiei
                SharedPreferences preferences = getContext().getSharedPreferences(aSmallPriceToPayForSalvation, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                View toolbarTheme = getActivity().findViewById(R.id.toolbar);
                View headTheme = getActivity().findViewById(R.id.main_app_head);

                if(settingTheme.getSelectedItemPosition()==0){
                    toolbarTheme.setBackgroundResource(R.drawable.gradient_turquoise_green);
                    headTheme.setBackgroundResource(R.drawable.gradient_turquoise_green);

                } else if(settingTheme.getSelectedItemPosition()==1){
                    toolbarTheme.setBackgroundResource(R.drawable.gradient_dark_green);
                    headTheme.setBackgroundResource(R.drawable.gradient_dark_green);

                } else if(settingTheme.getSelectedItemPosition()==2){
                    toolbarTheme.setBackgroundResource(R.drawable.gradient_dark_blue);
                    headTheme.setBackgroundResource(R.drawable.gradient_dark_blue);

                } else if(settingTheme.getSelectedItemPosition()==3){
                    toolbarTheme.setBackgroundResource(R.drawable.gradient_blue_green);
                    headTheme.setBackgroundResource(R.drawable.gradient_blue_green);

                } else if(settingTheme.getSelectedItemPosition()==4){
                    toolbarTheme.setBackgroundResource(R.drawable.gradient_purple_blue);
                    headTheme.setBackgroundResource(R.drawable.gradient_purple_blue);

                } else if(settingTheme.getSelectedItemPosition()==5){
                    toolbarTheme.setBackgroundResource(R.drawable.gradient_pink);
                    headTheme.setBackgroundResource(R.drawable.gradient_pink);
                }
                    editor.putString(prefferedTheme, settingTheme.getSelectedItem().toString());

                if(password.getText() != null && password.getText().length()>0){
                    editor.putString(prefferedPassword, password.getText().toString());
                }

                if(maxCredit.getText() != null && maxCredit.getText().length()>0){
                    editor.putString(prefferedMaxCredit, maxCredit.getText().toString());
                }
                editor.apply();

            }
        };
    }

    private void populateSpinners() {
        listThemes.add("Turquoise&Green gradient");
        listThemes.add("Dark&Green gradient");
        listThemes.add("Dark&Blue gradient");
        listThemes.add("Blue&Green gradient");
        listThemes.add("Purple&Blue gradient");
        listThemes.add("Pink-gradient");


        ArrayAdapter<String> adapterMode = new ArrayAdapter<String>(getContext().getApplicationContext(),
                android.R.layout.simple_spinner_item, listThemes);
        adapterMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        settingTheme.setAdapter(adapterMode);

    }

    private void InitComponents(View view){
        listThemes = new ArrayList<>();
        settingTheme = view.findViewById(R.id.fragment_settings_spinner_android_theme);
        settingSave = view.findViewById(R.id.fragment_settings_save);
        password = view.findViewById(R.id.fragment_settings_password_edit);
        maxCredit = view.findViewById(R.id.fragment_settings_credit_limit_edit);
    }
}