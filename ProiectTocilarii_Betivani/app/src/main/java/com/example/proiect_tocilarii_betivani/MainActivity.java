package com.example.proiect_tocilarii_betivani;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.proiect_tocilarii_betivani.Fragments.FragmentAccounts;
import com.example.proiect_tocilarii_betivani.Fragments.FragmentCalculator;
import com.example.proiect_tocilarii_betivani.Fragments.FragmentGrafix;
import com.example.proiect_tocilarii_betivani.Fragments.FragmentSettings;
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


//        pentru tematica --------------------------------------------------
    private static final String aSmallPriceToPayForSalvation = "preferinte";
    private static final String prefferedMaxCredit = "max_credit";
    private static final String prefferedTheme = "theme";
    private String loadTheme;
    private int setariFacute=0;
// --------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Afisare pe tot ecranul
        hideBars();

        configNavigation();
        initComponents();

//        pentru tematica --------------------------------------------------
        SharedPreferences preferinte = getSharedPreferences(aSmallPriceToPayForSalvation, MODE_PRIVATE);
        loadTheme = preferinte.getString(prefferedTheme, "");

        View toolbarTheme =  findViewById(R.id.toolbar);

        if(loadTheme.equals("")){
            toolbarTheme.setBackgroundResource(R.drawable.gradient_turquoise_green);

        }else if(loadTheme.equals("Turquoise&Green gradient")){
            toolbarTheme.setBackgroundResource(R.drawable.gradient_turquoise_green);
        }
        else if(loadTheme.equals("Dark&Green gradient")){
            toolbarTheme.setBackgroundResource(R.drawable.gradient_dark_green);

        } else if(loadTheme.equals("Dark&Blue gradient")){
            toolbarTheme.setBackgroundResource(R.drawable.gradient_dark_blue);

        } else if(loadTheme.equals("Blue&Green gradient")){
            toolbarTheme.setBackgroundResource(R.drawable.gradient_blue_green);

        } else if(loadTheme.equals("Purple&Blue gradient")){
            toolbarTheme.setBackgroundResource(R.drawable.gradient_purple_blue);

        } else if(loadTheme.equals("Pink-gradient")){
            toolbarTheme.setBackgroundResource(R.drawable.gradient_pink);
        }
//         --------------------------------------------------

        openDefaultFragment(savedInstanceState);

    }

    private void initComponents() {

        navigationView = findViewById(R.id.nav_view);
        //atasare eveniment de click pe optiunile din meniul lateral
        navigationView.setNavigationItemSelectedListener(addNavigationMenuItemSelectedEvent());

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
                R.string.navigation_drawer_close){

//        pentru tematica --------------------------------------------------
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if(setariFacute == 0){
                    View headTheme = findViewById(R.id.main_app_head);

                    if(loadTheme.equals("")){
                        headTheme.setBackgroundResource(R.drawable.gradient_turquoise_green);

                    }else if(loadTheme.equals("Turquoise&Green gradient")){
                        headTheme.setBackgroundResource(R.drawable.gradient_turquoise_green);
                    }
                    else if(loadTheme.equals("Dark&Green gradient")){
                        headTheme.setBackgroundResource(R.drawable.gradient_dark_green);

                    } else if(loadTheme.equals("Dark&Blue gradient")){
                        headTheme.setBackgroundResource(R.drawable.gradient_dark_blue);

                    } else if(loadTheme.equals("Blue&Green gradient")){
                        headTheme.setBackgroundResource(R.drawable.gradient_blue_green);

                    } else if(loadTheme.equals("Purple&Blue gradient")){
                        headTheme.setBackgroundResource(R.drawable.gradient_purple_blue);

                    } else if(loadTheme.equals("Pink-gradient")){
                        headTheme.setBackgroundResource(R.drawable.gradient_pink);
                    }
                    setariFacute=1;
                }
//                 --------------------------------------------------
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle); // Leaga meniul de buton
        actionBarDrawerToggle.syncState(); // Afiseaza butonul pe toolbar

    }

    // butoanele de navigatie si trimisul de intent
    private NavigationView.OnNavigationItemSelectedListener addNavigationMenuItemSelectedEvent() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.menu_item_AllAcounts) {
                    fragmentCreated = FragmentAccounts.newInstance(100);//pentru toate conturile
                }
                if(item.getItemId() == R.id.menu_item_CreditAcounts){
                    fragmentCreated = FragmentAccounts.newInstance(101);//pentru conturile de credit
                }
                if(item.getItemId() == R.id.menu_item_DepositAccounts){
                    fragmentCreated = FragmentAccounts.newInstance(102);//pentru conturile de debit
                }
                if(item.getItemId() == R.id.menu_item_Statistics){
                    fragmentCreated = FragmentGrafix.newInstance();
                }
                if(item.getItemId() == R.id.menu_item_Calculate){
                    fragmentCreated = FragmentCalculator.newInstance();
                }
                if(item.getItemId() == R.id.settings_button){
                    fragmentCreated = FragmentSettings.newInstance();
                }
                //incarcam pe ecran fragmentul corespunzator optiunii selectate
                openFragment();
                //inchidem meniul lateral
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        };
    }

    // Am deschis primul fragment -> cel cu liste
    private void openDefaultFragment(Bundle SavedInstanceState)  {
        if(SavedInstanceState == null){
            fragmentCreated = FragmentAccounts.newInstance(100);
            openFragment();
            navigationView.setCheckedItem(R.id.drawer_layout);
        }
    }

    //Deschidem fragmentul ales
    private void openFragment() {
        //se preia managerul de la nivelul appCompatActivity pentru a putea adauga un nou fragment
        //in interiorul unui FrameLayout
        getSupportFragmentManager()
                .beginTransaction()//incepe tranzactia pentru adaugarea fragmentului
                .replace(R.id.main_frame_container, fragmentCreated)//se inlocuieste FrameLayout din content_main.xml cu fisierul xml a fragmentul initializat
                .commit();//se confirma schimbarea
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
    // afiseaza aplicatia pe tot ecranul
    private void hideBars(){
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
    }








}
