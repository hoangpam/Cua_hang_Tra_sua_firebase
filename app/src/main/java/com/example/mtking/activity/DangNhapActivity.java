package com.example.mtking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mtking.R;
import com.example.mtking.object.KhachHang;
import com.facebook.common.Common;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {

    Button btResignter;
    Button btLogin;
    EditText etEmail;
    EditText etPassword;
    FirebaseAuth auth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bt_register1:
                Intent intent = new Intent(this, DangKyActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_login:
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if(!validateInput(email,password)){
                    return;
                }
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    private Object KhachHang;

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(DangNhapActivity.this, "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;

        }
    }
    void init()
    {
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        btResignter = findViewById(R.id.bt_register1);
        btResignter.setOnClickListener(this);

        btLogin=findViewById(R.id.bt_login);
        btLogin.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();
    }
    public boolean validateInput(String Email, String password) {
        boolean valid = true;
        if (Email.isEmpty()) {
            etEmail.setError("Không để trống!");
            valid = false;
        }
        else if (!Email.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")) {
            etEmail.setError("Email không đúng!");
            valid = false;
        } else {
            etEmail.setError(null);
        }

        if (password.isEmpty()) {
            etPassword.setError("Không để trống!");
            valid = false;
        } else if (password.length() < 8) {
            etPassword.setError("Tối thiểu 8 ký tự!");
            valid = false;
        } else {
            etPassword.setError(null);
        }
        return valid;
    }
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if(user != null)
        {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
    }
}