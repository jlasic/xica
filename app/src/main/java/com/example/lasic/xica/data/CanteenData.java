package com.example.lasic.xica.data;

import org.json.JSONArray;

/**
 * Created by lasic on 12.06.2017..
 */

public class CanteenData {

    public static final String NAME = "CANTEEN_NAME";
    public static final String DATE_FROM = "CANTEEN_DATE_FROM";
    public static final String DATE_TO = "CANTEEN_DATE_TO";
    public static final String MENU_DINNER = "CANTEEN_MENU_DINNER";
    public static final String MENU_LUNCH = "CANTEEN_MENU_LUNCH";

    private MenuData rMenuData;
    private MenuData vMenuData;
    public boolean hasDinner = false;

    public CanteenData() {
        rMenuData = new MenuData();
    }

    public void addRMeniItem(JSONArray meni){
        rMenuData.dodajStavku(meni);
    }
    public void addVMeniItem(JSONArray meni){
        if (meni.length() > 2) {
            if (!hasDinner) {
                vMenuData = new MenuData();
                hasDinner = true;
            }
            vMenuData.dodajStavku(meni);
        }
    }

    public MenuData getrMenuData() {
        return rMenuData;
    }

    public MenuData getvMenuData() {
        return vMenuData;
    }
}
