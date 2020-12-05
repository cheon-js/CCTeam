package com.example.ccteam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Comment extends AppCompatActivity {

    TextView title;
    TextView content;
    TextView name;
    ImageView profie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        String title_data = getIntent().getStringExtra("Title_data");
        String content_data = getIntent().getStringExtra("Content_data");
        String Uname = getIntent().getStringExtra("name");
        String URl = getIntent().getStringExtra("imageURl");
        title = (TextView)findViewById(R.id.txttitle);
        content = (TextView)findViewById(R.id.txcontent);
        name = (TextView)findViewById(R.id.profilename);
        profie = (ImageView)findViewById(R.id.imgfile);

        title.setText(title_data);
        content.setText(content_data);
        name.setText(Uname);
        Picasso.get().load(URl).into(profie);

    }
}