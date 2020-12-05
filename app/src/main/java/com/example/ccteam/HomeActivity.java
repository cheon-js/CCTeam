package com.example.ccteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class HomeActivity extends AppCompatActivity {
    Button notice;
    Button freeboard;
    Button qna;
    Button deal;
    Button best;
    Button carpool;
    Button mywrite;
    Button mycomment;
    //******************테스트 버튼. 추후 다른 기능에 추가*******************
    Button chat;
    Button article;
    //*********************************************************************

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        notice = findViewById(R.id.btnMainNotice);
        freeboard = findViewById(R.id.btnFreeboard);
        qna = findViewById(R.id.btnQnA);
        deal = findViewById(R.id.btnDeal);
       // best = findViewById(R.id.btnbest);
        carpool = findViewById(R.id.btntaxi);
        mywrite = findViewById(R.id.btnMywrite);
        mycomment = findViewById(R.id.btnMycomment);

        //******************테스트 버튼. 추후 다른 기능에 추가*******************
        chat = findViewById(R.id.btnQnA);
        article = findViewById(R.id.btnMywrite);
        //*********************************************************************

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        freeboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),boardList.class);
                startActivity(intent);
            }
        });

        qna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DealListActivity.class);
                startActivity(intent);
            }
        });

        /*best.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        carpool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CarpoolboardActivity.class);
                startActivity(intent);
            }
        });

        mywrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mycomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //********************************테스트 버튼. 추후 제거*************************************
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChatProfile.class);
                startActivity(intent);
            }
        });

        /*article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ContentsActivity.class);
                startActivity(intent);
            }
        });*/
        //******************************************************************************************

    }
}
