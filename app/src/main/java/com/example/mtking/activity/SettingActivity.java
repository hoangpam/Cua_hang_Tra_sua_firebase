package com.example.mtking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mtking.R;
import com.example.mtking.fragment.TaiKhoanFragment;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout setting;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        load();
    }

    private void load(){
        setting = findViewById(R.id.it_quandiachi);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(getApplicationContext(),QuanLyDiaChiActivity.class);
                startActivity(n);
            }
        });
        setting = findViewById(R.id.it_sdt);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(getApplicationContext(), SDTActivity.class);
                startActivity(m);
            }
        });
        setting = findViewById(R.id.it_mk);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(getApplicationContext(),QuanLyMKActivity.class);
                startActivity(l);
            }
        });
        setting = findViewById(R.id.it_gmail);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(getApplicationContext(), GmailActivity.class);
                startActivity(k);
            }
        });
        setting = findViewById(R.id.it_hethong);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(getApplicationContext(),HeThongActivity.class);
                startActivity(b);
            }
        });
        setting = findViewById(R.id.it_chungtoi);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent z = new Intent(getApplicationContext(), ChungToiActivity.class);
                startActivity(z);
            }
        });
        ll = findViewById(R.id.ll_trove);
        ll.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_trove:
                Toast.makeText(getApplication(), "Chưa thiết lập nhé!!\uD83D\uDC96\uD83D\uDC96\uD83D\uDC96,Đi đái xíu quay lại chỉnh",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

