package com.example.location;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class NewUser extends AppCompatActivity {

    private EditText nameEditText, mobileEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private Button registerButton;

    private TextView haveanaccount;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        firebaseAuth = FirebaseAuth.getInstance();

        nameEditText = findViewById(R.id.username);
        mobileEditText = findViewById(R.id.usermobilenumber);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmpassword);
        registerButton = findViewById(R.id.done);
        haveanaccount = findViewById(R.id.haveanaccount);

        haveanaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myInt = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(myInt);
                finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }



    private void registerUser() {
        String name = nameEditText.getText().toString().trim();
        String mobile = mobileEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        // Perform validation checks
        if (name.isEmpty() || mobile.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Register the user with Firebase
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registration successful
                            Toast.makeText(NewUser.this, "Registration successful", Toast.LENGTH_SHORT).show();

                            // Add logic to navigate to the next activity
                            Intent myIntent = new Intent(NewUser.this, WhomToAdd.class);
                            startActivity(myIntent);
                            finish(); // Optional: finish the current activity if needed
                        } else {
                            // If registration fails, display a message to the user.
                            Toast.makeText(NewUser.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}




//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//public class NewUser extends AppCompatActivity {
//
//    private EditText username;
//    private EditText usermobilenumber;
//    private EditText email;
//    private EditText password;
//    private EditText confirmpassword;
//    private TextView haveanaccount;
//    private Button buttonLogin;
//    private FirebaseAuth firebaseAuth;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_new_user);
//
//        FirebaseApp.initializeApp(this);
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        TextView btn1 = findViewById(R.id.haveanaccount);
//        Button btn = findViewById(R.id.done);
//        EditText username = findViewById(R.id.username);
//        EditText usermobilenumber = findViewById(R.id.usermobilenumber);
//        EditText email = findViewById(R.id.email);
//        EditText password = findViewById(R.id.password);
//        EditText confirmpassword = findViewById(R.id.confirmpassword);
//
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent myInt = new Intent(getApplicationContext(), LoginActivity.class);
//                myInt.putExtra("userEmail", email.getText().toString());
//                startActivity(myInt);
//                finish();
//            }
//        });
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(NewUser.this, "Done", Toast.LENGTH_SHORT).show();
//
//                Intent myInt = new Intent(getApplicationContext(), WhomToAdd.class);
//                myInt.putExtra("userEmail", email.getText().toString());
//                Toast.makeText(NewUser.this, "Welcome", Toast.LENGTH_SHORT).show();
//                startActivity(myInt);
//                finish();
//            }
//        });
//    }
//}
//
//        }
//
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            Intent i = new Intent(NewUser.this, MainActivity.class);
//            startActivity(i);
//            finish();
//        }
//    }
//
//
//
//    }



