package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        // get the texviews and put the data in them.
        TextView description = findViewById(R.id.description_tv);
        if (sandwich.getDescription().isEmpty()){
            // if their is no data  make the label and textview disappear
            description.setVisibility(View.GONE);
            findViewById(R.id.description_label).setVisibility(View.GONE);
        }else{
            // if their is data put it into the textview using setText
            description.setText(sandwich.getDescription());

        }

        TextView alsoKnownAs = findViewById(R.id.also_known_tv);
        if(sandwich.getAlsoKnownAs().isEmpty()){
            alsoKnownAs.setVisibility(View.GONE);
            findViewById(R.id.alsoKnownAs_label).setVisibility(View.GONE);
        }else{
            alsoKnownAs.setText(listtoString(sandwich.getAlsoKnownAs()));

        }

        TextView ingredients = findViewById(R.id.ingredients_tv);
        if(sandwich.getIngredients().isEmpty()){
            alsoKnownAs.setVisibility(View.GONE);
            findViewById(R.id.ingredients_label).setVisibility(View.GONE);
        }else{
            ingredients.setText(listtoString(sandwich.getIngredients()));

        }

        TextView placeOfOrigin = findViewById(R.id.origin_tv);
        if(sandwich.getPlaceOfOrigin().isEmpty()){
            placeOfOrigin.setVisibility(View.GONE);
            findViewById(R.id.placeOfOrigin_label).setVisibility(View.GONE);
        }else{
            placeOfOrigin.setText(sandwich.getPlaceOfOrigin());

        }
    }
    private String listtoString (List<String> list){
        // function that turns list into a string using StringBuilder
        StringBuilder builder = new StringBuilder();
        String answers;
        /* we don't need to worry about the list being empty because we
        make the textView disappear in the populateUI function.
        No empty list is passed into this function
         */
        for(int i =0; i< list.size();i++){
            String value = list.get(i);
            builder.append(value);
            if(i< list.size()-1){
                builder.append(", ");
            }
        }
        answers = builder.toString();

        return answers;
    }
}
