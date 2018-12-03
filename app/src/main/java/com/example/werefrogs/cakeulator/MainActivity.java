package com.example.werefrogs.cakeulator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void buttonPressed_toLibrary(View v) {
        Intent intent = new Intent(this, RecipeLibraryActivity.class);
        startActivity(intent);
    }
    public void buttonPressed_addToLibrary(View v) {
        Recipe r1 = new Recipe("Smørrebrd");
        r1.addIngredient(new Ingredient(1, "slice", "rye bread"));
        r1.addIngredient(new Ingredient(2, "slice", "roast beef"));
        r1.addIngredient(new Ingredient(4, "slice", "cucumber"));
        RecipeList.getInstance().addRecipe(r1);
    }
}
