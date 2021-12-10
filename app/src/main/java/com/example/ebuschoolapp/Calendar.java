package com.example.ebuschoolapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Calendar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    private CalendarView mCalendarView;

    // Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        //Change Status Bar Color
        getWindow().setStatusBarColor(ContextCompat.getColor(Calendar.this, R.color.background_header_color));


        //Hooks

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        textView = findViewById(R.id.textView);
        toolbar = findViewById(R.id.toolbar);

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Intent intent = new Intent(Calendar.this, Event.class);
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


    }

    // Make Menu Return
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent home = new Intent(Calendar.this, MainActivity.class);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;

            case R.id.nav_logout:
                Intent logout = new Intent(Calendar.this, Login.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                break;



            case R.id.nav_user_profile:
                Intent studentProfile = new Intent(Calendar.this,StudentProfile.class);
                startActivity(new Intent(getApplicationContext(), StudentProfile.class));
                finish();
                break;

            case R.id.nav_student_info:
                Intent studentInfo = new Intent(Calendar.this,StudentInfo.class);
                startActivity(new Intent(getApplicationContext(), StudentInfo.class));
                finish();
                break;

            case R.id.nav_calendar:
                Intent calendar = new Intent(Calendar.this, Calendar.class);
                startActivity(new Intent(getApplicationContext(), Calendar.class));
                finish();
                break;

            case R.id.nav_resetUserPassword:
                Intent reset = new Intent(Calendar.this, ResetPassword.class);
                startActivity(new Intent(getApplicationContext(), ResetPassword.class));
                finish();
                break;


        }

        return true;
    }
}