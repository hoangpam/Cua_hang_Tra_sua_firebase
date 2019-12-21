package com.example.mtking.activity;
//
//import android.os.Bundle;
//
//import com.example.mtking.Interface.ItemClickListener;
//
//import com.example.mtking.R;
//import com.example.mtking.ViewHolder.MenuViewHolder;
//import com.example.mtking.adapter.HomeAdapter;
//import com.example.mtking.object.Category;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.android.material.snackbar.Snackbar;
//
//import android.util.Log;
//import android.view.MenuItem;
//import android.view.View;
//
//import androidx.annotation.NonNull;
//import androidx.core.view.GravityCompat;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;
//
//import com.google.android.material.navigation.NavigationView;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.squareup.picasso.Picasso;
//
//import androidx.drawerlayout.widget.DrawerLayout;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.recyclerview.widget.DefaultItemAnimator;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.Menu;
//import android.widget.ImageView;
//
//import java.util.List;
//
public class HomeActivity  {}
//
//    private AppBarConfiguration mAppBarConfiguration;
//    DatabaseReference caterogy;
//
//    RecyclerView recyclerView_menu;
//    RecyclerView.LayoutManager layoutManager;
//
//    List<Category> categoryList;
//    RecyclerView recyclerView;
//    HomeAdapter homeAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("Menu");
//        setSupportActionBar(toolbar);
//
//        //Init Firebase
//        database = FirebaseDatabase.getInstance();
//        caterogy = database.getReference("Category");
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_cttvien, R.id.nav_mggia, R.id.nav_dban)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//
//        //Load menu
//        recyclerView_menu = (RecyclerView)findViewById(R.id.rcv_menu);
//        recyclerView_menu.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView_menu.setLayoutManager(layoutManager);
//
//
//        loadMenu();
//    }
//
//    private void loadMenu() {
//
//        recyclerView = findViewById(R.id.rcv_menu);
//        caterogy.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                categoryList.clear();
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    Category category = snapshot.getValue(Category.class);
//                    Log.e("anh",category.getImage());
//                    categoryList.add(category);
//
//                }
//                homeAdapter = new HomeAdapter(getApplicationContext(),categoryList);
//                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
//                recyclerView.setLayoutManager(mLayoutManager);
//                recyclerView.setItemAnimator(new DefaultItemAnimator());
//                recyclerView.setAdapter(homeAdapter);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    @Override
//    public void onBackPressed(){
//        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
////        return true;
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
//
//    @SuppressWarnings("StatemenWithEmtyBody")
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        int id = menuItem.getGroupId();
//
//        if(id == R.id.nav_cttvien){
//
//        } else if(id == R.id.nav_dban){
//
//        } else if(id == R.id.nav_mggia){
//
//        } else if(id == R.id.nav_ttcnhan){
//
//        }
//
//        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//}
