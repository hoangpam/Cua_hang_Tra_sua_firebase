package com.example.mtking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mtking.R;
import com.example.mtking.object.Discount_code;

import java.util.List;

public class TintucAdapter extends RecyclerView.Adapter<TintucAdapter.MyViewHolder> {
    private Context context;
    private List<Discount_code> magiamgiaList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView anh;
        RelativeLayout relativeLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            anh = itemView.findViewById(R.id.imvmagiamgia);
            relativeLayout = itemView.findViewById(R.id.relative_magiamgia);
        }
    }

    public TintucAdapter(Context ncontext,List<Discount_code> maggiaList){
        this.context = ncontext;
        this.magiamgiaList = maggiaList;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.magiamgia,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        final Discount_code magg = magiamgiaList.get(position);
        Glide.with(context).load(magg.getImaged()).into(holder.anh);
        String id = magg.getId();

    }

    @Override
    public int getItemCount() {
        return magiamgiaList.size();
    }


}
