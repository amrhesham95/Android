package com.example.retrofitcountries;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Country    {
    public static final String BASE_URL="https://www.androidbegin.com";
    @SerializedName("worldpopulation")
    @Expose
    private List<Worldpopulation> worldpopulation = null;

    public List<Worldpopulation> getWorldpopulation() {
        return worldpopulation;
    }

    public void setWorldpopulation(List<Worldpopulation> worldpopulation) {
        this.worldpopulation = worldpopulation;
    }


}