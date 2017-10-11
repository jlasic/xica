package com.example.lasic.xica.helpers;

import android.support.annotation.NonNull;

import com.example.lasic.xica.data.CanteenData;
import com.example.lasic.xica.data.MealData;
import com.example.lasic.xica.data.MenuData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by lasic on 10.10.2017..
 */

public final class Utils {
    private static final String TAG = "Utils";

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

    public static String getFormattedDate(){
        return new SimpleDateFormat("dd/MM/yyyy", Locale.GERMANY).format(new Date());
    }

    public static JSONObject fixResponse(JSONObject response){
        JSONArray jsonArray;
        try {
            jsonArray = response.getJSONArray("values");
        }catch (Exception e){
            return null;
        }

        JSONObject fixedJSON = new JSONObject();
        JSONObject menuLunch = null;
        JSONObject menuDinner = null;
        JSONArray mealArrayLunch = null;
        JSONArray mealArrayDinner = null;

        if (jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {

                //get array of values
                JSONArray row;
                try {
                    row = jsonArray.getJSONArray(i);
                } catch (Exception e) {
                    continue;
                }

                //date
                try {
                    if (row.getString(1).equals("MENZA")) {
                        String date1 = row.getString(3);
                        String date2 = row.getString(4);
                        fixedJSON.put(CanteenData.NAME, row.getString(2));
                        fixedJSON.put(CanteenData.DATE_START, date1);
                        fixedJSON.put(CanteenData.DATE_END, date2);
                    }
                } catch (Exception e) {}

                //working hours
                try {
                    if (row.getString(1).equals("RUČAK")){
                        String start = row.getString(2);
                        String end = row.getString(3);
                        if (!start.isEmpty() && !end.isEmpty()){
                            menuLunch = new JSONObject();
                            menuLunch.put(MenuData.NAME, "Ručak");
                            menuLunch.put(MenuData.START_TIME, start);
                            menuLunch.put(MenuData.END_TIME, end);
                        }
                    }
                    else if (row.getString(1).equals("VEČERA")){
                        String start = row.getString(2);
                        String end = row.getString(3);
                        if (!start.isEmpty() && !end.isEmpty()){
                            menuDinner = new JSONObject();
                            menuDinner.put(MenuData.NAME, "Večera");
                            menuDinner.put(MenuData.START_TIME, start);
                            menuDinner.put(MenuData.END_TIME, end);
                        }
                    }
                }catch (Exception e){}

                String[] menuIdentifiers = {"R", "V"};
                for (String menu : menuIdentifiers) {
                    JSONObject parsedMeal = null;
                    try {
                        if (row.getString(0).equals(menu.concat("-MENI"))) {
                            parsedMeal = new JSONObject();
                            try {
                                //get meal name (MENI1, VEGETERIJANSKI...)
                                parsedMeal.put(MealData.NAME, row.get(1));

                                //sastav
                                JSONArray parsedDishes = new JSONArray();
                                for (int j = 2; j < row.length() - 1; j++){
                                    String dish = row.getString(j);
                                    if(!dish.isEmpty())
                                        parsedDishes.put(dish);
                                }

                                parsedMeal.put(MealData.DISH_ARRAY, parsedDishes);

                                //price
                                parsedMeal.put(MealData.PRICE, row.getString(7));

                            } catch (Exception e) {
                                parsedMeal = null;
                            }
                        }
                        else if (row.length() == 2 && row.getString(0).equals(menu.concat("-JELO PO IZBORU"))){
                            parsedMeal = new JSONObject();
                            try {
                                //get meal name (MENI1, VEGETERIJANSKI...)
                                parsedMeal.put(MealData.NAME, "JELO");

                                //damn response fuck you
                                String data = row.getString(1);
                                String[] temp = data.split(" ");
                                String price = temp[temp.length - 1];
                                String dish = data.substring(0, data.length() - price.length());

                                //sastav
                                JSONArray parsedDishes = new JSONArray();
                                parsedDishes.put(dish);
                                parsedMeal.put(MealData.DISH_ARRAY, parsedDishes);

                                //price
                                parsedMeal.put(MealData.PRICE, price);

                            } catch (Exception e) {
                                parsedMeal = null;
                            }
                        }
                    } catch (Exception e) {}

                    if (parsedMeal != null){
                            switch (menu){
                                case "R":
                                    if (mealArrayLunch == null)
                                        mealArrayLunch = new JSONArray();
                                    mealArrayLunch.put(parsedMeal);
                                    break;
                                case "V":
                                    if (mealArrayDinner == null)
                                        mealArrayDinner = new JSONArray();
                                    mealArrayDinner.put(parsedMeal);
                                    break;
                            }
                    }
                }
            }
            try {
                if (menuLunch != null)
                    menuLunch.put(MenuData.MEAL_ARRAY, mealArrayLunch);

                if (menuDinner != null)
                    menuDinner.put(MenuData.MEAL_ARRAY, mealArrayDinner);

                fixedJSON.put(CanteenData.MENU_LUNCH, menuLunch);
                fixedJSON.put(CanteenData.MENU_DINNER, menuDinner);
            }
            catch (Exception e){
                return null;
            }
            return fixedJSON;
        }
        return null;
    }
}
