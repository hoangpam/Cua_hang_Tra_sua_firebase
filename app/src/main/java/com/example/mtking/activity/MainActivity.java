package com.example.mtking.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mtking.R;
import com.example.mtking.fragment.CuaHangFragment;
import com.example.mtking.fragment.DiemThuongFragment;
import com.example.mtking.fragment.FragmentCTTV;
import com.example.mtking.fragment.FragmentDatBan;
import com.example.mtking.fragment.FragmentThongTinCaNhan;
import com.example.mtking.fragment.HomeFragment;
import com.example.mtking.fragment.TaiKhoanFragment;
import com.example.mtking.fragment.TinTucFragment;
import com.example.mtking.ui.CTTV.CTTVFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity  {
    private AppBarConfiguration mAppBarConfiguration;
    ImageButton imageButton;

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

    private Object fab;
    private  Toolbar toolbar;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        imageButton = findViewById(R.id.idgiohang);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GioHangActivity.class));
            }
        });

        load();
        //phần toolbar
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbar.inflateMenu(R.menu.menu_toolbar);

        drawer = findViewById(R.id.drawer_layout);

        //phần tin tức
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new TinTucFragment()).commit();


        //phần điểm thưởng

        //phần home
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_cttvien, R.id.nav_mggia, R.id.nav_dban)
                .setDrawerLayout(drawer)
                .build();


        //phần cửa hàng

    }
    public void load(){

        final ImageView tool = findViewById(R.id.imvsomenu);
        tool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Toast.makeText(getApplication(), "Chưa thiết lập nhé!!\uD83D\uDC96\uD83D\uDC96\uD83D\uDC96,Đi đái xíu quay lại chỉnh",
                        Toast.LENGTH_SHORT).show();*/

            }
        });

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles

    }



    //menu toolbar
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);

        return true;

    }*/


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }
    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_cttvien:
                fragmentClass = FragmentCTTV.class;
                break;
            case R.id.nav_dban:
                fragmentClass = FragmentDatBan.class;
                break;
            case R.id.nav_ttcnhan:
                fragmentClass = FragmentThongTinCaNhan.class;
                break;
            default:
                fragmentClass = FragmentCTTV.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_view);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @SuppressWarnings("StatemenWithEmtyBody")

    @Override
    public void onBackPressed(){


        final ImageView toolbar =  findViewById(R.id.imvsomenu);
        final DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!drawer.isDrawerOpen(GravityCompat.START)){
                    drawer.closeDrawer(GravityCompat.START);
                } else{
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getGroupId();

        if(id == R.id.nav_cttvien){
            loadFragment(new FragmentCTTV());
        } else if(id == R.id.nav_dban){
            loadFragment(new FragmentDatBan());
        } else if(id == R.id.nav_ttcnhan){
            loadFragment(new FragmentThongTinCaNhan());
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

}
