package com.example.denemefinal.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.denemefinal.R;
import com.example.denemefinal.adapters.GalleryAdapter;
import com.example.denemefinal.models.GalleryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private static final String TAG = "card";

    ArrayList<GalleryModel> carditems;

    GalleryAdapter cardItemAdapter;

    RecyclerView cardRV;

    FirebaseFirestore ff = FirebaseFirestore.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        cardRV = root.findViewById(R.id.cardRV);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        cardRV.setLayoutManager(llm);



        carditems = new ArrayList<>();
        cardItemAdapter = new GalleryAdapter(getActivity(), carditems);
        cardRV.setAdapter(cardItemAdapter);

        getCarditems();


        return root;


    }

    private void getCarditems() {
        ff.collection("Gallery").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isComplete()){
                    for (QueryDocumentSnapshot eleman: task.getResult()){
                        Log.d(TAG, "veri çekildi");
                        GalleryModel gm = eleman.toObject(GalleryModel.class);
                        carditems.add(gm);
                    }
                    cardItemAdapter.notifyDataSetChanged();
                }else{
                    Log.d(TAG, task.getException().toString());
                }
            }
        });

    }
}