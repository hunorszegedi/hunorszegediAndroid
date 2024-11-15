package com.tasty.recipesapp.dto

data class NutritionDTO(
    val nutritionID: Int,
    val calories: Int,
    val carbohydrates: Int,
    val fat: Int,
    val protein: Int,
    val sugar: Int,
    val fiber: Int
)
