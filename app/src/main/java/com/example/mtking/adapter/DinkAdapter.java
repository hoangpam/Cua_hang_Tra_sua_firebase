package com.example.mtking.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mtking.R;
import com.example.mtking.activity.DrinkActivity;
import com.example.mtking.activity.product_detailsActivity;
import com.example.mtking.object.Category;
import com.example.mtking.object.Drinks;

import java.util.List;

public class DinkAdapter extends RecyclerView.Adapter<DinkAdapter.MyViewHolder> {
    private Context mContext;

    private List<Drinks> drinksList;
    private static final String TAG ="DrinkActivity";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView ten;
        public TextView gia;
        public ImageView anh;
        public ImageButton imageButton;
        RelativeLayout relativeLayout;
        public MyViewHolder(View view) {
            super(view);
            ten = (TextView) view.findViewById(R.id.tvTen);
            gia = (TextView) view.findViewById(R.id.txtgia1);
            anh = view.findViewById(R.id.imgThucUong1);
            imageButton = view.findViewById(R.id.btn_them1);
            relativeLayout = view.findViewById(R.id.relative_thucuong1);
        }
    }


    public DinkAdapter(Context mContext, List<Drinks> dichVuList) {
        this.mContext = mContext;
        this.drinksList=dichVuList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemgoimon, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Drinks drinks = drinksList.get(position);
        holder.ten.setText(drinks.getNameDrink());
        holder.gia.setText(String.valueOf(drinks.getPrice()));
        Glide.with(mContext).load(drinks.getHinh()).into(holder.anh);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, product_detailsActivity.class);
                intent.putExtra("key",drinks.getIdDrink());
                mContext.startActivity(intent);
            }
        });
       /* holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, product_detailsActivity.class));
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return drinksList.size();
    }
}
