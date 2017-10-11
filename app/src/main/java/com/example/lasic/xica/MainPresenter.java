package com.example.lasic.xica;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lasic.xica.data.CanteenData;
import com.example.lasic.xica.helpers.JSONparser;
import com.example.lasic.xica.helpers.Utils;
import com.example.lasic.xica.singletons.DataManager;
import com.example.lasic.xica.singletons.RequestManager;

import org.json.JSONObject;

/**
 * Created by lasic on 09.10.2017..
 */

public class MainPresenter {
    public interface Listener{
        void onResponse(CanteenData canteenData);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    private Listener listener;
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


    public void getCanteenData(final String canteenName){
        final String endpoint = Utils.getEndpoint(canteenName);

        if (endpoint == null)
            return;

        currentReqEndpoint = endpoint;

        JSONObject data = DataManager.getInstance(mContext).getCanteenData(endpoint);
        CanteenData canteenData = JSONparser.parse(data);

        if (canteenData != null){
            notifyDataChanged(canteenData);
        }
        else {
            JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    Constants.BASE_URL.concat(endpoint),
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            CanteenData canteenData = JSONparser.parse(response);

                            if (canteenData != null) {
                                DataManager.getInstance(mContext).makeCache(endpoint, response);
                                notifyDataChanged(canteenData);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            RequestManager.getInstance(mContext).addToRequestQueue(jsObjRequest);
        }
    }

    private void notifyDataChanged(CanteenData canteenData){
        currentCanteenData = canteenData;
        if (listener != null)
            listener.onResponse(canteenData);
    }

}
