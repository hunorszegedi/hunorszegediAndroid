package com.tasty.recipesapp.dto

data class RecipeDTO(
    val recipeID: Int,
    val name: String,
    val description: String,
    val nutrition: NutritionDTO,
    val instructions: List<InstructionDTO>,
    val components: List<ComponentDTO>,
    val servings: Int
)
