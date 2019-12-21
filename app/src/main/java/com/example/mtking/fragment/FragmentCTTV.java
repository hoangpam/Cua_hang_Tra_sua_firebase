package com.example.mtking.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mtking.R;

public class FragmentCTTV extends androidx.fragment.app.Fragment {
    View rootview;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_cttv,container,false);
        return rootview;
    }
}
