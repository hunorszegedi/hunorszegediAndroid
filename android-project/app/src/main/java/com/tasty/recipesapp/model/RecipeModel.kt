package com.tasty.recipesapp.model

data class RecipeModel(
    val id: Int,
    val name: String,
    val description: String,
    val nutrition: NutritionModel,
    val instructions: List<InstructionModel>,
    val ingredients: List<ComponentModel>,
    val servings: Int
)
