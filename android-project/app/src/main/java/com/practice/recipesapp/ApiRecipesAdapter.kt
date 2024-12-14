package com.practice.recipesapp

import ApiRecipe
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practice.recipesapp.databinding.ItemApiRecipeBinding

class ApiRecipesAdapter(private var recipes: ArrayList<ApiRecipe>) :
    RecyclerView.Adapter<ApiRecipesAdapter.ApiRecipeViewHolder>() {

    inner class ApiRecipeViewHolder(val binding: ItemApiRecipeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiRecipeViewHolder {
        val binding = ItemApiRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ApiRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApiRecipeViewHolder, position: Int) {
        val recipe = recipes[position]

        holder.binding.recipeTitle.text = recipe.name
        holder.binding.recipeDescription.text = recipe.description

        Glide.with(holder.itemView.context)
            .load(recipe.thumbnailUrl)
            .into(holder.binding.recipeImage)

        holder.itemView.setOnClickListener {
            // Itt kezelheted, ha például egy recept részleteire akarsz navigálni
        }
    }

    override fun getItemCount(): Int = recipes.size

    fun updateRecipes(newRecipes: List<ApiRecipe>) {
        recipes.clear()
        recipes.addAll(newRecipes)
        notifyDataSetChanged()
    }
}
