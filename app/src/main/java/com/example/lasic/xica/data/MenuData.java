package com.example.lasic.xica.data;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by lasic on 12.06.2017..
 */

public class MenuData {

    public static final String NAME = "MENU_NAME";
    public static final String MEAL_ARRAY = "MENU_MEAL_ARRAY";
    public static final String START_TIME = "MENU_START_TIME";
    public static final String END_TIME = "MENU_END_TIME";

    @SerializedName(NAME)
    public String name;

    @SerializedName(START_TIME)
    public String startTime;

    @SerializedName(END_TIME)
    public String endTime;

    @SerializedName(MEAL_ARRAY)
    private ArrayList<MealData> meals;

    public ArrayList<MealData> getMeals() {
        return meals;
    }
}
