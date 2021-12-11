package com.example.ebuschoolapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Calendar_Admin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // private static final String TAG = "MainActivity";

    private CalendarView mCalendarView;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    // Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_admin);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        //Change Status Bar Color
        getWindow().setStatusBarColor(ContextCompat.getColor(Calendar_Admin.this, R.color.background_header_color));


        //Hooks

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        textView = findViewById(R.id.textView);
        toolbar = findViewById(R.id.toolbar);



        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Intent intent = new Intent(Calendar_Admin.this, Event_Admin.class);
                startActivity(intent);
            }
        });

        //ToolBar
        setSupportActionBar(toolbar);


        //Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.setCheckedItem(R.id.nav_logout);
        navigationView.setCheckedItem(R.id.nav_student_info);
        navigationView.setCheckedItem(R.id.nav_calendar);
        navigationView.setCheckedItem(R.id.nav_resetUserPassword);
        navigationView.setCheckedItem(R.id.nav_user_profile);

    }

    // Make Menu Return
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent backtoadmin = new Intent(Calendar_Admin.this, Admin.class);
            startActivity(backtoadmin);
            finish();
        }


    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {



        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent home = new Intent(Calendar_Admin.this, Admin.class);
                startActivity(new Intent(getApplicationContext(), Admin.class));
                finish();
                break;


            case R.id.nav_logout:
                Intent logout = new Intent(Calendar_Admin.this, Login.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                break;

            case R.id.nav_user_profile:
                Intent adminProfile= new Intent(Calendar_Admin.this, UserProfile_Admin.class);
                startActivity(new Intent(getApplicationContext(), UserProfile_Admin.class));
                finish();
                break;

            case R.id.nav_student_info:
                Intent studentRegistration = new Intent(Calendar_Admin.this,GradingActivity.class);
                startActivity(new Intent(getApplicationContext(), GradingActivity.class));
                finish();
                break;

            case R.id.nav_calendar:
                Intent calendar = new Intent(Calendar_Admin.this, Calendar_Admin.class);
                startActivity(new Intent(getApplicationContext(), Calendar_Admin.class));
                finish();
                break;

            case R.id.nav_resetUserPassword:
                Intent reset = new Intent(Calendar_Admin.this, ResetPassword_Admin.class);
                startActivity(new Intent(getApplicationContext(), ResetPassword_Admin.class));
                finish();
                break;


        }

        return true;
    }


}
