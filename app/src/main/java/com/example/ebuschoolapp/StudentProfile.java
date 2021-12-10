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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class StudentProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Variable for the menu to show inside the login activity- Michelle
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView textView;

    Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        //Change Status Bar Color
        getWindow().setStatusBarColor(ContextCompat.getColor(StudentProfile.this,R.color.background_header_color));

        //Hooks

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        textView =findViewById(R.id.textView);
        toolbar = findViewById(R.id.toolbar);
        //ToolBar
        setSupportActionBar(toolbar);


        //Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new  ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


        //logout Button

        logoutBtn = findViewById(R.id.logoutBtn);
       logoutBtn.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View v) {
              startActivity(new Intent(getApplicationContext(), Login.class));
           }
       });

    }

    // Make Menu Return
    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch(menuItem.getItemId()){
            case R.id.nav_home:
                Intent home = new Intent(StudentProfile.this,MainActivity.class);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;

            case R.id.nav_logout:
                Intent logout = new Intent(StudentProfile.this,Login.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                break;


            case R.id.nav_user_profile:
                Intent studentProfile = new Intent(StudentProfile.this,StudentProfile.class);
                startActivity(new Intent(getApplicationContext(), StudentProfile.class));
                finish();
                break;

            case R.id.nav_student_info:
                Intent studentInfo = new Intent(StudentProfile.this,StudentInfo.class);
                startActivity(new Intent(getApplicationContext(), StudentInfo.class));
                finish();
                break;

       case R.id.nav_calendar:
            Intent studentInformation = new Intent (StudentProfile.this,Calendar.class);
               startActivity(new Intent(getApplicationContext(), Calendar.class));
             finish();
              break;

            case R.id.nav_resetUserPassword:
                Intent reset= new Intent (StudentProfile.this,ResetPassword.class);
                startActivity(new Intent(getApplicationContext(), ResetPassword.class));
                finish();
                break;



        }
        return true;
    }

}