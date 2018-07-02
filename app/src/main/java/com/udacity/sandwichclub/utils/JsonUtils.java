package com.udacity.sandwichclub.utils;


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
            JSONObject JsonResponse = new JSONObject(json);
            JSONObject JsonObj = JsonResponse.getJSONObject("name");

            String mainName = JsonObj.getString("mainName");

            String placeOfOrigin = JsonResponse.optString("placeOfOrigin");

            String description  = JsonResponse.optString("description");
            String image = JsonResponse.optString("image");
            List<String> alsoKnownAs = new ArrayList<>();
            JSONArray akaArray = JsonObj.getJSONArray("alsoKnownAs");

            for(int i = 0; i<akaArray.length();i++){
                alsoKnownAs.add(akaArray.getString(i));
            }

            JSONArray ingredientArray = JsonResponse.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<>();

            for(int i = 0; i<ingredientArray.length();i++){
                ingredients.add(ingredientArray.optString(i));
            }

            return new Sandwich( mainName,  alsoKnownAs,  placeOfOrigin,  description,  image,  ingredients);

        }catch(JSONException e){
            return null;

        }
    }
}