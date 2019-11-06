package com.example.mtking.activity;

import android.Manifest;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.mtking.R;
import com.example.mtking.object.KhachHang;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener {
    Button btResignter;
    EditText etTenHienThi;
    EditText etSDT;
    EditText etMatKhau;
    EditText etNhapLaiMK;
    EditText etEmail;
    DatabaseReference reference;
//    ImageView imvAddAvartar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        init();
    }

    void init() {
        etTenHienThi = findViewById(R.id.et_tenhienthi);
        etMatKhau = findViewById(R.id.et_matkhau);
        etSDT = findViewById(R.id.et_sdt);
        etNhapLaiMK = findViewById(R.id.et_nhaplaimk);
        etEmail = findViewById(R.id.et_email);
        reference = FirebaseDatabase.getInstance().getReference("KhachHang");

        btResignter = findViewById(R.id.bt_register);
        btResignter.setOnClickListener(this);

//        imvAddAvartar = findViewById(R.id.addAvartar);
//        imvAddAvartar.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_register:

                final String tenHienThi = etTenHienThi.getText().toString();
                final String sdt = etSDT.getText().toString();
                final String matKhau = etMatKhau.getText().toString();
                String nhapLaimk = etNhapLaiMK.getText().toString();
                final String email = etEmail.getText().toString();
                if (!validateInput(tenHienThi, email, matKhau, nhapLaimk, sdt)) {
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, matKhau)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(DangKyActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                    updateUser(tenHienThi, firebaseAuth.getCurrentUser());
                                } else {
                                    Toast.makeText(DangKyActivity.this, "Quá trình đăng ký đã thất bại!\nCó thể email đã được đăng ký!\nVui lòng kiểm tra lại kết nối mạng.", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                break;
        }
    }

    private void updateUser(final String tenHienThi, final FirebaseUser currentUser) {
        final String sdt = etSDT.getText().toString();
        final String matKhau = etMatKhau.getText().toString();
        final String nhapLaimk = etNhapLaiMK.getText().toString();
        final String email = etEmail.getText().toString();
        KhachHang khachHang = new KhachHang(tenHienThi,email,matKhau,sdt,"","","");
        FirebaseDatabase.getInstance().getReference("KhachHang")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(khachHang).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }

    public boolean validateInput(String tenHienThi, String Email, String password, String passConfirm, String SDT) {
        boolean valid = true;
        if (tenHienThi.isEmpty()) {
            etTenHienThi.setError("Không để trống!");
            valid = false;
        }

        if (Email.isEmpty()) {
            etEmail.setError("Không để trống!");
            valid = false;
        } else if (!Email.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")) {
            etEmail.setError("Email không đúng!");
            valid = false;
        } else {
            etEmail.setError(null);
        }

        if (password.isEmpty()) {
            etMatKhau.setError("Không để trống!");
            valid = false;
        } else if (password.length() < 8) {
            etMatKhau.setError("Tối thiểu 8 ký tự!");
            valid = false;
        } else {
            etMatKhau.setError(null);
        }

        if (passConfirm.isEmpty()) {
            etNhapLaiMK.setError("Không để trống!");
            valid = false;
        } else if (passConfirm.length() < 8) {
            etNhapLaiMK.setError("Tối thiểu 8 ký tự!");
            valid = false;
        } else if (!password.equals(passConfirm)) {
            etNhapLaiMK.setError("Mật khẩu không khớp!");
            valid = false;
        } else {
            etNhapLaiMK.setError(null);
        }

        if (SDT.isEmpty()) {
            etSDT.setError("Không để trống!");
            valid = false;
        } else if (SDT.length() != 10) {
            etSDT.setError("Số điện thoại phải 10 số!");
            valid = false;
        } else {
            etSDT.setError(null);
        }
        return valid;
    }


    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
