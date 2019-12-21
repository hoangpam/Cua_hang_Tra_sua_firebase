package com.example.mtking.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mtking.R;
import com.example.mtking.adapter.DinkAdapter;
import com.example.mtking.adapter.HomeAdapter;
import com.example.mtking.object.Category;
import com.example.mtking.object.Drinks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DrinkActivity extends AppCompatActivity   {
    DatabaseReference reference;
    List<Drinks> drinksList;
    RecyclerView recyclerView;
    DinkAdapter dinkAdapter;
    String idCate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_list);
        loadMenu();

    }
    private void loadMenu() {
        idCate = getIntent().getStringExtra("key");
        drinksList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Drinks");
        Log.e("idcate",idCate);
        recyclerView = findViewById(R.id.rv_drink);

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    drinksList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Drinks drinks = snapshot.getValue(Drinks.class);
                        String idtam = drinks.getIdCate();
                        drinks.setIdDrink(snapshot.getKey());
                        Log.e("idtam", idtam);
                        if (idtam.equals(idCate)) {
                            drinksList.add(drinks);
                        }
                    }
                    dinkAdapter = new DinkAdapter(getApplicationContext(), drinksList);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(dinkAdapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }

}
