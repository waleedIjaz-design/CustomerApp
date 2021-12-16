package com.example.customerside.Auto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customerside.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainAdapterBike extends RecyclerView.Adapter<MainAdapterBike.ViewHolder> {
    ArrayList<MainModelBike> mainModelsBike;
    Context context;

    public MainAdapterBike(Context context,ArrayList<MainModelBike> mainModelsBike){
        this.context = context;
        this.mainModelsBike = mainModelsBike;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_bike_parts_list,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(mainModelsBike.get(position).getBikePartsList());
        holder.textView1.setText(mainModelsBike.get(position).getBikePartsName());
        holder.textView2.setText(mainModelsBike.get(position).getBikePartsPrice());

    }

    @Override
    public int getItemCount() {
        return mainModelsBike.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Initialise Variable
        ImageView imageView;
        TextView textView1;
        TextView textView2;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.bike_img_view);
            textView1 = itemView.findViewById(R.id.bike_text_view);
            textView2 = itemView.findViewById(R.id.bike_txt_rupees);
        }
    }
}

