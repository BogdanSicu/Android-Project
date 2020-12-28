package com.example.proiect_tocilarii_betivani;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class FragmentSettings extends Fragment {

    private Spinner settingTheme;
    private Button settingSave;
    private List<String> listThemes;

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
                View mortiiMatii = getActivity().findViewById(R.id.toolbar);
                View mortiiMatii2 = getActivity().findViewById(R.id.main_app_head);

                if(settingTheme.getSelectedItemPosition()==0){
                    mortiiMatii.setBackgroundResource(R.drawable.gradient_turquoise_green);
                    mortiiMatii2.setBackgroundResource(R.drawable.gradient_turquoise_green);

                } else if(settingTheme.getSelectedItemPosition()==1){
                    mortiiMatii.setBackgroundResource(R.drawable.gradient_dark_green);
                    mortiiMatii2.setBackgroundResource(R.drawable.gradient_dark_green);

                } else if(settingTheme.getSelectedItemPosition()==2){
                    mortiiMatii.setBackgroundResource(R.drawable.gradient_dark_blue);
                    mortiiMatii2.setBackgroundResource(R.drawable.gradient_dark_blue);

                } else if(settingTheme.getSelectedItemPosition()==3){
                    mortiiMatii.setBackgroundResource(R.drawable.gradient_blue_green);
                    mortiiMatii2.setBackgroundResource(R.drawable.gradient_blue_green);

                } else if(settingTheme.getSelectedItemPosition()==4){
                    mortiiMatii.setBackgroundResource(R.drawable.gradient_purple_blue);
                    mortiiMatii2.setBackgroundResource(R.drawable.gradient_purple_blue);

                } else if(settingTheme.getSelectedItemPosition()==5){
                    mortiiMatii.setBackgroundResource(R.drawable.gradient_pink);
                    mortiiMatii2.setBackgroundResource(R.drawable.gradient_pink);
                }
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
    }
}