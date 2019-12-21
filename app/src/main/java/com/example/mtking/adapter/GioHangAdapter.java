package com.example.mtking.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mtking.R;
import com.example.mtking.activity.product_detailsActivity;
import com.example.mtking.object.ChiTietGioHang;
import com.example.mtking.object.Drinks;
import com.example.mtking.object.GioHang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.MyViewHolder> {
    private Context mContext;

    private List<ChiTietGioHang> chiTietGioHangs;
    private static final String TAG ="DrinkActivity";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView ten;
        public TextView size;
        public TextView gia;
        public TextView sl;
        public ImageView imageView;
        public Button btGiam;
        public Button btTang;
        public Button btXoa;
        public MyViewHolder(View view) {
            super(view);
            ten = (TextView) view.findViewById(R.id.tvTen);
            size = (TextView) view.findViewById(R.id.tvSize);
            gia = view.findViewById(R.id.tvGia);
            sl = view.findViewById(R.id.tvSoluong);
            imageView = view.findViewById(R.id.imgThucUong2);
            btXoa = view.findViewById(R.id.btnXoa);
            btGiam = view.findViewById(R.id.btn_giamsoluong);
            btTang = view.findViewById(R.id.btn_tangsoluong);
        }
    }


    public GioHangAdapter(Context mContext, List<ChiTietGioHang> chiTietGioHangs) {
        this.mContext = mContext;
        this.chiTietGioHangs=chiTietGioHangs;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemgiohang, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ChiTietGioHang chiTietGioHang = chiTietGioHangs.get(position);
        holder.size.setText("Size: "+ chiTietGioHang.getSize());
        holder.sl.setText(String.valueOf(chiTietGioHang.getSl()));
        holder.gia.setText("Giá tiền: "+ chiTietGioHang.getGiaTien());

        String idDrink = chiTietGioHang.getIdDrink();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Drinks").child(idDrink);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Drinks drinks = dataSnapshot.getValue(Drinks.class);
                holder.ten.setText(drinks.getNameDrink());
                Glide.with(mContext).load(drinks.getHinh()).into(holder.imageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        holder.btXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("test",chiTietGioHang.getIdChiTietGH());
                DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("ChiTietGioHang").child(chiTietGioHang.getIdChiTietGH());
                reference2.removeValue();
            }
        });

        holder.btTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference("ChiTietGioHang").child(chiTietGioHang.getIdChiTietGH());
                reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ChiTietGioHang chiTietGioHang1 = dataSnapshot.getValue(ChiTietGioHang.class);
                        int giatien = chiTietGioHang1.getGiaTien();
                        int sl = chiTietGioHang1.getSl();
                        int slht = Integer.parseInt(holder.sl.getText().toString()) + 1;
                        int giatiensau = (slht * giatien)/sl;
                        reference3.child("giaTien").setValue(giatiensau);
                        reference3.child("sl").setValue(slht);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        holder.btGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference reference4 = FirebaseDatabase.getInstance().getReference("ChiTietGioHang").child(chiTietGioHang.getIdChiTietGH());
                reference4.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ChiTietGioHang chiTietGioHang1 = dataSnapshot.getValue(ChiTietGioHang.class);
                        int giatien = chiTietGioHang1.getGiaTien();
                        int sl = chiTietGioHang1.getSl();
                        int slht = Integer.parseInt(holder.sl.getText().toString()) - 1;
                        if(slht == 0){
                            reference4.removeValue();
                        }
                        else {
                            int giatiensau = (slht * giatien)/sl;
                            reference4.child("giaTien").setValue(giatiensau);
                            reference4.child("sl").setValue(slht);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return chiTietGioHangs.size();
    }
}
