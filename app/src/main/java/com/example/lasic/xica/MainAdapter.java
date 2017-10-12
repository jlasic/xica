package com.example.lasic.xica;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lasic.xica.data.MenuData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lasic on 11.10.2017..
 */

public class MainAdapter extends RecyclerView.Adapter {

    private static final int TYPE_NO_DATA = 0;
    private static final int TYPE_NORMAL = 1;

    private LayoutInflater inflater;
    private Context context;

    private MenuData data;
    private boolean isLoading = true;

    public MainAdapter(Context context){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(MenuData data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void setLoading(boolean isLoading){
        this.isLoading = isLoading;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            View view = inflater.inflate(R.layout.item_main, parent, false);
            return new ViewHolder(view);
        }
        else {
            View view = inflater.inflate(R.layout.item_no_data, parent, false);
            return new NoDataViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            ViewHolder viewHolder = (ViewHolder) holder;

            String nazivMeni = data.getMeals().get(position).getName();
            String cijena = data.getMeals().get(position).getPrice();
            String sastav = TextUtils.join("\n", data.getMeals().get(position).getDishes());


            viewHolder.tvNaziv.setText(nazivMeni);
            viewHolder.tvItem.setText(sastav);
            viewHolder.tvCijena.setText(cijena);
        }
        else {
            NoDataViewHolder viewHolder = (NoDataViewHolder) holder;
            viewHolder.setLoading(isLoading);
        }
    }

    public int getItemCount() {
        if (data != null)
            return data.getSize();
        else
            return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return data == null || isLoading ? TYPE_NO_DATA : TYPE_NORMAL;
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

    public class NoDataViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.loader_no_data)
        ProgressBar progressBar;

        @BindView(R.id.warning_no_data)
        TextView tvWarning;

        public NoDataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setLoading(boolean isLoading){
            progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            tvWarning.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        }
    }
}
