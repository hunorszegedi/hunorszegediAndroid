package com.tasty.recipesapp.dto

data class MeasurementDTO(
    val measurementID: Int,
    val quantity: String,
    val unit: UnitDTO
)
