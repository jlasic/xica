package com.example.lasic.xica.helpers;

import android.util.Log;

import com.example.lasic.xica.data.CanteenData;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by lasic on 12.06.2017..
 */

public class JSONparser {
    private static final String TAG = "JSONparser";

    public static CanteenData Parse(JSONArray jsonArray){
        CanteenData canteenData = new CanteenData();

        for (int i=0; i<jsonArray.length(); i++){
            try {
                if (jsonArray.getJSONArray(i).length() >= 2) {
                    String item;
                    JSONArray tmpArray = jsonArray.getJSONArray(i);
                    Log.d(TAG, "Parse: " + tmpArray);

                    item = tmpArray.getString(0);
                    if (item.contains("-"))
                        canteenData.addRMeniItem(tmpArray);
//                    switch (item) {
//                        case "R-MENI":
//                        case "R-JELO PO IZBORU":
//                            canteenData.addRMeniItem(tmpArray);
//                            break;
//                        case "V-MENI":
//                        case "V-JELO PO IZBORU":
//                            canteenData.addVMeniItem(tmpArray);
//                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return canteenData;
    }
}
