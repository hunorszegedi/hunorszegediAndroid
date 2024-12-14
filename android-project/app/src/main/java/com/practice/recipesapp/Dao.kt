package com.practice.recipesapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {
    @Query("SELECT * FROM recipe")
    fun getAll(): List<Recipe?>?

    @Insert
    fun insertRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipe WHERE uid = :id")
    fun getRecipeById(id: Int): Recipe?

    @Query("SELECT * FROM recipe WHERE category = :category")
    fun getRecipesByCategory(category: String): List<Recipe?>?

    @Delete
    fun deleteRecipe(recipe: Recipe)

    @Update
    fun updateRecipe(recipe: Recipe)
}