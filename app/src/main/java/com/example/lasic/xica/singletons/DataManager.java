package com.example.lasic.xica.singletons;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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

    public JSONObject getCanteenData(String endpoint){
        String stringResponse = preferences.getString(endpoint, null);
        if (stringResponse != null){
            try {
                return new JSONObject(stringResponse);
            }catch (Exception e){
                preferences.edit().putString(endpoint, null).apply();
                return null;
            }
        }
        return null;
    }

    public void makeCache(String endpoint, JSONObject jsonObject){
        preferences.edit().putString(endpoint, jsonObject.toString()).apply();
    }
}
