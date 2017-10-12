package com.example.lasic.xica;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.lasic.xica.data.CanteenData;
import com.example.lasic.xica.helpers.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainPresenterListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.viewPager)
    ViewPager viewPager;

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
