package com.example.werefrogs.cakeulator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText newName, newAmount, newUnit, newItem, newServing;
    Recipe newRecipe;
    Ingredient newIngredient;
    ListView ingredientList;
    ArrayAdapter<Ingredient> adapterIngredient;
    ArrayList<Ingredient> listIngredients = new ArrayList<Ingredient>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newName = findViewById(R.id.et_addName);
        newAmount = findViewById(R.id.et_addAmount);
        newUnit = findViewById(R.id.et_addUnit);
        newItem = findViewById(R.id.et_addItem);
        ingredientList = findViewById(R.id.lv_ingredients);
        newServing = findViewById(R.id.et_amount);

        newRecipe = new Recipe();
    }

    public void buttonPressed_toLibrary(View v) { //Switches activities (Main to Recipe Library)
        Intent intent = new Intent(this, RecipeLibraryActivity.class);
        startActivity(intent);
    }

    public void buttonPressed_addToLibrary(View v) { //Adds given recipe to the library
        //Makes a toast (short popup text) whenever the "Add to Library" button is pressed
        Context context = getApplicationContext();
        CharSequence text = "Recipe added!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //Test recipe
        Recipe r1 = new Recipe("Smørrebrød");
        r1.addIngredient(new Ingredient(1, "slice", "rye bread"));
        r1.addIngredient(new Ingredient(2, "slice", "roast beef"));
        r1.addIngredient(new Ingredient(4, "slice", "cucumber"));
        RecipeList.getInstance().addRecipe(r1);

        //User inputted recipe name
        String nameToAdd = newName.getText().toString();
        Log.d("setName", nameToAdd);
        newRecipe.setName(nameToAdd);
        Log.d("getName", newRecipe.getName());
        RecipeList.getInstance().addRecipe(newRecipe);

        //User inputted servings
        int servingToAdd = Integer.parseInt(newServing.getText().toString());
        newRecipe.setServings(servingToAdd);

        recipeReset();
    }

    public void buttonPressed_addIngredient(View v) { //Adds ingredient to the List View
        double amountToAdd = Double.parseDouble(newAmount.getText().toString());
        String unitToAdd = newUnit.getText().toString();
        String itemToAdd = newItem.getText().toString();

        newIngredient = new Ingredient(amountToAdd, unitToAdd, itemToAdd);
        newRecipe.addIngredient(newIngredient);
        listIngredients.add(newIngredient);
        adapterIngredient = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_list_item_1, listIngredients);
        ingredientList.setAdapter(adapterIngredient);
        ingredientReset();
    }

    public void ingredientReset() { //Resets the ingredient input fields to blank
        newAmount.setText(null);
        newUnit.setText(null);
        newItem.setText(null);
        newIngredient = null;
    }

    public void recipeReset() { //Resets the recipe input fields and the list to blank
        listIngredients.clear();
        newName.setText(null);
        newRecipe = null;
        newServing.setText(null);

        newRecipe = new Recipe();
        ingredientList.setAdapter(adapterIngredient);
    }
}
