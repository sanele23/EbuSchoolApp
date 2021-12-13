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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.auth.User;

public class Admin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //Change Status Bar Color
        getWindow().setStatusBarColor(ContextCompat.getColor(Admin.this, R.color.background_header_color));


        //Hooks

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        textView = findViewById(R.id.textView);
        toolbar = findViewById(R.id.toolbar);



        // User Profile Click Activity from Home page to Student Info Activity

        ImageView StudentInfo = findViewById(R.id.course_main_img);

        StudentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GradingActivity.class));
                finish();
            }
        });

        // Amdin User Profile Click Activity from Home page to User Profile Admin Activity

        ImageView Profile = findViewById(R.id.user_main_img);
                Profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), UserProfile_Admin.class));
                        finish();
                    }
                });

        // User Profile Click Activity from Home page to Calendar Activity
        ImageView Calendar = findViewById(R.id.calendar_main_img);
        Calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Calendar_Admin.class));
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
        navigationView.setCheckedItem(R.id.nav_logout);
        navigationView.setCheckedItem(R.id.nav_student_info);
        navigationView.setCheckedItem(R.id.nav_calendar);
        navigationView.setCheckedItem(R.id.nav_resetUserPassword);

    }
    // Make Menu Return
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START
            )
            ;
        } else {
            super.onBackPressed();
        }
    }
    //New Selection
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent home = new Intent(Admin.this, Admin.class);
                startActivity(new Intent(getApplicationContext(), Admin.class));
                finish();
                break;

            case R.id.nav_logout:
                Intent login = new Intent(Admin.this, Login.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                break;

            case R.id.nav_student_info:
                Intent studentInfo = new Intent(Admin.this, GradingActivity.class);
                startActivity(new Intent(getApplicationContext(), GradingActivity.class));
                finish();
                break;

            case R.id.nav_user_profile:
                Intent adminProfile= new Intent(Admin.this, UserProfile_Admin.class);
                startActivity(new Intent(getApplicationContext(), UserProfile_Admin.class));
                finish();
                break;


            case R.id.nav_calendar:
                Intent calendar_admin = new Intent(Admin.this, Calendar_Admin.class);
                startActivity(new Intent(getApplicationContext(), Calendar_Admin.class));
                finish();
                break;

            case R.id.nav_resetUserPassword:
                Intent reset= new Intent (Admin.this,ResetPassword_Admin.class);
                startActivity(new Intent(getApplicationContext(), ResetPassword_Admin.class));
                finish();
                break;


        }


        // reset password event listener (on the menu options)
        if (menuItem.getItemId() == R.id.nav_resetUserPassword) {
            startActivity(new Intent(getApplicationContext(), ResetPassword_Admin.class));
        }

        return super.onOptionsItemSelected(menuItem);
    }

    public void logoutAdmin(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }


}