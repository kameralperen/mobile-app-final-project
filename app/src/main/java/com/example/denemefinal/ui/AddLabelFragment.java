package com.example.denemefinal.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import android.widget.ListView;

import com.example.denemefinal.R;
import com.example.denemefinal.adapters.LabelAdapter;
import com.example.denemefinal.models.LabelModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import android.widget.Toast;
import android.widget.ArrayAdapter;


public class AddLabelFragment extends Fragment{private EditText labelInput, descriptionInput;
    private Button addButton;
    private ListView labelsListView;
    private ArrayList<String> labelsList;
    private ArrayAdapter<String> labelAdapter;

    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addlabel, container, false);

        labelInput = view.findViewById(R.id.labelInput);
        descriptionInput = view.findViewById(R.id.descriptionInput);
        addButton = view.findViewById(R.id.addButton);
        labelsListView = view.findViewById(R.id.labelsListView);

        labelsList = new ArrayList<>();
        labelAdapter = new LabelAdapter(getContext(), labelsList);
        labelsListView.setAdapter(labelAdapter);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Tags");

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLabel();
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                labelsList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    LabelModel label = snapshot.getValue(LabelModel.class);
                    if (label != null) {
                        labelsList.add(label.getLabelName());
                    }
                }

                labelAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // HATA DURUMU
            }
        });

        return view;
    }


    private void addLabel() {
        String labelName = labelInput.getText().toString().trim();
        String description = descriptionInput.getText().toString().trim();

        if (!TextUtils.isEmpty(labelName) && !TextUtils.isEmpty(description)) {
            LabelModel label = new LabelModel(labelName, description);

            String labelId = databaseReference.push().getKey();
            databaseReference.child(labelId).setValue(label);

            labelInput.setText("");
            descriptionInput.setText("");

            fetchLabels();
        } else {
            Toast.makeText(getContext(), "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchLabels() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                labelsList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    LabelModel label = snapshot.getValue(LabelModel.class);
                    if (label != null) {
                        labelsList.add(label.getLabelName());
                    }
                }
                labelAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // HATA DURUMU
            }
    });
}
}