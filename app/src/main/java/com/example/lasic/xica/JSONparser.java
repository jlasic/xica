package com.example.lasic.xica;

import android.util.Log;

import com.example.lasic.xica.data.Jelovnik;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by lasic on 12.06.2017..
 */

public class JSONparser {
    private static final String TAG = "JSONparser";

    public static Jelovnik Parse(JSONArray jsonArray){
        Jelovnik jelovnik = new Jelovnik();

        for (int i=0; i<jsonArray.length(); i++){
            try {
                if (jsonArray.getJSONArray(i).length() >= 2) {
                    String item;
                    JSONArray tmpArray = jsonArray.getJSONArray(i);
                    Log.d(TAG, "Parse: " + tmpArray);

                    item = tmpArray.getString(0);
                    if (item.contains("-"))
                        jelovnik.addRMeniItem(tmpArray);
//                    switch (item) {
//                        case "R-MENI":
//                        case "R-JELO PO IZBORU":
//                            jelovnik.addRMeniItem(tmpArray);
//                            break;
//                        case "V-MENI":
//                        case "V-JELO PO IZBORU":
//                            jelovnik.addVMeniItem(tmpArray);
//                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jelovnik;
    }
}
