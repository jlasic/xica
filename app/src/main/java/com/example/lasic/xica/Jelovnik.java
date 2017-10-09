package com.example.lasic.xica;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lasic on 12.06.2017..
 */

public class Jelovnik {
    private Meni rMeni;
    private Meni vMeni;
    public boolean hasDinner = false;

    public Jelovnik() {
        rMeni = new Meni();
    }

    public void addRMeniItem(JSONArray meni){
        rMeni.dodajStavku(meni);
    }
    public void addVMeniItem(JSONArray meni){
        if (meni.length() > 2) {
            if (!hasDinner) {
                vMeni = new Meni();
                hasDinner = true;
            }
            vMeni.dodajStavku(meni);
        }
    }

    public Meni getrMeni() {
        return rMeni;
    }

    public Meni getvMeni() {
        return vMeni;
    }
}
