package com.example.lasic.xica.data;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by lasic on 12.06.2017..
 */

public class MealData {
    public static final String NAME = "MEAL_NAME";
    public static final String DISH_ARRAY = "MEAL_DISH_ARRAY";
    public static final String PRICE = "MEAL_PRICE";

    @SerializedName(NAME)
    public String name;

    @SerializedName(PRICE)
    public String price;

    @SerializedName(DISH_ARRAY)
    public ArrayList<String> dishes;

    public ArrayList<String> getDishes() {
        return dishes;
    }
}
