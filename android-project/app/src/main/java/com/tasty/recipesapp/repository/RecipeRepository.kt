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
import java.io.BufferedReader
import java.io.InputStreamReader

class RecipeRepository(private val context: Context) {

    private val recipes: List<RecipeModel> by lazy {
        loadRecipesFromAssets()
    }

    private fun loadRecipesFromAssets(): List<RecipeModel> {
        val gson = Gson()
        val jsonString = readJsonFileFromAssets("recipes.json")
        val listRecipeType = object : TypeToken<List<RecipeDTO>>() {}.type
        val recipeDTOList: List<RecipeDTO> = gson.fromJson(jsonString, listRecipeType)
        return recipeDTOList.toModelList()
    }

    private fun readJsonFileFromAssets(filename: String): String {
        return context.assets.open(filename).use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                reader.readText()
            }
        }
    }

    fun getRecipes(): List<RecipeModel> {
        return recipes
    }

    fun getRecipeById(id: Int): RecipeModel? {
        return recipes.find { it.id == id }
    }

    fun getDummyRecipes(): List<RecipeModel> {
        return listOf(
            RecipeModel(
                id = 1,
                name = "Dummy Recipe 1",
                description = "This is a dummy recipe description.",
                nutrition = NutritionModel(calories = 200, carbohydrates = 30, fat = 10, protein = 5, sugar = 5, fiber = 3),
                instructions = listOf(
                    InstructionModel(id = 1, displayText = "Step 1: Dummy instruction"),
                    InstructionModel(id = 2, displayText = "Step 2: Another dummy instruction")
                ),
                ingredients = listOf(
                    ComponentModel(id = 1, ingredient = IngredientModel(id = 1, name = "Ingredient 1"), quantity = 100.0, unit = UnitModel(name = "g", abbreviation = "g"), position = 1)
                ),
                servings = 4
            )
        )
    }

}
