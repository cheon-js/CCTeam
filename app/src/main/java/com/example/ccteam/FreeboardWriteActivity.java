package com.example.ccteam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.ACTION_PICK;

public class FreeboardWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private final int GET_GALLERY_IMAGE = 200;

    EditText title, content;
    String input_title, input_content;
    ImageView board_iv;

    Animation fab_open, fab_close;
    FloatingActionButton menu, input, input_iv;
    Boolean openFlag = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freeboard_write);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        menu = findViewById(R.id.menu);
        input = findViewById(R.id.input);
        input_iv = findViewById(R.id.input_iv);
        board_iv = findViewById(R.id.board_iv);

        input.startAnimation(fab_close);
        input_iv.startAnimation(fab_close);
        input.setClickable(false);

        menu.setOnClickListener(this);
        input.setOnClickListener(this);
        input_iv.setOnClickListener(this);

        title = findViewById(R.id.board_title);
        content = findViewById(R.id.board_content);

        title.getText().toString();
        content.getText().toString();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.menu:
                anim();
                break;
            case R.id.input:
                anim();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("boardnum");
                DatabaseReference listboard = database.getReference("boardlist");
                DatabaseReference user = database.getReference("User");

                String boradId = "Board_" + System.currentTimeMillis();
                GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);

                listboard.child(boradId).child("name").setValue(signInAccount.getDisplayName());
                listboard.child(boradId).child("Title").setValue(title.getText().toString());
                listboard.child(boradId).child("Content").setValue(content.getText().toString());
                        //키값 없는 게시글 데이터베이스 입력 push();
                 Intent intent = new Intent(getApplicationContext(),boardList.class);
                 startActivity(intent);
                Toast.makeText(this, "게시글을 등록합니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.input_iv:
                anim();
                Intent intent2 = new Intent(Intent.ACTION_PICK);
                intent2. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent2, GET_GALLERY_IMAGE);
                Toast.makeText(this, "사진 첨부화면입니다.", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    public void anim() {

        if (openFlag) {
            input.startAnimation(fab_close);
            input_iv.startAnimation(fab_close);
            input.setClickable(false);
            input_iv.setClickable(false);
            openFlag = false;
        } else {
            input.startAnimation(fab_open);
            input_iv.startAnimation(fab_open);
            input.setClickable(true);
            input_iv.setClickable(true);
            openFlag = true;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            board_iv.setImageURI(selectedImageUri);

        }

    }
}
/*class test
                {
                    String a;
                    String b;
                }
                test te = new test();
                te.a = "sdfsdf";
                te.b = "ASfsdafl";*/