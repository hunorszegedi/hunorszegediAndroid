package com.tasty.recipesapp.dto

data class UnitDTO(
    val unitID: Int,
    val name: String,
    val abbreviation: String,
    val displayPlural: String,
    val displaySingular: String,
    val system: String
)
