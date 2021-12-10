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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ebuschoolapp.MainActivity;
import com.example.ebuschoolapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResetPassword extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    EditText userPassword, userConfPassword;
    Button savePasswordBtn;
    FirebaseUser user;


    // Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        //Change Status Bar Color
        getWindow().setStatusBarColor(ContextCompat.getColor(ResetPassword.this,R.color.background_header_color));


        //Hooks

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        textView =findViewById(R.id.textView);
        toolbar = findViewById(R.id.toolbar);


        // Extract data after user data
        userPassword = findViewById(R.id.newUserPassword);
        userConfPassword = findViewById(R.id.newConfirmPass);

        // check firebase user
        user = FirebaseAuth.getInstance().getCurrentUser();

        // extract data after save button is clicked
        savePasswordBtn = findViewById(R.id.resetPasswordBtn);
        savePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // error is fields are not filled
                if(userPassword.getText().toString().isEmpty()) {
                    userPassword.setError("Field is required");
                    return;
                }
                if(userConfPassword.getText().toString().isEmpty()) {
                    userConfPassword.setError("Field is required");
                    return;
                }

                // compare fields
                if(!userPassword.getText().toString().equals(userConfPassword.getText().toString())) {
                    userConfPassword.setError("Passwords do not match.");
                    return;
                }

                // if successful
                user.updatePassword(userPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ResetPassword.this, "Password Updated.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ResetPassword.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
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
                Intent home = new Intent(ResetPassword.this, MainActivity.class);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;

            case R.id.nav_logout:
                Intent logout = new Intent(ResetPassword.this, Login.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                break;


            case R.id.nav_user_profile:
                Intent studentProfile = new Intent(ResetPassword.this,StudentProfile.class);
                startActivity(new Intent(getApplicationContext(), StudentProfile.class));
                finish();
                break;

            case R.id.nav_student_info:
                Intent studentInfo = new Intent(ResetPassword.this,StudentInfo.class);
                startActivity(new Intent(getApplicationContext(), StudentInfo.class));
                finish();
                break;

            case R.id.nav_calendar:
                Intent calendar = new Intent(ResetPassword.this, Calendar.class);
                startActivity(new Intent(getApplicationContext(), Calendar.class));
                finish();
                break;

            case R.id.nav_resetUserPassword:
                Intent reset = new Intent(ResetPassword.this, ResetPassword.class);
                startActivity(new Intent(getApplicationContext(), ResetPassword.class));
                finish();
                break;


        }

        return true;
    }


}