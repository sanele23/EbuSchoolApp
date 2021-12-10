package com.example.ebuschoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth auth;


    // Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView textView;


    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();


        //Change Status Bar Color
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.background_header_color));


        //Hooks

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        textView = findViewById(R.id.textView);
        toolbar = findViewById(R.id.toolbar);

        // User Profile Click Activity from Home page to Student Info Activity

        ImageView StudentInfo = findViewById(R.id.profile_img);

        StudentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StudentInfo.class));
                finish();
            }
        });

        // User Profile Click Activity from Home page to Calendar Activity
        ImageView Calendar = findViewById(R.id.calendar_main_img);
        Calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Calendar.class));
                finish();
            }
        });

        // Student Profile Click Activity from Home page to StudentProfile (
        // Only for test is using the courses icon then I will change it to the profile Icon when administrators profile are ready)

        ImageView StudentProfile = findViewById(R.id.course_main_img);

        StudentProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StudentProfile.class));
                finish();
            }
        });


        Calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Calendar.class));
                finish();
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
                Intent home = new Intent(MainActivity.this, MainActivity.class);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;

            case R.id.nav_logout:
                Intent login = new Intent(MainActivity.this, Login.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                break;

            case R.id.nav_student_info:
                Intent studentInfo = new Intent(MainActivity.this, StudentInfo.class);
                startActivity(new Intent(getApplicationContext(), StudentInfo.class));
                finish();
                break;

            case R.id.nav_user_profile:
                Intent studentProfile = new Intent(MainActivity.this, StudentProfile.class);
                startActivity(new Intent(getApplicationContext(), StudentProfile.class));
                finish();
                break;

            case R.id.nav_calendar:
                Intent calendar = new Intent(MainActivity.this, Calendar.class);
                startActivity(new Intent(getApplicationContext(), Calendar.class));
                finish();
                break;

            case R.id.nav_resetUserPassword:
                Intent reset= new Intent (MainActivity.this,ResetPassword.class);
                startActivity(new Intent(getApplicationContext(), ResetPassword.class));
                finish();
                break;


        }


        // reset password event listener (on the menu options)
        if (menuItem.getItemId() == R.id.nav_resetUserPassword) {
            startActivity(new Intent(getApplicationContext(), ResetPassword.class));
        }

        return super.onOptionsItemSelected(menuItem);
    }
}
