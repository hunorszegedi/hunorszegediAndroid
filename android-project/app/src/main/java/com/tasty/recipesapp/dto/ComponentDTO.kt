package com.tasty.recipesapp.dto

data class ComponentDTO(
    val componentID: Int,
    val ingredient: IngredientDTO,
    val measurements: List<MeasurementDTO>,
    val position: Int
)
