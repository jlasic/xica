package com.example.lasic.xica;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lasic.xica.data.MenuData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lasic on 11.10.2017..
 */

public class MainAdapter extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private Context context;

    private MenuData data;

    public MainAdapter(Context context){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(MenuData data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_view_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        String nazivMeni = data.getMeals().get(position).getName();
        String cijena = data.getMeals().get(position).getPrice();
        String sastav = data.getMeals().get(position).getDishes().get(0);


        viewHolder.tvNaziv.setText(nazivMeni);
        viewHolder.tvItem.setText(sastav);
        viewHolder.tvCijena.setText(cijena);

    }

    public int getItemCount() {
        if (data != null)
            return data.getSize();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.meniTV)
        TextView tvNaziv;

        @BindView(R.id.itemTV)
        TextView tvItem;

        @BindView(R.id.cijenaTV)
        TextView tvCijena;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
