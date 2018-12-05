package com.example.werefrogs.cakeulator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText newName, newAmount, newUnit, newIngredient;
    Recipe newRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newName = findViewById(R.id.et_addName);
        newAmount = findViewById(R.id.et_addAmount);
        newUnit = findViewById(R.id.et_addUnit);
        newIngredient = findViewById(R.id.et_addIngredient);

        newRecipe = new Recipe();

    }
    public void buttonPressed_toLibrary(View v) {
        Intent intent = new Intent(this, RecipeLibraryActivity.class);
        startActivity(intent);
    }
    public void buttonPressed_addToLibrary(View v) {
        //Makes a toast (short popup text) whenever the "Add to Library" button is pressed
        Context context = getApplicationContext();
        CharSequence text = "Recipe added!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Recipe r1 = new Recipe("Smørrebrød");
        r1.addIngredient(new Ingredient(1, "slice", "rye bread"));
        r1.addIngredient(new Ingredient(2, "slice", "roast beef"));
        r1.addIngredient(new Ingredient(4, "slice", "cucumber"));
        RecipeList.getInstance().addRecipe(r1);

        String nameToAdd = newName.getText().toString();
        Log.d("setName", nameToAdd );
        newRecipe.setName(nameToAdd);
        Log.d("getName", newRecipe.getName());
        RecipeList.getInstance().addRecipe(newRecipe);
    }
    public void buttonPressed_addIngredient(View v) {
        int amountToAdd = Integer.parseInt(newAmount.getText().toString());
        String unitToAdd = newUnit.getText().toString();
        String ingredientToAdd = newIngredient.getText().toString();

        newRecipe.addIngredient(new Ingredient(amountToAdd, unitToAdd, ingredientToAdd));
    }
}
