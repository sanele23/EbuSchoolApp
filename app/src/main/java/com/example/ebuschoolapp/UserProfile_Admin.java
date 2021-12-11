package com.example.ebuschoolapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;

public class UserProfile_Admin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView fullName, email, phone;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId; // to identify user
    Button logoutBtn;

    // Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_admin);

        //Change Status Bar Color
        getWindow().setStatusBarColor(ContextCompat.getColor(UserProfile_Admin.this, R.color.background_header_color));


        //Hooks

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        textView = findViewById(R.id.textView);
        toolbar = findViewById(R.id.toolbar);
        logoutBtn = findViewById(R.id.logoutBtn);


        // User Profile Click Activity from Home page to Student Info Activity

        fullName = findViewById(R.id.profileName);
        email = findViewById(R.id.profileEmail);
        phone = findViewById(R.id.profilePhone);

        // initialize data
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        // Retrieve data from Firebase
        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("Users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                fullName.setText(documentSnapshot.getString("FullName"));
                email.setText(documentSnapshot.getString("UserEmail"));
                phone.setText(documentSnapshot.getString("PhoneNumber"));
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
        navigationView.setCheckedItem(R.id.nav_user_profile);
        navigationView.setCheckedItem(R.id.nav_calendar);
        navigationView.setCheckedItem(R.id.nav_resetUserPassword);
        navigationView.setCheckedItem(R.id.nav_student_info);


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });

    }

    // Make Menu Return
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent backToMain = new Intent(UserProfile_Admin.this, Admin.class);
            startActivity(backToMain);
            finish();
        }


    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {



        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent home = new Intent(UserProfile_Admin.this,Admin.class);
                startActivity(new Intent(getApplicationContext(), Admin.class));
                finish();
                break;


            case R.id.nav_logout:
                Intent logout = new Intent(UserProfile_Admin.this, Login.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                break;

            case R.id.nav_student_info:
                Intent studentInfo = new Intent(UserProfile_Admin.this, GradingActivity.class);
                startActivity(new Intent(getApplicationContext(), GradingActivity.class));
                finish();
                break;

            case R.id.nav_user_profile:
                Intent studentProfile = new Intent(UserProfile_Admin.this,  UserProfile_Admin.class);
                startActivity(new Intent(getApplicationContext(),  UserProfile_Admin.class));
                finish();
                break;

            case R.id.nav_calendar:
                Intent calendar = new Intent(UserProfile_Admin.this, Calendar_Admin.class);
                startActivity(new Intent(getApplicationContext(), Calendar_Admin.class));
                finish();
                break;

            case R.id.nav_resetUserPassword:
                Intent reset = new Intent(UserProfile_Admin.this, ResetPassword_Admin.class);
                startActivity(new Intent(getApplicationContext(), ResetPassword_Admin.class));
                finish();
                break;


        }

        return true;
    }



}
