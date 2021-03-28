package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import com.leo.simplearcloader.SimpleArcLoader;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Countries extends AppCompatActivity {
    ListView listView;
    public  static  List<Customitem> customitems = new ArrayList<Customitem>();
   // Countries countries;
    SimpleArcLoader simpleArcLoader;
    //CustomAdapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        listView = findViewById(R.id.listView);
        //String arr[]={"Indai","ad","fa"};
        //ArrayAdapter<String> ad=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arr);
        //listView.setAdapter(ad);
        getData();

    }

    public void getData() {
        String url = "https://corona.lmao.ninja/v3/covid-19/countries/";
       // simpleArcLoader.start();
        final List<String> jsonResponses = new ArrayList<>();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    final String[] arr=new String[jsonArray.length()];
                    {
                        for(int i=0;i<jsonArray.length();i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String countries = jsonObject.getString("country");
                            arr[i] = countries;
                            //System.out.println(arr[0]);
                            //Toast.makeText(Countries.this, arr[0], Toast.LENGTH_SHORT).show();

                            JSONObject object = jsonObject.getJSONObject("countryInfo");
                            String flag = object.getString("flag");
                            Customitem c = new Customitem(countries, flag);
                            customitems.add(c);
                        }
                    }
                    Customad ad=new Customad(Countries.this,R.layout.listlayout,customitems);
                    //ArrayAdapter<String> ad= new ArrayAdapter<>(Countries.this,android.R.layout.simple_list_item_1,arr);
                    listView.setAdapter(ad);
                    //simpleArcLoader.stop();
                  //  simpleArcLoader.setVisibility(View.GONE);
                    //Toast.makeText(countries, arr[0], Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Countries.this, "Please check your internet connection and restart the app", Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

   class Customitem {
        String countries, flag;

        public Customitem(String countries, String flag) {
            this.countries = countries;
            this.flag = flag;
        }

        public String getCountries() {
            return countries;
        }

        public void setCountries(String countries) {
            this.countries = countries;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }
    }
}