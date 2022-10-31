package com.example.limasmart.retfrofitUtil;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.limasmart.R;
import com.example.limasmart.model.registeredDocs;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    private List<registeredDocs> regDocs;
    private Context context;
    private RecyclerViewClickListener listener;
    private String locality = "";

    public Adapter(List<registeredDocs> regDocs, Context context, RecyclerViewClickListener listener) {
        this.regDocs = regDocs;
        this.context = context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        String name = regDocs.get(position).getName();
        holder.name.setText(name);
        holder.address.setText(regDocs.get(position).getAddress());
        holder.locality.setText(regDocs.get(position).getLocality());

        //holder.qualification.setText(loc(name));

    }

    public String loc(String name){
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String result;
                String[] field = new String[1];
                field[0] = "name";
                //Creating array for data
                String[] data = new String[1];
                data[0] = name;

                PutData putData = new PutData("http://10.8.122.87/project1/getLoc.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        result = String.valueOf(putData.getResult());
                        /*if (result.equals("N/A")){
                            locality = result;
                        }else{
                           locality=result;
                        }*/
                        locality = result;


                    }

                }}
        });
        return locality;

    }

    @Override
    public int getItemCount() {
        return regDocs.size();
    }

    //removes static
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name, address,locality;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
           locality = itemView.findViewById(R.id.qualification);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //listener.onClick(itemView, getAbsoluteAdapterPosition());
            listener.onClick(view,getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }
}
