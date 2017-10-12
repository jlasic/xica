package com.example.lasic.xica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lasic.xica.data.MenuData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lasic on 12.10.2017..
 */

public class MainFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private MainAdapter adapter;

    public static MainFragment newInstance(int type){
        MainFragment fragment = new MainFragment();

        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        adapter = new MainAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    public void setData(MenuData menuData){
        adapter.setData(menuData);
    }
}
