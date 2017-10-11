package com.example.lasic.xica.data;

import com.example.lasic.xica.helpers.Utils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by lasic on 12.06.2017..
 */

public class CanteenData {

    public static final String NAME = "CANTEEN_NAME";
    public static final String DATE_START = "CANTEEN_DATE_START";
    public static final String DATE_END = "CANTEEN_DATE_END";
    public static final String MENU_DINNER = "CANTEEN_MENU_DINNER";
    public static final String MENU_LUNCH = "CANTEEN_MENU_LUNCH";

    @SerializedName(NAME)
    public String name;

    @SerializedName(DATE_START)
    private String dateStart;

    @SerializedName(DATE_END)
    private String dateEnd;

    @SerializedName(MENU_LUNCH)
    private MenuData lunchMenu;

    @SerializedName(MENU_DINNER)
    private MenuData dinnerMenu;

    public MenuData getLunchMenu() {
        return lunchMenu;
    }
    public MenuData getDinnerMenu() {
        return dinnerMenu;
    }

    public int getSize(){
        int lunchSize = lunchMenu != null ? lunchMenu.getSize() : 0;
        int dinnerSize = dinnerMenu != null ? dinnerMenu.getSize() : 0;
        return dinnerSize + lunchSize;
    }

    public boolean hasValidInfo(){
        String s = Utils.getFormattedDate();
        return s.equals(dateEnd);
    }
}
