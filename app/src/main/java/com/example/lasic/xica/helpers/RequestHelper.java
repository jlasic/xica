package com.example.lasic.xica.helpers;

import android.support.annotation.NonNull;

import com.example.lasic.xica.Constants;

/**
 * Created by lasic on 10.10.2017..
 */

public final class RequestHelper {

    public static String getEndpoint(@NonNull String canteenName){
        for (int i = 0 ; i < Constants.CanteenName.NAME_ARRAY.length ; i++)
            if (Constants.CanteenName.NAME_ARRAY[i].equals(canteenName))
                return Constants.Endpoint.ENDPOINT_ARRAY[i];

        return null;
    }

    public static String getCanteenName(@NonNull String endpoint){
        for (int i = 0 ; i < Constants.Endpoint.ENDPOINT_ARRAY.length ; i++)
            if (Constants.Endpoint.ENDPOINT_ARRAY[i].equals(endpoint))
                return Constants.CanteenName.NAME_ARRAY[i];

        return null;
    }
}
