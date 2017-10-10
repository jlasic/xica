package com.example.lasic.xica;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lasic.xica.data.CanteenData;
import com.example.lasic.xica.data.DishData;
import com.example.lasic.xica.helpers.JSONparser;
import com.example.lasic.xica.singletons.RequestManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private CanteenData currentCanteenData;

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

        mContext = this;
        mListView = (ListView) findViewById(R.id.meniList);

        final MainPresenter presenter =  new MainPresenter(this, Constants.CanteenName.FESB);
        presenter.setListener(new MainPresenter.Listener() {
            @Override
            public void onResponse(CanteenData currentCanteenData) {
                String output = "";

                ArrayList<DishData> meniStavke = currentCanteenData.getrMenuData().getDishData();

                MeniAdapter adapter = new MeniAdapter(mContext, R.layout.adapter_view_layout,meniStavke);
                mListView.setAdapter(adapter);
            }
        });

        Button kampus = (Button) findViewById(R.id.kampus);
        kampus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getCanteenData(Constants.CanteenName.KAMPUS);
            }
        });
        Button fesb = (Button) findViewById(R.id.fesb);
        fesb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getCanteenData(Constants.CanteenName.FESB);
            }
        });
        Button efst = (Button) findViewById(R.id.efst);
        efst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getCanteenData(Constants.CanteenName.EKONOMIJA);
            }
        });
    }
}
