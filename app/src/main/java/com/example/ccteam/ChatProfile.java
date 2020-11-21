package com.example.ccteam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

public class ChatProfile extends AppCompatActivity {
    TextView etName;
    ImageView ivProfile;
    Uri personPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_profile);

        etName = findViewById(R.id.et_name);
        ivProfile = findViewById(R.id.iv_profile);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null) {
            personPhoto = signInAccount.getPhotoUrl();
            etName.setText(signInAccount.getDisplayName());
            Picasso.get().load(personPhoto).into(ivProfile);
        }

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,10);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10:
                if(resultCode==RESULT_OK){
                    personPhoto = data.getData();
                    //Glide.with(this).load(imgUri).into(ivProfile);
                    //Glide는 이미지를 읽어와서 보여줄때 내 device의 외장메모리에 접근하는 퍼미션이 요구됨.
                    //(퍼미션이 없으면 이미지가 보이지 않음.)
                    //Glide를 사용할 때는 동적 퍼미션 필요함.

                    //Picasso 라이브러리는 퍼미션 없어도 됨.
                    Picasso.get().load(personPhoto).into(ivProfile);
                }
                break;
        }
    }

    public void clickBtn(View view) {
        //1. save작업
            saveData();
        //2. ChatActivity로 전환
    }
    void saveData() {
    }
}
