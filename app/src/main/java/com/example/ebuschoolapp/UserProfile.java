package com.example.ebuschoolapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class UserProfile extends AppCompatActivity {

    TextView fullName, email, phone;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId; // to identify user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

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

    }
}