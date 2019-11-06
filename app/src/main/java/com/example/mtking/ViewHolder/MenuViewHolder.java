package com.example.mtking.ViewHolder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.mtking.Interface.ItemClickListener;
import com.example.mtking.R;
import androidx.recyclerview.widget.RecyclerView;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView igv;
    private ItemClickListener itemClickListener;

    public MenuViewHolder(@NonNull View itemView) {

        super(itemView);

        igv = (ImageView)itemView.findViewById(R.id.imageView);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
