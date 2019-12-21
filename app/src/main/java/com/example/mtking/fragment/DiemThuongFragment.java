package com.example.mtking.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.example.mtking.activity.CDTLuyActivity;
import com.example.mtking.activity.DoiDiemActivity;
import com.example.mtking.activity.HistoryActivity;
import com.example.mtking.activity.QuaTangActivity;
import com.example.mtking.R;
import com.example.mtking.activity.ThanhVienActivity;

public class DiemThuongFragment extends androidx.fragment.app.Fragment implements View.OnClickListener {
    RelativeLayout rllichsu;
    RelativeLayout rlcachdoi;
    RelativeLayout doidiem;
    RelativeLayout quatang;
    RelativeLayout thanhvien;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_diemthuong,container,false);
        rllichsu = rootview.findViewById(R.id.it_lichsudiemthuong);
        rllichsu.setOnClickListener(this);
        rlcachdoi = rootview.findViewById(R.id.it_ctluy);
        rlcachdoi.setOnClickListener(this);
        doidiem = rootview.findViewById(R.id.it_ddtluy);
        doidiem.setOnClickListener(this);
        quatang = rootview.findViewById(R.id.it_quatang);
        quatang.setOnClickListener(this);
        thanhvien = rootview.findViewById(R.id.it_tv);
        thanhvien.setOnClickListener(this);
        return rootview;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.it_lichsudiemthuong:
                startActivity(new Intent(getActivity(), HistoryActivity.class));
                break;
            case R.id.it_ctluy:
                startActivity(new Intent(getActivity(), CDTLuyActivity.class));
                break;
            case R.id.it_ddtluy:
                startActivity(new Intent(getActivity(), DoiDiemActivity.class));
                break;
            case R.id.it_quatang:
                startActivity(new Intent(getActivity(), QuaTangActivity.class));
                break;
            case R.id.it_tv:
                startActivity(new Intent(getActivity(), ThanhVienActivity.class));
                break;
        }

    }
}
