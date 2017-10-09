package com.example.lasic.xica;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by lasic on 12.06.2017..
 */

public class Stavka {
    public String ime;
    public ArrayList<String> sastav;
    private String cijena;

    public Stavka() {
        sastav = new ArrayList<>();
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void addSastav(String naziv){
        sastav.add(naziv);
    }

    public String getCijena() {
        return cijena;
    }

    public void setCijena(String cijena) {
        this.cijena = cijena;
    }

    public ArrayList<String> getSastav() {
        return sastav;
    }
    public String getOutputSastav(){
        return TextUtils.join("\n", sastav);
    }
}
