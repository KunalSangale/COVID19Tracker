package com.example.covidtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Customad extends ArrayAdapter {
    public  String arr[];
    private List<Countries.Customitem> customitems;
    public Customad(@NonNull Context context, int resource, @NonNull List<Countries.Customitem> customitems) {
        super(context, resource, customitems);
        this.customitems=customitems;

    }

    @Nullable
    @Override
    public Countries.Customitem getItem(int position) {
        return customitems.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.listlayout,parent,false);
        TextView t=convertView.findViewById(R.id.textView6);
        t.setText(customitems.get(position).getCountries());
        ImageView i=convertView.findViewById(R.id.imageView3);
        Glide.with(getContext()).load(customitems.get(position).getFlag()).into(i);
        return  convertView;
    }
}
