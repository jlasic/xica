package com.example.lasic.xica.singletons;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lasic.xica.data.CanteenData;
import com.example.lasic.xica.helpers.Constants;
import com.example.lasic.xica.helpers.Utils;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by lasic on 09.10.2017..
 */

public class DataManager {

    public interface CanteenDataCallback {
        void onSuccess(CanteenData data);
        void onError(String errorMessage);
    }
    private static final String TAG = "DataManager";

    private static final String PREF_NAME = "data_manager";
    private static final String BASE_STRING = "data_canteen";

    private static Context mContext;
    private static DataManager mInstance;
    private SharedPreferences preferences;

    private DataManager(Context context){
        mContext = context;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static DataManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DataManager(context.getApplicationContext());
        }
        return mInstance;
    }

    public void getCanteenData(@NonNull final String endpoint, final CanteenDataCallback canteenDataCallback){
        CanteenData canteenData = getCachedCanteenData(endpoint);

        if (canteenData != null && canteenDataCallback != null){
            canteenDataCallback.onSuccess(canteenData);
        }
        else {
            JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    Constants.BASE_URL.concat(endpoint),
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            CanteenData canteenData = null;
                            JSONObject fixedJSON = Utils.fixResponse(response);

                            if (fixedJSON != null){
                                canteenData = new Gson().fromJson(fixedJSON.toString(), CanteenData.class);
                                if (canteenData != null) {
                                    makeCache(endpoint, fixedJSON);
                                    if (canteenDataCallback != null)
                                        canteenDataCallback.onSuccess(canteenData);
                                    return;
                                }
                            }
                            if (canteenDataCallback != null)
                                canteenDataCallback.onError("WRONG SERVER RESPONSE");
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //TODO: error response
                            if (canteenDataCallback != null)
                                canteenDataCallback.onError("REQUEST ERROR");
                        }
                    });
            RequestManager.getInstance(mContext).addToRequestQueue(jsObjRequest);
        }
    }

    private CanteenData getCachedCanteenData(String endpoint){
        String stringResponse = preferences.getString(endpoint, "");

        CanteenData canteenData = new Gson().fromJson(stringResponse, CanteenData.class);

        if (canteenData == null)
            preferences.edit().putString(endpoint, null).apply();

        return canteenData;
    }

    private void makeCache(String endpoint, JSONObject jsonObject){
        preferences.edit().putString(endpoint, jsonObject.toString()).apply();
    }
}
