package com.example.che_ti_bleEX;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private ArrayList<Teacher> arrayList;
    private Context context;
    private  OnUserListerner mOnUserListener;

    public UserAdapter(ArrayList<Teacher> arrayList, Context context, OnUserListerner onUserListerner){
        this.arrayList = arrayList;
        this.context = context;
        this.mOnUserListener = onUserListerner;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list,parent,false);
        UserViewHolder holder = new UserViewHolder(view, mOnUserListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.user_name.setText(arrayList.get(position).getName());
        holder.user_sub.setText(arrayList.get(position).getSubject());

    }

    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView user_name;
        TextView user_sub;
        OnUserListerner onUserListerner;

        public UserViewHolder(@NonNull View itemView, OnUserListerner onUserListerner) {
            super(itemView);
            this.user_name = itemView.findViewById(R.id.user_name);
            this.user_sub = itemView.findViewById(R.id.user_sub);
            this.onUserListerner = onUserListerner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onUserListerner.onUserClick(getAdapterPosition());
        }
    }

    public  interface  OnUserListerner{
        void onUserClick(int position);

    }
}
