package com.example.mtking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mtking.R;

public class ChungToiActivity extends AppCompatActivity {
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chung_toi);
        load();
    }
public void load(){
    ll = findViewById(R.id.ll_tenthumuc);
    ll.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent p = new Intent(getApplicationContext(),SettingActivity.class);
            startActivity(p);
        }
    });
}

}
