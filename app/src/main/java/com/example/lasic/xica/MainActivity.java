package com.example.lasic.xica;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lasic.xica.data.CanteenData;
import com.example.lasic.xica.helpers.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainPresenterListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.spinner)
    Spinner spinner;

    private MainPresenter presenter;
    private MainPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter =  new MainPresenter(this, null);
        presenter.setListener(this);

        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Constants.CanteenName.NAME_ARRAY);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.getCanteenData(Constants.CanteenName.NAME_ARRAY[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onResponse(CanteenData canteenData) {
        pagerAdapter.getItem(0).setData(canteenData.getLunchMenu());
        pagerAdapter.getItem(1).setData(canteenData.getDinnerMenu());
    }

    private static class MainPagerAdapter extends FragmentPagerAdapter{

        private MainFragment dinnerFragment;
        private MainFragment lunchFragment;

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public MainFragment getItem(int position) {
            switch (position){
                case 0:
                    if (lunchFragment == null)
                        lunchFragment = MainFragment.newInstance(position);
                    return lunchFragment;
                case 1:
                    if (dinnerFragment == null)
                        dinnerFragment = MainFragment.newInstance(position);
                    return dinnerFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
