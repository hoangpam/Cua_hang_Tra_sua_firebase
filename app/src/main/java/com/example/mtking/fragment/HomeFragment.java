package com.example.mtking.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtking.R;
import com.example.mtking.adapter.HomeAdapter;
import com.example.mtking.object.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {
    DatabaseReference reference;
    List<Category> categoryList;
    RecyclerView recyclerView;
    HomeAdapter homeAdapter;
    View rootview;
    public HomeFragment(){

    }

    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        rootview = inflater.inflate(R.layout.content_home,container,false);
//        rootview = inflater.inflate(R.layout.fragment_giohang,container,false);

        loadMenu();

        return rootview;
    }
    private void loadMenu() {
        categoryList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Category");
        recyclerView = rootview.findViewById(R.id.rcv_menu);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                categoryList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Category category = snapshot.getValue(Category.class);
                    category.setId(snapshot.getKey());
                    categoryList.add(category);

                }
                homeAdapter = new HomeAdapter(getContext(),categoryList);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(homeAdapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
