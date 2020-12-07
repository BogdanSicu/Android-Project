package com.example.proiect_tocilarii_betivani;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    private Fragment fragmentCreated;
    private NavigationView navigationView;

    private View decorView;
    // Hide both the navigation bar and the status bar.
    // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
    // a general rule, you should design your app to hide the status bar whenever you
    // hide the navigation bar.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        setContentView(R.layout.activity_main);
//        initComponents();
        openDefaultFragment();
        configNavigation();
    }
    
    // Cum sa faci sa setezi meniul si bara cu butonul pentru meniu
    private void configNavigation() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //seteaza toolbarul ca bara aplicatiei pentru activitatea curenta.
        drawerLayout = findViewById(R.id.drawer_layout);

        // Creaza butonnul pentru deschiderea meniului
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle); // Leaga meniul de buton
        actionBarDrawerToggle.syncState(); // Afiseaza butonul pe toolbar
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

//    Am deschis primul fragment -> cel cu liste
    private void openDefaultFragment()  {
            fragmentCreated = new FragmentAccounts();
            getSupportFragmentManager().beginTransaction().add(R.id.main_frame_container, fragmentCreated).commit();
//            navigationView.setCheckedItem(R.id.drawer_layout);
    }

    //TO DO -> deschiderea fragmentului in functie de ce buton am apasat
    private void openFragment() {
        //se preia managerul de la nivelul appCompatActivity pentru a putea adauga un nou fragment
        //in interiorul unui FrameLayout
        getSupportFragmentManager()
                .beginTransaction()//incepe tranzactia pentru adaugarea fragmentului
                .replace(R.id.main_frame_container, fragmentCreated)//se inlocuieste FrameLayout din content_main.xml cu fisierul xml a fragmentul initializat
                .commit();//se confirma schimbarea
    }

//    TO DO -> nush, help Baljit
    private void initComponents() {
        navigationView = findViewById(R.id.drawer_layout);
        //atasare eveniment de click pe optiunile din meniul lateral
        navigationView.setNavigationItemSelectedListener(addNavigationMenuItemSelectedEvent());
    }

    //TO DO -> butoanele de navigatie si trimisul de intent
    private NavigationView.OnNavigationItemSelectedListener addNavigationMenuItemSelectedEvent() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_item_AllAcounts) {

                }

                //incarcam pe ecran fragmentul corespunzator optiunii selectate
                openFragment();
                //inchidem meniul lateral
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        };
    }
}
