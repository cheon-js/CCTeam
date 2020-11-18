package com.example.ccteam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FreeboardWriteActivity extends AppCompatActivity {
    Button input;
    EditText title, content;
    String input_title, input_content;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freeboard_write);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        input = findViewById(R.id.input_button);
        title = findViewById(R.id.board_title);
        content = findViewById(R.id.board_content);

        title.getText().toString();
        content.getText().toString();

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child("test").push().setValue(title.getText().toString());
                myRef.child("test").push().setValue(content.getText().toString());
            }
        });
        }
    }
