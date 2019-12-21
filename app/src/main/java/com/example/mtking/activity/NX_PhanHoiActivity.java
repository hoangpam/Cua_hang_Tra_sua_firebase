package com.example.mtking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mtking.R;

public class NX_PhanHoiActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nx__phan_hoi);
        load();
    }
    public void load(){
        ll = findViewById(R.id.ll_tenthumuc);
        ll.setOnClickListener(this);
        ll = findViewById(R.id.ll_chonphanloai);
        ll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_tenthumuc:
                Toast.makeText(getApplication(), "Chưa thiết lập nhé!!\uD83D\uDC96\uD83D\uDC96\uD83D\uDC96,Đi đái xíu quay lại chỉnh",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_chonphanloai:
                Toast.makeText(getApplication(), "Món yêu thích" +
                                                        ",  Món nổi bật " +
                                                        ",  Trà sữa, Chè " +
                                                        " ,  Fresh Fruit Tea" +
                                                        ",   Mecchiato" +
                                                        ",   Speacial Drinks" +
                                                        ",   Bánh " +
                                                        ",   Đò Ăn Vặt",
                                                Toast.LENGTH_LONG).show();
                break;
        }
    }
}
