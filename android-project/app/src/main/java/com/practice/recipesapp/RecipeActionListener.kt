package com.practice.recipesapp

interface RecipeActionListener {
    fun onDeleteClicked(recipe: Recipe)
    fun onEditClicked(recipe: Recipe)
    fun onItemClicked(recipe: Recipe)
}
