package com.example.lasic.xica.data;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by lasic on 12.06.2017..
 */

public class MenuData {
    private ArrayList<DishData> dishData;

    public MenuData() {
        dishData = new ArrayList<>();
    }
    public void dodajStavku(JSONArray meni){
        try {
            DishData tmpDishData = new DishData();
            int i;

            boolean containsJelo;

            containsJelo = meni.getString(0).contains("JELO");
            if (containsJelo == false) {
                tmpDishData.setIme(meni.getString(1));

                for (i = 2; i < meni.length() - 1; i++)
                    tmpDishData.addSastav(meni.getString(i));

                tmpDishData.setCijena(meni.getString(i));
            }
            else if(containsJelo){
                tmpDishData.setIme("JELO");
                String imeCijena = meni.getString(1);
                String[] temp = imeCijena.split(" ");
                String cijena = temp[temp.length - 1];
                tmpDishData.setCijena(cijena);
                tmpDishData.addSastav(imeCijena.substring(0, imeCijena.length() - cijena.length()));
            }
            dishData.add(tmpDishData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<DishData> getDishData() {
        return dishData;
    }
}
