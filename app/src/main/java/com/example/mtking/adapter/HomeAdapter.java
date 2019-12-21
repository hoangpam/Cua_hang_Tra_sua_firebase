package com.example.mtking.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mtking.R;
import com.example.mtking.activity.DrinkActivity;
import com.example.mtking.activity.MainActivity;
import com.example.mtking.object.Category;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private Context mContext;

    private List<Category> categoryList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView anh;
        RelativeLayout relativeLayout;
        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tvTen);
            anh = view.findViewById(R.id.imgThucUong);
            relativeLayout = view.findViewById(R.id.relative);
        }
    }


    public HomeAdapter(Context mContext, List<Category> dichVuList) {
        this.mContext = mContext;
        this.categoryList=dichVuList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemhome, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Category category = categoryList.get(position);
        holder.name.setText(category.getmName());
        Glide.with(mContext).load(category.getmImage()).into(holder.anh);
        String id = category.getId();
        Log.e("id",id);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DrinkActivity.class);
                intent.putExtra("key",category.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
