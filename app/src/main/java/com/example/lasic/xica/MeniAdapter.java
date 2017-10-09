package com.example.lasic.xica;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lasic.xica.data.Stavka;

import java.util.ArrayList;

/**
 * Created by lasic on 13.06.2017..
 */

public class MeniAdapter extends ArrayAdapter<Stavka> {
    private static final String TAG = "MeniAdapter";
    private Context mContext;
    private int mResource;

    public MeniAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Stavka> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String nazivMeni = getItem(position).getIme();
        String sastav = getItem(position).getOutputSastav();
        String cijena = getItem(position).getCijena();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvNaziv = (TextView) convertView.findViewById(R.id.meniTV);
        TextView tvItem = (TextView) convertView.findViewById(R.id.itemTV);
        TextView tvCijena = (TextView) convertView.findViewById(R.id.cijenaTV);

        tvNaziv.setText(nazivMeni);
        tvItem.setText(sastav);
        tvCijena.setText(cijena);
        return convertView;
    }
}
