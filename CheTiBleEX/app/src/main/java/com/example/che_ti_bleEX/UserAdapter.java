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

    private ArrayList<User> arrayList;
    private Context context;

    public UserAdapter(ArrayList<User> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list,parent,false);
        UserViewHolder holder = new UserViewHolder(view);
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

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView user_name;
        TextView user_sub;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            this.user_name = itemView.findViewById(R.id.user_name);
            this.user_sub = itemView.findViewById(R.id.user_sub);
        }
    }
}
