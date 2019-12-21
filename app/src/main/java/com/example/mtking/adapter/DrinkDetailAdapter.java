package com.example.mtking.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.mtking.R;
import com.example.mtking.activity.GioHangActivity;
import com.example.mtking.object.DetailDrinks;


import java.util.ArrayList;
import java.util.List;

public class DrinkDetailAdapter extends RecyclerView.Adapter<DrinkDetailAdapter.MyViewHolder> {
    private Context mContext;

    public List<Integer> getChitiet() {
        return chitiet;
    }

    private List<Integer> chitiet;

    private List<DetailDrinks> detailDrinksList;
    private static final String TAG ="product_detailsActivity";


    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        TextView txtten;
        TextView txtgia;
        CheckBox checkBox;
        public MyViewHolder(View view) {
            super(view);
            txtten = view.findViewById(R.id.tendt);
            txtgia = view.findViewById(R.id.giadt);
            checkBox = view.findViewById(R.id.checkloai);
            relativeLayout = view.findViewById(R.id.rl_phanloai);

        }
    }


    public DrinkDetailAdapter(Context mContext, List<DetailDrinks> detailDrinksList1) {
        this.mContext = mContext;
        this.detailDrinksList = detailDrinksList1;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemdetail, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final DetailDrinks detailDrinks = detailDrinksList.get(position);
        chitiet = new ArrayList<>();
        holder.txtten.setText(detailDrinks.getTenCT());
        holder.txtgia.setText(String.valueOf(detailDrinks.getGiatien()));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GioHangActivity.class);
                intent.putExtra("choice",detailDrinks.getIdDrinks());
                mContext.startActivity(intent);

        }
        });
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.checkBox.isChecked()){
                    chitiet.add(detailDrinks.getGiatien());
                }
                else if(!holder.checkBox.isChecked())
                {
                    chitiet.remove(detailDrinks.getGiatien());
                }
            }
        });




    }

    @Override
    public int getItemCount() { return detailDrinksList.size();
    }
}
