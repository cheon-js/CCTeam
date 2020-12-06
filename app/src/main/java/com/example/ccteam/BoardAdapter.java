package com.example.ccteam;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
    private ArrayList<Board> arrayList;
    private ArrayList<String> mkeys;
    private Context context;

    public BoardAdapter(ArrayList<Board> arrayList,ArrayList<String> mkeys, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        this.mkeys = mkeys;
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_item,parent,false);
        BoardViewHolder holder = new BoardViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.iv_profile);
        holder.tv_title.setText(arrayList.get(position).getTitle());
        holder.tv_content.setText(arrayList.get(position).getContent());
        holder.tv_username.setText(arrayList.get(position).getUsername());

        holder.itemView.setOnClickListener(new View.OnClickListener(
        ) {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database;
                DatabaseReference databaseReference;

                Intent intent = new Intent(v.getContext(), Comment.class);
                intent.putExtra("Key_data", mkeys.get(position));
                intent.putExtra("Title_data", arrayList.get(position).getTitle());
                intent.putExtra("Content_data",arrayList.get(position).getContent());
                intent.putExtra("name", arrayList.get(position).getUsername());
                intent.putExtra("imageURl", arrayList.get(position).getProfile());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_profile;
        TextView tv_title;
        TextView tv_content;
        TextView tv_username;

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_content = itemView.findViewById(R.id.tv_content);
            this.tv_username = itemView.findViewById(R.id.tv_username);
        }



    }
}
