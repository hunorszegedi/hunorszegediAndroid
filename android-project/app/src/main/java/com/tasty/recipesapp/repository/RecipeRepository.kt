package com.tasty.recipesapp.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.dto.RecipeDTO
import com.tasty.recipesapp.mapper.toModelList
import com.tasty.recipesapp.model.ComponentModel
import com.tasty.recipesapp.model.IngredientModel
import com.tasty.recipesapp.model.InstructionModel
import com.tasty.recipesapp.model.NutritionModel
import com.tasty.recipesapp.model.RecipeModel
import com.tasty.recipesapp.model.UnitModel
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class RecipeRepository(private val context: Context) {

    // Function to get all recipes
    fun getAllRecipes(): List<RecipeModel> {
        return readAllRecipes().toModelList()
    }

    private fun readAllRecipes(): List<RecipeDTO> {
        val gson = Gson()
        val recipeList: List<RecipeDTO>
        val assetManager = context.assets
        try {
            val inputStream = assetManager.open("recipes.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val jsonString = String(buffer, Charsets.UTF_8)

            val jsonObject = JSONObject(jsonString)
            val recipesArray = jsonObject.getJSONArray("recipes")
            val type = object : TypeToken<List<RecipeDTO>>() {}.type

             recipeList = gson.fromJson(recipesArray.toString(), type)
        } catch (e: IOException) {
            e.printStackTrace()
            return emptyList()
        }
        return recipeList
    }

    fun getDummyRecipes(): List<RecipeModel> {
        return listOf(
            RecipeModel(
                id = 1,
                name = "Dummy Recipe",
                description = "This is a dummy recipe.",
                nutrition = NutritionModel(
                    calories = 100,
                    carbohydrates = 20,
                    fat = 5,
                    protein = 10,
                    sugar = 5,
                    fiber = 2
                ),
                instructions = listOf(
                    InstructionModel(id = 1, displayText = "Step 1: Do something."),
                    InstructionModel(id = 2, displayText = "Step 2: Do something else.")
                ),
                ingredients = listOf(
                    ComponentModel(
                        id = 1,
                        ingredient = IngredientModel(id = 1, name = "Ingredient 1"),
                        quantity = 100.0,
                        unit = UnitModel(name = "grams", abbreviation = "g"),
                        position = 1
                    )
                ),
                servings = 4
            )
        )
    }

}
