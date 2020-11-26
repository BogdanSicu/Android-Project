package com.example.proiect_tocilarii_betivani;

import androidx.appcompat.app.AppCompatActivity;

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

public class LoginActivity extends AppCompatActivity {

    private View decorView;
    // Hide both the navigation bar and the status bar.
    // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
    // a general rule, you should design your app to hide the status bar whenever you
    // hide the navigation bar.


    private EditText editText;
    private static final String aSmallPriceToPayForSalvation = "preferinte";
    private static final String password = "password";
    private String loadPassword;


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


        editText = findViewById(R.id.editLoginNumber);

        //pentru a adauga alta functionalitate butonului de enter de pe keyboard
        editText.setOnEditorActionListener(enterButton);

        //adaugam in fisierul de preferinte o parola care este hardcodata
        //trebuie mai intai sa adaugam numele preferintelor noastre "preferinte" si tipul in care este salvat
        SharedPreferences preferinte = getSharedPreferences(aSmallPriceToPayForSalvation, MODE_PRIVATE);
        //editam aceste preferinte
        SharedPreferences.Editor editor = preferinte.edit();

        editor.putString(password, "12345");
        //aplicam schimbarile
        editor.apply();
    }

    //pentru a adauga alta functionalitate butonului de enter de pe keyboard
    private TextView.OnEditorActionListener enterButton = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                SharedPreferences preferinte = getSharedPreferences(aSmallPriceToPayForSalvation, MODE_PRIVATE);
                loadPassword = preferinte.getString(password,"");
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