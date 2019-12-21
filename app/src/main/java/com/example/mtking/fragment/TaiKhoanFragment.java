package com.example.mtking.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mtking.R;
import com.example.mtking.activity.DangNhapActivity;
import com.example.mtking.activity.NX_PhanHoiActivity;
import com.example.mtking.activity.SettingActivity;
import com.google.firebase.auth.FirebaseAuth;

public class TaiKhoanFragment extends Fragment implements View.OnClickListener {
    RelativeLayout rlDangXuat;

    ImageButton btnsetting;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_taikhoan,container,false);
        rlDangXuat = rootview.findViewById(R.id.it_logout);
        rlDangXuat.setOnClickListener(this);
        rlDangXuat =rootview.findViewById(R.id.it_nxphanhoi);
        rlDangXuat.setOnClickListener(this);
        btnsetting =rootview.findViewById(R.id.imb_setting);
        btnsetting.setOnClickListener(this);
        return rootview;

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.it_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), DangNhapActivity.class));
                break;
            case R.id.it_nxphanhoi:
                startActivity(new Intent(getActivity(), NX_PhanHoiActivity.class));
                break;
            case R.id.imb_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.imv_avatar:
                break;
        }
    }
}