package com.example.lasic.xica.singletons;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by lasic on 09.10.2017..
 */

public class DataManager {
    private static Context mContext;
    private static DataManager mInstance;

    private DataManager(Context context){
        mContext = context;
    }

    public static DataManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DataManager(context.getApplicationContext());
        }
        return mInstance;
    }

    public JSONObject getCanteenData(String canteenName){
        return null;
    }
}
