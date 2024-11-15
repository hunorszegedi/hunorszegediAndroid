package com.tasty.recipesapp.mapper

import com.tasty.recipesapp.dto.*
import com.tasty.recipesapp.model.*

fun InstructionDTO.toModel(): InstructionModel {
    return InstructionModel(
        id = this.instructionID,
        displayText = this.displayText
    )
}

fun List<InstructionDTO>.toModelList(): List<InstructionModel> {
    return this.map { it.toModel() }
}

fun NutritionDTO.toModel(): NutritionModel {
    return NutritionModel(
        calories = this.calories,
        carbohydrates = this.carbohydrates,
        fat = this.fat,
        protein = this.protein,
        sugar = this.sugar,
        fiber = this.fiber
    )
}

fun UnitDTO.toModel(): UnitModel {
    return UnitModel(
        name = this.name,
        abbreviation = this.abbreviation
    )
}

fun MeasurementDTO.toModel(): MeasurementModel {
    return MeasurementModel(
        quantity = this.quantity.toDoubleOrNull() ?: 0.0,
        unit = this.unit.toModel()
    )
}

fun IngredientDTO.toModel(): IngredientModel {
    return IngredientModel(
        id = this.ingredientID,
        name = this.name
    )
}

fun ComponentDTO.toModel(): ComponentModel {
    val measurement = this.measurements.firstOrNull()?.toModel()
    return ComponentModel(
        id = this.componentID,
        ingredient = this.ingredient.toModel(),
        quantity = measurement?.quantity ?: 0.0,
        unit = measurement?.unit ?: UnitModel("", ""),
        position = this.position
    )
}

fun RecipeDTO.toModel(): RecipeModel {
    return RecipeModel(
        id = this.recipeID,
        name = this.name,
        description = this.description,
        nutrition = this.nutrition.toModel(),
        instructions = this.instructions.toModelList(),
        components = this.components.map { it.toModel() },
        servings = this.servings
    )
}

fun List<RecipeDTO>.toModelList(): List<RecipeModel> {
    return this.map { it.toModel() }
}
