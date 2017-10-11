package com.example.lasic.xica.data;

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

    private ArrayList<MealData> mealData;

    public MenuData() {
        mealData = new ArrayList<>();
    }
    public void dodajStavku(JSONArray meni){
        try {
            MealData tmpMealData = new MealData();
            int i;

            boolean containsJelo;

            containsJelo = meni.getString(0).contains("JELO");
            if (containsJelo == false) {
                tmpMealData.setIme(meni.getString(1));

                for (i = 2; i < meni.length() - 1; i++)
                    tmpMealData.addSastav(meni.getString(i));

                tmpMealData.setCijena(meni.getString(i));
            }
            else if(containsJelo){
                tmpMealData.setIme("JELO");
                String imeCijena = meni.getString(1);
                String[] temp = imeCijena.split(" ");
                String cijena = temp[temp.length - 1];
                tmpMealData.setCijena(cijena);
                tmpMealData.addSastav(imeCijena.substring(0, imeCijena.length() - cijena.length()));
            }
            mealData.add(tmpMealData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<MealData> getMealData() {
        return mealData;
    }
}
