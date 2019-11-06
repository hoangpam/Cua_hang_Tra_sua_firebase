package com.example.mtking.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.mtking.R;
import com.example.mtking.fragment.CuaHangFragment;
import com.example.mtking.fragment.DiemThuongFragment;
import com.example.mtking.fragment.HomeFragment;
import com.example.mtking.fragment.TaiKhoanFragment;
import com.example.mtking.fragment.TinTucFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity  {
    private AppBarConfiguration mAppBarConfiguration;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected( MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.bottomTintuc:
                    selectedFragment = new TinTucFragment();
                    break;
                case R.id.bottomDiemThuong:
                    selectedFragment = new DiemThuongFragment();
                    break;
                case R.id.bottomHome:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.bottomCuahang:
                    selectedFragment = new CuaHangFragment();
                    break;
                case R.id.bottomTaiKhoan:
                    selectedFragment = new TaiKhoanFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();
            return true;
        }
    };
    private Toolbar toolbar;
    private Object fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        //phần tin tức
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new TinTucFragment()).commit();
        //phần điểm thưởng
        
        //phần home
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //phần cửa hàng

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_cttvien, R.id.nav_mggia, R.id.nav_dban)
                .setDrawerLayout(drawer)
                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public void onBackPressed(){
//        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
        final ImageView toolbar = (ImageView) findViewById(R.id.toolbar);
        final DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawer.isDrawerOpen(GravityCompat.START)){
                    drawer.closeDrawer(GravityCompat.START);
                } else{
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
    }

}
