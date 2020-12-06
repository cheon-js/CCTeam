package com.example.ccteam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

public class Comment extends AppCompatActivity {

    TextView title;
    TextView content;
    TextView name;
    ImageView profie;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<CommentItem> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    ImageView iv_google;
    TextView tv_name, tv_contents;
    EditText et2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        String title_data = getIntent().getStringExtra("Title_data");
        String content_data = getIntent().getStringExtra("Content_data");
        String Uname = getIntent().getStringExtra("name");
        String URl = getIntent().getStringExtra("imageURl");
        String key = getIntent().getStringExtra("Key_data");

        title = (TextView)findViewById(R.id.txttitle);
        content = (TextView)findViewById(R.id.txcontent);
        name = (TextView)findViewById(R.id.profilename);
        profie = (ImageView)findViewById(R.id.imgfile);

        title.setText(title_data);
        content.setText(content_data);
        name.setText(Uname);
        Picasso.get().load(URl).into(profie);


        iv_google = findViewById(R.id.myprofile);
        tv_contents = findViewById(R.id.tv_contents);
        tv_name = findViewById(R.id.tv_usernames);
        et2 =findViewById(R.id.et2);



        recyclerView = findViewById(R.id.listcomment); // 아디 연결
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();


        database = FirebaseDatabase.getInstance();


        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        databaseReference = database.getReference(key);


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                CommentItem commentItem = snapshot.getValue(CommentItem.class);

                arrayList.add(commentItem);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




                /*addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CommentItem commentItem = dataSnapshot.getValue(CommentItem.class);
                    arrayList.add(commentItem);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MainActivity", String.valueOf(error.toException()));
            }
        });*/
        adapter = new CommentAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);

    }
    public void clickSend(View view) {
        String key = getIntent().getStringExtra("Key_data");

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        String imageUrl = signInAccount.getPhotoUrl().toString();
        String contents = et2.getText().toString();
        DatabaseReference commentdata = database.getReference("postfreelist").child(key);
        DatabaseReference commentdata2 = database.getReference(key);
        CommentItem commentItems = new CommentItem(contents, signInAccount.getDisplayName(), imageUrl);
        String commentId = "Comment_" + System.currentTimeMillis();
        commentdata.child(commentId).setValue(commentItems);
        commentdata2.child(commentId).setValue(commentItems);


    }

}