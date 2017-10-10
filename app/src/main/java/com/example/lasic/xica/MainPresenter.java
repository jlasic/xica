package com.example.lasic.xica;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lasic.xica.data.CanteenData;
import com.example.lasic.xica.helpers.JSONparser;
import com.example.lasic.xica.helpers.RequestHelper;
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

    public MainPresenter(Context context){
        mContext = context;
    }

    public MainPresenter(Context context, String canteenName){
        this(context);
        getCanteenData(canteenName);
    }


    public void getCanteenData(final String canteenName){
        final String endpoint = RequestHelper.getEndpoint(canteenName);

        if (endpoint == null)
            return;

        currentReqEndpoint = endpoint;

        JSONObject data = DataManager.getInstance(mContext).getCanteenData(endpoint);

        if (JSONparser.parse(data) == null){
            JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    Constants.BASE_URL.concat(endpoint),
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            CanteenData canteenData = JSONparser.parse(response);
                            if (listener != null)
                                listener.onResponse(canteenData);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
            // Add the request to the RequestQueue.
            RequestManager.getInstance(mContext).addToRequestQueue(jsObjRequest);
        }
    }


}
