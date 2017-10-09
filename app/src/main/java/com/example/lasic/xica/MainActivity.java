package com.example.lasic.xica;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Jelovnik currentJelovnik;

    TextView mTextView1;
    TextView mTextView2;
    private RequestQueue mQueue;
    private String url;
    private Context mContext;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: STARTED");

        mContext = this;
        mListView = (ListView) findViewById(R.id.meniList);

        Button kampus = (Button) findViewById(R.id.kampus);
        kampus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "http://filestest.dbtouch.com/scst/menu/api/?place=kampus";
                request();
            }
        });
        Button fesb = (Button) findViewById(R.id.fesb);
        fesb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "http://filestest.dbtouch.com/scst/menu/api/?place=fesb_vrh";
                request();
            }
        });
        Button efst = (Button) findViewById(R.id.efst);
        efst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "http://filestest.dbtouch.com/scst/menu/api/?place=efst";
                request();
            }
        });

        // Instantiate the RequestQueue.

        mQueue = Volley.newRequestQueue(this);



    }

    private void request(){
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray json =response.getJSONArray("values");


                            currentJelovnik = JSONparser.Parse(json);

                            String output = "";

                            ArrayList<Stavka> meniStavke = currentJelovnik.getrMeni().getStavka();

                            MeniAdapter adapter = new MeniAdapter(mContext, R.layout.adapter_view_layout,meniStavke);
                            mListView.setAdapter(adapter);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getApplicationContext(), "Greska!", Toast.LENGTH_SHORT).show();
                    }
                });
        // Add the request to the RequestQueue.
        mQueue.add(jsObjRequest);
    }
}
