package com.example.denemefinal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denemefinal.MainActivity;
import com.example.denemefinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;

    Button buton_login;

    TextView toRegister;

    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);

        buton_login = findViewById(R.id.login_btn);

        toRegister = findViewById(R.id.login_toRegister);

        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        buton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_email = email.getText().toString();
                String str_password = password.getText().toString();

                if(str_email.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Email alanı boş bırakılamaz", Toast.LENGTH_SHORT).show();
                }

                if(str_password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Şifre alanı boş bırakılamaz", Toast.LENGTH_SHORT).show();
                }

                auth = FirebaseAuth.getInstance();

                auth.signInWithEmailAndPassword(str_email, str_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Giriş başarılı, ana sayfaya yönlendiriliyorsunuz", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else {
                            Toast.makeText(LoginActivity.this, "Giriş işlemi esnasında bilinmeyen bir hata oluştu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}