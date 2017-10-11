package com.example.lasic.xica.singletons;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.lasic.xica.data.CanteenData;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by lasic on 09.10.2017..
 */

public class DataManager {
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

    public CanteenData getCanteenData(String endpoint){
        String stringResponse = preferences.getString(endpoint, "");

        CanteenData canteenData = new Gson().fromJson(stringResponse, CanteenData.class);

        if (canteenData != null && canteenData.hasValidInfo())
            return canteenData;
        else {
            preferences.edit().putString(endpoint, null).apply();
            return null;
        }
    }

    public void makeCache(String endpoint, JSONObject jsonObject){
        preferences.edit().putString(endpoint, jsonObject.toString()).apply();
    }
}
