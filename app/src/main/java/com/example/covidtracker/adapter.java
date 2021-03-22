package com.example.covidtracker;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class adapter  extends ArrayAdapter<Countries> {
    private Context context;
    private List<Countries>countriesList;
    public adapter(Context context, List<Countries> countriesList) {
        super(context, R.layout.listlayout,countriesList);
        this.context=context;
        this.countriesList=countriesList;
    }
}
