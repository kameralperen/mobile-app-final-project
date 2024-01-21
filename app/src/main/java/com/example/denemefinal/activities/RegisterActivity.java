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

import com.example.denemefinal.R;
import com.example.denemefinal.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    EditText name, lastname, email, password;

    Button button;

    TextView toLogin;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.reg_name);
        lastname = findViewById(R.id.reg_lastname);
        email = findViewById(R.id.reg_email);
        password = findViewById(R.id.reg_password);

        button = findViewById(R.id.reg_btn);

        toLogin = findViewById(R.id.reg_toLogin);


        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_name = name.getText().toString();
                String str_lastname = lastname.getText().toString();
                String str_email = email.getText().toString();
                String str_password = password.getText().toString();

                if(str_name.isEmpty() && str_lastname.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "İsim ve Soyisim alanı boş bırakılamaz", Toast.LENGTH_SHORT).show();
                }

                if(str_email.isEmpty() && str_password.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Email ve şifre alanı boş bırakılamaz", Toast.LENGTH_SHORT).show();
                }

                FirebaseAuth auth = FirebaseAuth.getInstance();

                auth.createUserWithEmailAndPassword(str_email, str_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String uid = task.getResult().getUser().getUid();

                            Toast.makeText(RegisterActivity.this, "Kayıt başarılı, giriş ekranından uygulamaya giriş yapabilirsiniz", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            CollectionReference ref = db.collection("UserModel");

                            UserModel user = new UserModel(str_email, str_lastname, str_name);

                            ref.add(user);
                        }else{
                            Toast.makeText(RegisterActivity.this, "Kayıt işlemi sırasında bir hata oluştu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}