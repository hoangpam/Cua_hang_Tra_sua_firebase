package com.example.mtking.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mtking.R;
import com.example.mtking.adapter.DinkAdapter;
import com.example.mtking.adapter.DrinkDetailAdapter;
import com.example.mtking.diolog.DialogThemTP;
import com.example.mtking.object.ChiTietGioHang;
import com.example.mtking.object.DetailDrinks;
import com.example.mtking.object.Drinks;
import com.example.mtking.object.GioHang;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

public class product_detailsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, DialogThemTP.dialogThemTP {
    DatabaseReference reference;
    DatabaseReference reference2;
    TextView tvName;
    TextView tvGiaTien;
    TextView tvTongTien;
    String idDrink;
    String idDt;
    ImageView imvHinh;
    Button btnTang;
    Button btnGiam;
    Button btThemTP;
    TextView tvSL;
    ImageButton btnGioHang;
    DrinkDetailAdapter drinkDetailAdapter;
    List<DetailDrinks> detailDrinksList;
    RecyclerView recyclerView;
    String keyDh;
    DialogThemTP dialogThemTP;
    int tongtien = 0;
    int tongchitiet = 0;
    RelativeLayout relativeLayout;
    Spinner spinner;
    int giatiensp;
    String uid;
    int dem = 0;
    String idgiohangg = null;
    DatabaseReference reference3;
    DatabaseReference reference4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        init();
        loadThongTin();

    }


    public void init()
    {

        tvName = findViewById(R.id.tv_tenthucuong);
        tvGiaTien = findViewById(R.id.tv_giathucuong);
        imvHinh = findViewById(R.id.imvhinh);
        tvSL = findViewById(R.id.idsl);
        tvTongTien = findViewById(R.id.idTongTien);

        btnTang = findViewById(R.id.btn_tangsoluong);
        btnTang.setOnClickListener(this);
        btnGiam = findViewById(R.id.btn_giamsoluong);
        btnGiam.setOnClickListener(this);
        btnGioHang = findViewById(R.id.btn_giohang);
        btnGioHang.setOnClickListener(this);

        idDrink = getIntent().getStringExtra("key");

        idDt = getIntent().getStringExtra("choice");
        detailDrinksList = new ArrayList<>();

        reference3 = FirebaseDatabase.getInstance().getReference("GioHang");
        reference4 = FirebaseDatabase.getInstance().getReference("ChiTietGioHang");
        reference = FirebaseDatabase.getInstance().getReference("Drinks").child(idDrink);
        Log.e("idDrink",idDrink);
        reference2 = FirebaseDatabase.getInstance().getReference("DetailDink");

      /*  recyclerView = findViewById(R.id.recycle);*/

        tvSL.setText("1");

        uid = FirebaseAuth.getInstance().getUid();
        spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.size, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



    }


    public void loadThongTin(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Drinks drinks = dataSnapshot.getValue(Drinks.class);
                tvGiaTien.setText(drinks.getIdDrink());
                tvName.setText(drinks.getNameDrink());
                Glide.with(getApplicationContext()).load(drinks.getHinh()).into(imvHinh);

                giatiensp = drinks.getPrice();
                String size = spinner.getSelectedItem().toString();
                int tongtientam;
                if(size.equals("Trung bình"))
                {
                    tongtientam = (int) (1.5*Integer.parseInt(tvSL.getText().toString()))*giatiensp;
                    tvTongTien.setText(String.valueOf(tongtientam));
                }
                else if(size.equals("Lớn"))
                {
                    tongtientam = (int) (2*Integer.parseInt(tvSL.getText().toString()))*giatiensp;
                    tvTongTien.setText(String.valueOf(tongtientam));
                }else {
                    tongtientam = (int) (Integer.parseInt(tvSL.getText().toString()))*giatiensp;
                    tvTongTien.setText(String.valueOf(tongtientam));
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       /* reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                detailDrinksList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DetailDrinks detailDrinks = snapshot.getValue(DetailDrinks.class);
                    String idDltam = detailDrinks.getIdDrinks();
                    detailDrinks.setIdDrinks(snapshot.getKey());
                     Log.e("idddltam", idDltam);
                     if(idDltam.equals(idDrink))
                         detailDrinksList.add(detailDrinks);


                }
                drinkDetailAdapter = new DrinkDetailAdapter(getApplicationContext(),detailDrinksList);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(drinkDetailAdapter);


            }*/

          /*  @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



        /*for(int i =0; i< drinkDetailAdapter.getChitiet().size();i++){
            tongchitiet = tongchitiet + drinkDetailAdapter.getChitiet().get(i);
        }*/
        /*Log.e("tongchitiet",drinkDetailAdapter.getChitiet().get(0));*/

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_tangsoluong:
                int sl = Integer.parseInt(tvSL.getText().toString());
                sl = sl + 1;
                Log.e("tang",String.valueOf(sl));
                tvSL.setText(String.valueOf(sl));

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Drinks").child(idDrink);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Drinks drinks =dataSnapshot.getValue(Drinks.class);
                        int giatien  = drinks.getPrice();
                        String size = spinner.getSelectedItem().toString();
                        int tongtientam;

                        if(size.equals("Trung bình"))
                        {
                            tongtientam = (int) (1.5*Integer.parseInt(tvSL.getText().toString()))*giatiensp;
                            tvTongTien.setText(String.valueOf(tongtientam));
                        }
                        else if(size.equals("Lớn"))
                        {
                            tongtientam = (int) (2*Integer.parseInt(tvSL.getText().toString()))*giatiensp;
                            tvTongTien.setText(String.valueOf(tongtientam));
                        }else {
                            tongtientam = (int) (Integer.parseInt(tvSL.getText().toString()))*giatiensp;
                            tvTongTien.setText(String.valueOf(tongtientam));
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case R.id.btn_giamsoluong:
                int sl1 = Integer.parseInt(tvSL.getText().toString());
                if(sl1 >0){
                    sl1 = sl1 - 1;
                    Log.e("giam",String.valueOf(sl1));
                    tvSL.setText(String.valueOf(sl1));
                }
                DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("Drinks").child(idDrink);
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Drinks drinks =dataSnapshot.getValue(Drinks.class);
                        int giatien  = drinks.getPrice();
                        String size = spinner.getSelectedItem().toString();
                        int tongtientam;

                        if(size.equals("Trung bình"))
                        {
                            tongtientam = (int) (1.5*Integer.parseInt(tvSL.getText().toString()))*giatiensp;
                            tvTongTien.setText(String.valueOf(tongtientam));
                        }
                        else if(size.equals("Lớn"))
                        {
                            tongtientam = (int) (2*Integer.parseInt(tvSL.getText().toString()))*giatiensp;
                            tvTongTien.setText(String.valueOf(tongtientam));
                        }else {
                            tongtientam = (int) (Integer.parseInt(tvSL.getText().toString()))*giatiensp;
                            tvTongTien.setText(String.valueOf(tongtientam));
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case R.id.btn_giohang:

                reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        /*String idgiohangg = null;*/
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            GioHang gioHang = snapshot.getValue(GioHang.class);
                            if(gioHang.getTinhtrang().equals("Chưa đặt hàng")){
                                dem = dem +1;
                                idgiohangg = snapshot.getKey();

                        }
                        if(dem>0){
                            reference4.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    int dem1 = 0;
                                    for(DataSnapshot snapshot2: dataSnapshot.getChildren())
                                    {
                                        ChiTietGioHang chiTietGioHang = snapshot2.getValue(ChiTietGioHang.class);
                                        Log.e("test1",chiTietGioHang.getIdDrink());
                                        Log.e("test2",idDrink);
                                        if(chiTietGioHang.getIdDrink().equals(idDrink)&&chiTietGioHang.getIdGioHang().equals(idgiohangg))
                                        {
                                            dem1 =dem +1;

                                        }
                                    }
                                    if(dem1 >0 )
                                    {
                                        Toast.makeText(product_detailsActivity.this, "Sản phẩm này đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        int slsp = Integer.parseInt(tvSL.getText().toString());
                                        String sizeSP = spinner.getSelectedItem().toString();
                                        int giatien3 = Integer.parseInt(tvTongTien.getText().toString());

                                        ChiTietGioHang chiTietGioHang = new ChiTietGioHang(idDrink,idgiohangg,slsp,giatien3,sizeSP);
                                        reference4.push().setValue(chiTietGioHang);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }


                        }
                        if(dem == 0){

                            int tongtiengiohang = Integer.parseInt(tvTongTien.getText().toString());
                            GioHang gioHang2 = new GioHang(uid,"Chưa đặt hàng",tongtiengiohang);
                            String idGioHang = reference3.push().getKey();
                            reference3.child(idGioHang).setValue(gioHang2);

                            int slsp = Integer.parseInt(tvSL.getText().toString());
                            String sizeSP = spinner.getSelectedItem().toString();
                            int giatien3 = Integer.parseInt(tvTongTien.getText().toString());
                            ChiTietGioHang chiTietGioHang = new ChiTietGioHang(idDrink,idGioHang,slsp,giatien3,sizeSP);
                            reference4.push().setValue(chiTietGioHang);

                        }
                        Log.e("Dem",String.valueOf(dem));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                startActivity(new Intent(this,GioHangActivity.class));

                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        if(text.equals("Trung bình"))
        {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Drinks").child(idDrink);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Drinks drinks =dataSnapshot.getValue(Drinks.class);
                    int giatien  = drinks.getPrice();
                    int sl = Integer.parseInt(tvSL.getText().toString());
                    int tongtien3 = (int) (giatien*1.5*sl);
                    tvTongTien.setText(String.valueOf(tongtien3));


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        if(text.equals("Nhỏ"))
        {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Drinks").child(idDrink);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Drinks drinks =dataSnapshot.getValue(Drinks.class);
                    int giatien  = drinks.getPrice();
                    int sl = Integer.parseInt(tvSL.getText().toString());
                    tvTongTien.setText(String.valueOf(giatien*sl));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if(text.equals("Lớn"))
        {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Drinks").child(idDrink);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Drinks drinks =dataSnapshot.getValue(Drinks.class);
                    int giatien  = drinks.getPrice();
                    int sl = Integer.parseInt(tvSL.getText().toString());
                    tvTongTien.setText(String.valueOf(giatien*2*sl));

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void themTP() {
        String tam = getIntent().getStringExtra("key");
        Log.e("key",tam);
    }
}
