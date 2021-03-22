package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textcases,textdeaths,textrecovered,textactive;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textcases=findViewById(R.id.textcases);
        textactive=findViewById(R.id.textactive);
        textrecovered=findViewById(R.id.textrecovered);
        textdeaths=findViewById(R.id.textdeaths);
        simpleArcLoader=findViewById(R.id.loader);
        scrollView=findViewById(R.id.scrollView2);
        pieChart=findViewById(R.id.piechart);
        button=findViewById(R.id.button);
        getdata();


    }
    public void openActivity(View v)
    {
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

    private void getdata() {
        String url="https://corona.lmao.ninja/v3/covid-19/all";
        final List<String> jsonResponses = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject jsonObject=new JSONObject(response.toString());
                    textcases.setText(jsonObject.getString("cases"));
                    textactive.setText(jsonObject.getString("active"));
                    textdeaths.setText(jsonObject.getString("deaths"));
                    textrecovered.setText(jsonObject.getString("recovered"));
                    pieChart.addPieSlice(new PieModel("Total Cases",Integer.parseInt(textcases.getText().toString()), Color.parseColor("#FFA726")));
                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(textactive.getText().toString()), Color.parseColor("#29B6F6")));
                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(textdeaths.getText().toString()), Color.parseColor("#EF5350")));
                    pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(textrecovered.getText().toString()), Color.parseColor("#66BB6A")));
                    pieChart.startAnimation();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);


                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "Please check your internet connection and restart the app", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
