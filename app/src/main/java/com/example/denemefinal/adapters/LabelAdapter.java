package com.example.denemefinal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.denemefinal.R;

import java.util.ArrayList;

public class LabelAdapter extends ArrayAdapter<String> {
    public LabelAdapter(Context context, ArrayList<String> labels) {
        super(context, 0, labels);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String label = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.label_desc, parent, false);
        }

        TextView labelTextView = convertView.findViewById(R.id.labelTextView);
        labelTextView.setText(label);

        return convertView;
        }

}
