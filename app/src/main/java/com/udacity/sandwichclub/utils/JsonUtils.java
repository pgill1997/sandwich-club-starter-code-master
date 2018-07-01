package com.udacity.sandwichclub.utils;


import com.udacity.sandwichclub.model.Sandwich;

import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import android.util.Log;
import java.util.List;
import java.util.ArrayList;





public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try{
            JSONObject sandiwchFeed = new JSONObject(json);
            JSONObject name = sandiwchFeed.getJSONObject("name");

            String mainName = name.getString("mainName");

            String placeOfOrigin = sandiwchFeed.optString("placeOfOrigin");

            String description  = sandiwchFeed.optString("description");
            String image = sandiwchFeed.optString("image");
            List<String> alsoKnownAs = new ArrayList<>();
            JSONArray akaArray = name.getJSONArray("alsoKnownAs");

            for(int i = 0; i<akaArray.length();i++){
                alsoKnownAs.add(akaArray.getString(i));
            }

            JSONArray ingredientArray = sandiwchFeed.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<>();

            for(int i = 0; i<ingredientArray.length();i++){
                ingredients.add(ingredientArray.optString(i));
            }

            return new Sandwich( mainName,  alsoKnownAs,  placeOfOrigin,  description,  image,  ingredients);

        }catch(JSONException exc){
            return null;

        }
    }
}