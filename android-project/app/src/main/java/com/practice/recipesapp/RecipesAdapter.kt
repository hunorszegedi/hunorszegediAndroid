package com.practice.recipesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practice.recipesapp.databinding.ItemRecipeBinding

class RecipesAdapter(
    private val recipes: ArrayList<Recipe>,
    private val context: Context
) : RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    inner class RecipeViewHolder(private val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.recipeTitle.text = recipe.tittle

//            binding.recipeDescription.text = recipe.des
            // Kép beállítása
            // (Használhatsz Glide-t vagy Picasso-t az `img` URL betöltésére)
        }
    }
}
