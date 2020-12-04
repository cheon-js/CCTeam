package com.example.ccteam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FreeboardWriteActivity extends AppCompatActivity {
    Button input,inputimage;
    EditText title, content;
    ImageView ivboard;
    String input_title, input_content;
    Uri Photo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freeboard_write);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("boardnum");
        DatabaseReference listboard = database.getReference("boardlist");
        DatabaseReference user = database.getReference("User");

        input = findViewById(R.id.input_button);
        ivboard = findViewById(R.id.board_iv);
        inputimage = findViewById(R.id.input_image);
        title = findViewById(R.id.board_title);
        content = findViewById(R.id.board_content);

        title.getText().toString();
        content.getText().toString();

        String boradId = "Board_" + System.currentTimeMillis();
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listboard.child(boradId).child("name").setValue(signInAccount.getDisplayName());
                listboard.child(boradId).child("Title").setValue(title.getText().toString());
                listboard.child(boradId).child("Content").setValue(content.getText().toString());
                //키값 없는 게시글 데이터베이스 입력 push();

                Intent intent = new Intent(getApplicationContext(),boardList.class);
                startActivity(intent);
            }
        });

        inputimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,20);
            }
        });
        }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

            case 20:
                if(resultCode==RESULT_OK){
                    Photo = data.getData();
                    Picasso.get().load(Photo).into(ivboard);
                }
                break;
        }
    }
    }