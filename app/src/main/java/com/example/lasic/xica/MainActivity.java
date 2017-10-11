package com.example.lasic.xica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.lasic.xica.data.CanteenData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private MainPresenter presenter;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        adapter = new MainAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        presenter =  new MainPresenter(this, null);
        presenter.setListener(new MainPresenter.Listener() {
            @Override
            public void onResponse(CanteenData currentCanteenData) {
                adapter.setData(currentCanteenData.getLunchMenu());
            }
        });
    }

    @OnClick({R.id.kampus, R.id.fesb, R.id.efst})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.kampus:
                presenter.getCanteenData(Constants.CanteenName.KAMPUS);
                break;
            case R.id.fesb:
                presenter.getCanteenData(Constants.CanteenName.FESB);
                break;
            case R.id.efst:
                presenter.getCanteenData(Constants.CanteenName.EKONOMIJA);
                break;
        }
    }
}
