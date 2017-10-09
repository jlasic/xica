package com.example.lasic.xica;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lasic on 12.06.2017..
 */

public class Meni {
    private ArrayList<Stavka> stavka;

    public Meni() {
        stavka = new ArrayList<>();
    }
    public void dodajStavku(JSONArray meni){
        try {
            Stavka tmpStavka = new Stavka();
            int i;

            boolean containsJelo;

            containsJelo = meni.getString(0).contains("JELO");
            if (containsJelo == false) {
                tmpStavka.setIme(meni.getString(1));

                for (i = 2; i < meni.length() - 1; i++)
                    tmpStavka.addSastav(meni.getString(i));

                tmpStavka.setCijena(meni.getString(i));
            }
            else if(containsJelo){
                tmpStavka.setIme("JELO");
                String imeCijena = meni.getString(1);
                String[] temp = imeCijena.split(" ");
                String cijena = temp[temp.length - 1];
                tmpStavka.setCijena(cijena);
                tmpStavka.addSastav(imeCijena.substring(0, imeCijena.length() - cijena.length()));
            }
            stavka.add(tmpStavka);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Stavka> getStavka() {
        return stavka;
    }
}
