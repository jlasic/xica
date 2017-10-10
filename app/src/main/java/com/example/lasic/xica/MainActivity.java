package com.example.lasic.xica;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.example.lasic.xica.data.CanteenData;
import com.example.lasic.xica.data.DishData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

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
