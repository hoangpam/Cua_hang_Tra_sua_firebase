package com.example.mtking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtking.R;
import com.example.mtking.adapter.DrinkDetailAdapter;
import com.example.mtking.adapter.GioHangAdapter;
import com.example.mtking.object.ChiTietGioHang;
import com.example.mtking.object.DetailDrinks;
import com.example.mtking.object.GioHang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class GioHangActivity extends AppCompatActivity {

    GioHangAdapter gioHangAdapter;
    List<ChiTietGioHang> chiTietGioHangs;
    RecyclerView recyclerView;
    Button btnThanhToan;
    TextView tvTongtien;

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("GioHang");
    DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("ChiTietGioHang");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        load();
        LoadChiTiet();
    }
    void load(){
        tvTongtien = findViewById(R.id.tvTongtien);
        recyclerView = findViewById(R.id.rv_dsgiohang);
        btnThanhToan = findViewById(R.id.btn_thanhtoan);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        chiTietGioHangs.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            GioHang gioHang = snapshot.getValue(GioHang.class);
                            if(gioHang.getTinhtrang().equals("Chưa đặt hàng"))
                            {
                               reference.child(snapshot.getKey()).child("tinhtrang").setValue("Đã đặt hàng");
                                Toast.makeText(GioHangActivity.this,"Đã đặt hàng thành công",Toast.LENGTH_LONG).show();

                            }
                            else {
                                Toast.makeText(GioHangActivity.this,"Chưa có thức uống trong giở hàng",Toast.LENGTH_LONG).show();
                            }

                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });



    }
    void LoadChiTiet(){
        chiTietGioHangs = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    GioHang gioHang = snapshot.getValue(GioHang.class);
                    if(gioHang.getTinhtrang().equals("Chưa đặt hàng"))
                    {
                        final String idGioHang = snapshot.getKey();
                        Log.e("idGioHang",idGioHang);
                        reference2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                chiTietGioHangs.clear();
                                int tongtien = 0;
                                for(DataSnapshot snapshot1:dataSnapshot.getChildren()){
                                    ChiTietGioHang chiTietGioHang = snapshot1.getValue(ChiTietGioHang.class);
                                    Log.e("idchitiet",chiTietGioHang.getIdGioHang());

                                    if(idGioHang.equals(chiTietGioHang.getIdGioHang()))
                                    {
                                        chiTietGioHangs.add(chiTietGioHang);
                                        chiTietGioHang.setIdChiTietGH(snapshot1.getKey());
                                        tongtien = tongtien + chiTietGioHang.getGiaTien();

                                    }

                                }
                                tvTongtien.setText("Tổng tiền: " + String.valueOf(tongtien));

                                gioHangAdapter = new GioHangAdapter(getApplicationContext(),chiTietGioHangs);
                                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                                recyclerView.setLayoutManager(mLayoutManager);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(gioHangAdapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
