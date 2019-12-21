package com.example.mtking.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtking.R;
import com.example.mtking.adapter.TintucAdapter;
import com.example.mtking.object.Discount_code;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TinTucFragment extends Fragment {
    DatabaseReference databaseReference;
    List<Discount_code> magiamgias;
    TintucAdapter magiamgiaAdapter;
    View rootview;
    ViewFlipper viewFlipper;
    RecyclerView Review;

    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_tintuc,container,false);
        loadTintuc();
        hienthihinhdong();
        return rootview;
    }
    private void hienthihinhdong(){
        int images[] = {R.drawable.quangcao,R.drawable.ic_langman,R.drawable.ic_quangcao,R.drawable.ic_phucvu};
        viewFlipper = rootview.findViewById(R.id.viewfl_tintuc);
        for (int img: images) {
            Slider(img);
        }
    }
    public void Slider(int image){
        ImageView imageView= new ImageView(getActivity());

        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);

        viewFlipper.setFlipInterval(3000);

        viewFlipper.setAutoStart(true);

        //set animation to slider
        viewFlipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getActivity(),android.R.anim.slide_out_right);
    }
    private void loadTintuc(){
        magiamgias = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Discount_code");
        Review = rootview.findViewById(R.id.rv_magiamgia);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                magiamgias.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Discount_code magiamgia = snapshot.getValue(Discount_code.class);
                    magiamgia.setId(snapshot.getKey());
                    magiamgias.add(magiamgia);

                }
                magiamgiaAdapter = new TintucAdapter(getContext(),magiamgias);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
                Review.setLayoutManager(layoutManager);
                Review.setItemAnimator(new DefaultItemAnimator());
                Review.setAdapter(magiamgiaAdapter);
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
    }

}
