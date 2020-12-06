package com.example.ccteam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{
    private ArrayList<CommentItem> arrayList;
    private Context context;


    public CommentAdapter(ArrayList<CommentItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,parent,false);
        CommentViewHolder holder = new CommentViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {


        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfiles())
                .into(holder.iv_profiles);
        holder.tv_contents.setText(arrayList.get(position).getC_content());
        holder.tv_usernames.setText(arrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_profiles;
        TextView tv_contents;
        TextView tv_usernames;


        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profiles = itemView.findViewById(R.id.iv_profiles);
            this.tv_contents = itemView.findViewById(R.id.tv_contents);
            this.tv_usernames = itemView.findViewById(R.id.tv_usernames);
        }
    }
}
