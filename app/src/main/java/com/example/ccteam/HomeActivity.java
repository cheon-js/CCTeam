package com.example.ccteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class HomeActivity extends AppCompatActivity {
    Button boardList;
    Button chat;
    Button carpool;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        boardList = findViewById(R.id.btnFreeboard);
        carpool = findViewById(R.id.btntaxi);
        chat = findViewById(R.id.btnQnA);

        boardList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),boardList.class);
                startActivity(intent);
            }
        });

        carpool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CarpoolboardActivity.class);
                startActivity(intent);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChatProfile.class);
                startActivity(intent);
            }
        });
    }
}
