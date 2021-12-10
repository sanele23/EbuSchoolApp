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
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Event extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    EditText title;
    EditText location;
    EditText description;
    Button addEvent;


    // Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        //Change Status Bar Color
        getWindow().setStatusBarColor(ContextCompat.getColor(Event.this,R.color.background_header_color));


        //Hooks

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        textView =findViewById(R.id.textView);
        toolbar = findViewById(R.id.toolbar);


        title = findViewById(R.id.etTitle);
        location = findViewById(R.id.etLocation);
        description = findViewById(R.id.etDescription);
        addEvent = findViewById(R.id.btnAdd);

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!title.getText().toString().isEmpty() && !location.getText().toString().isEmpty()
                        && !description.getText().toString().isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, title.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, location.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, description.getText().toString());
                    //intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    //intent.putExtra(Intent.EXTRA_EMAIL, "atombir3@gmail.com");

                    if(intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(Event.this, "There is no app that can support this action",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Event.this, "Please fill all the fields",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        //ToolBar
        setSupportActionBar(toolbar);


        //Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new  ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
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
                Intent home = new Intent(Event.this, MainActivity.class);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;

            case R.id.nav_logout:
                Intent logout = new Intent(Event.this, Login.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                break;


            case R.id.nav_user_profile:
                Intent studentProfile = new Intent(Event.this,StudentProfile.class);
                startActivity(new Intent(getApplicationContext(), StudentProfile.class));
                finish();
                break;

            case R.id.nav_student_info:
                Intent studentInfo = new Intent(Event.this,StudentInfo.class);
                startActivity(new Intent(getApplicationContext(), StudentInfo.class));
                finish();
                break;

            case R.id.nav_calendar:
                Intent calendar = new Intent(Event.this, Calendar.class);
                startActivity(new Intent(getApplicationContext(), Calendar.class));
                finish();
                break;

            case R.id.nav_resetUserPassword:
                Intent reset = new Intent(Event.this, ResetPassword.class);
                startActivity(new Intent(getApplicationContext(), ResetPassword.class));
                finish();
                break;


        }

        return true;
    }


}