package com.tasty.recipesapp.model

data class ComponentModel(
    val id: Int,
    val ingredient: IngredientModel,
    val quantity: Double,
    val unit: UnitModel,
    val position: Int
)
