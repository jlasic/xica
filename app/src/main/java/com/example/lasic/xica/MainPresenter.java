package com.example.lasic.xica;

import android.content.Context;

import com.example.lasic.xica.data.CanteenData;
import com.example.lasic.xica.helpers.Utils;
import com.example.lasic.xica.singletons.DataManager;

/**
 * Created by lasic on 09.10.2017..
 */

public class MainPresenter {

    private MainPresenterListener listener;
    private Context mContext;
    private String currentReqEndpoint;
    private CanteenData currentCanteenData;

    public MainPresenter(Context context){
        mContext = context;
    }

    public MainPresenter(Context context, String canteenName){
        this(context);
        getCanteenData(canteenName);
    }

    public void setListener(MainPresenterListener listener) {
        this.listener = listener;
    }

    public void getCanteenData(final String canteenName){
        final String endpoint = Utils.getEndpoint(canteenName);

        if (endpoint == null)
            return;

        if (listener != null)
            listener.onLoading(true);

        currentReqEndpoint = endpoint;

        DataManager.getInstance(mContext).getCanteenData(endpoint, new DataManager.CanteenDataCallback() {
            @Override
            public void onSuccess(CanteenData data) {
                if (endpoint.equals(currentReqEndpoint))
                    notifyDataChanged(data);
            }

            @Override
            public void onError(String errorMessage) {
                if (endpoint.equals(currentReqEndpoint))
                    notifyDataChanged(null);
            }
        });
    }

    private void notifyDataChanged(CanteenData canteenData){
        currentCanteenData = canteenData;
        if (listener != null) {
            listener.onResponse(canteenData);
            listener.onLoading(false);
        }
    }

}
