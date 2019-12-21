package com.example.mtking.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mtking.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CuaHangFragment extends androidx.fragment.app.Fragment implements OnMapReadyCallback{
    GoogleMap map;
    SupportMapFragment googleMap;
    View rootView;
    public CuaHangFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.fragment_cuahang,container,false);

        return rootView;
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        googleMap = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.mapp);
        googleMap.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng pp =new LatLng(10.800025, 106.615842);
        MarkerOptions options = new MarkerOptions();
        options.position(pp).title(" Ghé để thưởng thức uống tuyệt vời nhé✿◕ ‿ ◕✿\uD83D\uDC96");
        map.addMarker(options);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pp,13));
    }
}
