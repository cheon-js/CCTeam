package com.example.ccteam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FreeboardWriteActivity extends AppCompatActivity {
    Button input, input_iv;
    EditText title, content;
    String input_title, input_content;
    ImageView image_iv;
    Uri board_iv;
    boolean isChanged= false;
    Board board1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freeboard_write);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userboard = database.getReference("userfreeboard");
        DatabaseReference postfreeboard = database.getReference("postfreelist");

        input = findViewById(R.id.input_button);
        title = findViewById(R.id.board_title);
        content = findViewById(R.id.board_content);
        input_iv = findViewById(R.id.input_image);
        image_iv = findViewById(R.id.board_iv);

        title.getText().toString();
        content.getText().toString();


        String boradId = "Board_" + System.currentTimeMillis();
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);

        input_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,20);
                isChanged=true;
            }
        });

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isChanged==true){
                    board1 = new Board(title.getText().toString(), content.getText().toString(),signInAccount.getDisplayName(),G.boardUrl);
                    postfreeboard.child(boradId).setValue(board1);
                    userboard.child(boradId).setValue(board1);

                }
                else{
                    board1 = new Board(title.getText().toString(), content.getText().toString(),signInAccount.getDisplayName(),G.boardUrl);
                    postfreeboard.child(boradId).setValue(board1);
                    userboard.child(boradId).setValue(board1);
                }

                Intent intent = new Intent(getApplicationContext(),boardList.class);
                startActivity(intent);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 20:
                if(resultCode==RESULT_OK){
                    board_iv = data.getData();
                    Picasso.get().load(board_iv).into(image_iv);

                    FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();

                    SimpleDateFormat image= new SimpleDateFormat(""+System.currentTimeMillis());
                    String fileName= image.format(new Date())+".png";

                    final StorageReference img= firebaseStorage.getReference("FreeboardImage/"+fileName);

                    UploadTask uploadTask=img.putFile(board_iv);
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            img.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    G.boardUrl = uri.toString();
                                    //Toast.makeText(getApplicationContext(),"사진업로드 완료",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                break;
        }
    }
}