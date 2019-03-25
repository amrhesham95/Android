package com.example.volleydemosingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

/**
 * Created by iti on 21/02/2017.
 */

public class MyJSONParser {
    private static final String COUNTRY_NAME = "country";
    private static final String RANK = "rank";
    private static final String POPULATION = "population";
    private static final String FLAG = "flag";
    public static Vector<CountryPopulation> getCountryVector(JSONObject jsonObj){
        Vector<CountryPopulation> countryPopulations = new Vector<CountryPopulation>();

        try {
            JSONArray jsonArray = jsonObj.getJSONArray("worldpopulation");
            JSONObject temp;
            CountryPopulation country = new CountryPopulation();
            for (int i=0 ; i<jsonArray.length() ; i++){
                temp = jsonArray.getJSONObject(i);
                country = new CountryPopulation();
                country.setCountry(temp.getString(COUNTRY_NAME));
                country.setPopulation(temp.getString(POPULATION));
                country.setRank(temp.getString(RANK));
                country.setUrl(temp.getString(FLAG).replace("http://","https://"));
                countryPopulations.add(country);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return countryPopulations;
    }
}
