package com.example.denemefinal.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.denemefinal.MainActivity;
import com.example.denemefinal.R;
import com.example.denemefinal.activities.SplashActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragment extends Fragment {

    Button logout_btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                         ViewGroup container, Bundle savedInstanceState){

    View root = inflater.inflate(R.layout.fragment_logout, container, false);

    logout_btn = root.findViewById(R.id.logout_btn);

    logout_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FirebaseAuth auth = FirebaseAuth.getInstance();

            if(auth != null && auth.getCurrentUser() != null){
                auth.signOut();
                Toast.makeText(getContext(), "Hesaptan çıkış yapılıyor", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), SplashActivity.class));
            }else{
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        }
    });

    return root;
    }
}