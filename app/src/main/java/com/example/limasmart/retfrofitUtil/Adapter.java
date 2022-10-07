package com.example.limasmart.retfrofitUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.limasmart.R;
import com.example.limasmart.model.registeredDocs;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    private List<registeredDocs> regDocs;
    private Context context;

    public Adapter(List<registeredDocs> regDocs, Context context) {
        this.regDocs = regDocs;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        holder.name.setText(regDocs.get(position).getName());
        holder.address.setText(regDocs.get(position).getAddress());
        holder.qualification.setText(regDocs.get(position).getQualification());

    }

    @Override
    public int getItemCount() {
        return regDocs.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, address,qualification;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            qualification = itemView.findViewById(R.id.qualification);
        }
    }
}
