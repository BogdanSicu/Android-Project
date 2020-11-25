package com.example.proiect_tocilarii_betivani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        // afiseaza aplicatia pe tot ecranul
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        editText = findViewById(R.id.editLoginNumber);

        //pentru a adauga alta functionalitate butonului de enter de pe keyboard
        editText.setOnEditorActionListener(enterButton);
    }

    //pentru a adauga alta functionalitate butonului de enter de pe keyboard
    private TextView.OnEditorActionListener enterButton = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                if (v.getText().toString().equals("1234")) {
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
}