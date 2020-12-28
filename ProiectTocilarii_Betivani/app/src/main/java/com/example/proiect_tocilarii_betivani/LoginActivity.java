package com.example.proiect_tocilarii_betivani;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proiect_tocilarii_betivani.LocalDataBase.Services.AccountService;
import com.example.proiect_tocilarii_betivani.Util.AccountType;
import com.example.proiect_tocilarii_betivani.Util.Acount;
import com.example.proiect_tocilarii_betivani.asyncTask.Callback;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private View decorView;
    // Hide both the navigation bar and the status bar.
    // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
    // a general rule, you should design your app to hide the status bar whenever you
    // hide the navigation bar.


    private EditText editText;
    private static final String aSmallPriceToPayForSalvation = "preferinte";
    private static final String prefferedPassword = "password";
    private static final String prefferedTheme = "theme";
    private static final String prefferedMaxCredit = "max_credit";
    private String loadPassword;
    private String loadTheme;

    private List<Acount> accounts = new ArrayList<>();;
    private AccountService accountService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // afiseaza aplicatia pe tot ecranul
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //pentru a scoate action bar
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener(){
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });

        //mai intai initializam baza de date cu elemente inca din prima activitate
        accountService = new AccountService(getApplicationContext());
        accountService.getAllAccounts(InsertAccountIntoDBCallback());

        editText = findViewById(R.id.editLoginNumber);

        //pentru a adauga alta functionalitate butonului de enter de pe keyboard
        editText.setOnEditorActionListener(enterButton);


        //adaugam in fisierul de preferinte o parola care este hardcodata
        //trebuie mai intai sa adaugam numele preferintelor noastre "preferinte" si tipul in care este salvat
        SharedPreferences preferinte = getSharedPreferences(aSmallPriceToPayForSalvation, MODE_PRIVATE);


        //verificam daca utilizatorul si.a introdus o parola in setari
        //daca nu, punem o parola default in caz ca utilizatorul nu si.a introdus parola din setari
        loadPassword = preferinte.getString(prefferedPassword,"");
        if(loadPassword.equals("") || loadPassword.equals("12345")){
            //editam aceste preferinte
            SharedPreferences.Editor editor = preferinte.edit();

            editor.putString(prefferedPassword, "0000");
            //aplicam schimbarile
            editor.apply();

            Toast.makeText(LoginActivity.this, "The default password is 0000", Toast.LENGTH_LONG).show();
        }

//        //incarcam setarea de theme
//        loadTheme = preferinte.getString(prefferedTheme, "");
//        View mortiiMatii =  findViewById(R.id.toolbar);
//        View mortiiMatii2 = findViewById(R.id.main_app_head);
//        View mortiiMatii3 = findViewById(R.id.activity_login);
//        if(loadTheme.equals("")) {
//            mortiiMatii.setBackgroundResource(R.drawable.gradient_turquoise_green);
//            mortiiMatii2.setBackgroundResource(R.drawable.gradient_turquoise_green);
//            mortiiMatii3.setBackgroundResource(R.drawable.gradient_turquoise_green);
//
//        }else if(loadTheme.equals("Turquoise&Green gradient")){
//            mortiiMatii.setBackgroundResource(R.drawable.gradient_turquoise_green);
//            mortiiMatii2.setBackgroundResource(R.drawable.gradient_turquoise_green);
//            mortiiMatii3.setBackgroundResource(R.drawable.gradient_turquoise_green);
//
//        } else if(loadTheme.equals("Dark&Green gradient")){
//            mortiiMatii.setBackgroundResource(R.drawable.gradient_dark_green);
//            mortiiMatii2.setBackgroundResource(R.drawable.gradient_dark_green);
//            mortiiMatii3.setBackgroundResource(R.drawable.gradient_dark_green);
//
//        } else if(loadTheme.equals("Dark&Blue gradient")){
//            mortiiMatii.setBackgroundResource(R.drawable.gradient_dark_blue);
//            mortiiMatii2.setBackgroundResource(R.drawable.gradient_dark_blue);
//            mortiiMatii3.setBackgroundResource(R.drawable.gradient_dark_blue);
//
//        } else if(loadTheme.equals("Blue&Green gradient")){
//            mortiiMatii.setBackgroundResource(R.drawable.gradient_blue_green);
//            mortiiMatii2.setBackgroundResource(R.drawable.gradient_blue_green);
//            mortiiMatii3.setBackgroundResource(R.drawable.gradient_blue_green);
//
//        } else if(loadTheme.equals("Purple&Blue gradient")){
//            mortiiMatii.setBackgroundResource(R.drawable.gradient_purple_blue);
//            mortiiMatii2.setBackgroundResource(R.drawable.gradient_purple_blue);
//            mortiiMatii3.setBackgroundResource(R.drawable.gradient_purple_blue);
//
//        } else if(loadTheme.equals("Pink-gradient")){
//            mortiiMatii.setBackgroundResource(R.drawable.gradient_pink);
//            mortiiMatii2.setBackgroundResource(R.drawable.gradient_pink);
//            mortiiMatii3.setBackgroundResource(R.drawable.gradient_pink);
//        }

    }

    //pentru a adauga alta functionalitate butonului de enter de pe keyboard
    private TextView.OnEditorActionListener enterButton = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                SharedPreferences preferinte = getSharedPreferences(aSmallPriceToPayForSalvation, MODE_PRIVATE);
                loadPassword = preferinte.getString(prefferedPassword,"");
                if (v.getText().toString().equals(loadPassword)) {
                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main);
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, v.getText().toString() + " is the wrong password", Toast.LENGTH_LONG).show();
                    v.setText("");
                }
            }
            return false;
        }
    };

    //luam toata lista de date si dupa introducem datele in baza de date
    private Callback<List<Acount>> InsertAccountIntoDBCallback(){
        return new Callback<List<Acount>>() {
            @Override
            public void runResultOnUiThread(List<Acount> result) {
                if(result != null){
                    accounts.clear();
                    accounts.addAll(result);
                    if(accounts.isEmpty()) {

                        Acount cont1 = new Acount(AccountType.valueOf("Deposit"), "BCRO 0111 0222 3333 4444",
                                14954.4f, 55.5f, "24/7/2020", "24/12/2021", "Alex", "BCR");
                        Acount cont2 =new Acount(AccountType.valueOf("Credit"), "BCRO 0111 0222 3223 4444",
                                22954.4f, -44.5f, "25/3/2018", "31/12/2020", "Alex21", "BNR");
                        Acount cont3 =new Acount(AccountType.valueOf("Deposit"), "BCRO 0111 0222 3333 4444",
                                14954.4f, 55.5f, "24/7/2020", "24/12/2021", "Alex3", "BCR");
                        Acount cont4 =new Acount(AccountType.valueOf("Credit"), "BCRO 0111 0222 3223 4444",
                                22954.4f, -44.5f, "25/3/2018", "31/12/2020", "Alex23421", "BNR");

                        accountService.insertAccount(InsertOneAccount(), cont1);
                        accountService.insertAccount(InsertOneAccount(), cont2);
                        accountService.insertAccount(InsertOneAccount(), cont3);
                        accountService.insertAccount(InsertOneAccount(), cont4);

                    }
                }
            }
        };
    }

    private Callback<Acount> InsertOneAccount(){
        return new Callback<Acount>() {
            @Override
            public void runResultOnUiThread(Acount result) {
//                if(result != null) {
//
//                }
            }
        };
    }



    //pentru a scoate action bar
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }
    //pentru a scoate action bar
    private int hideSystemBars() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
    }


}