package com.example.ccteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    ImageButton boardList;
    ImageButton chat;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        boardList = findViewById(R.id.imageButton2);
        chat = findViewById(R.id.imageButton6);

        boardList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),boardList.class);
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
