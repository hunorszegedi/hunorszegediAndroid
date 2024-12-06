package com.practice.recipesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practice.recipesapp.databinding.ItemRecipeBinding

class RecipeAdapter(private val recipeList: ArrayList<Recipe>, private val context: Context) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.binding.recipeTitle.text = recipe.tittle
        holder.binding.recipeCategory.text = recipe.category
        // Add more UI bindings if needed
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}
