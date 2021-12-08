package com.example.ebuschoolapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ebuschoolapp.MainActivity;
import com.example.ebuschoolapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResetPassword extends AppCompatActivity {
    EditText userPassword, userConfPassword;
    Button savePasswordBtn;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

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
    }
}