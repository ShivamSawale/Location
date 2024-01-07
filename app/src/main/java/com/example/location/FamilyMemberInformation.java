package com.example.location;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FamilyMemberInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_member_information);

        Button btn = findViewById(R.id.done);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FamilyMemberInformation.this, "We are working on the OTP Section", Toast.LENGTH_SHORT).show();

                Intent myInt = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myInt);
            }
        });
    }

}