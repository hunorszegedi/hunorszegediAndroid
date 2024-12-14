package com.practice.recipesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practice.recipesapp.databinding.ItemRecipeBinding

class MyRecipesAdapter(
    private val recipes: ArrayList<Recipe>,
    private val context: Context,
    private val actionListener: RecipeActionListener
) : RecyclerView.Adapter<MyRecipesAdapter.MyRecipesViewHolder>() {

    inner class MyRecipesViewHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecipesViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyRecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyRecipesViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.binding.recipeTitle.text = recipe.tittle
        holder.binding.recipeCategory.text = recipe.category

        // Kép megjelenítése Glide segítségével
        Glide.with(context).load(recipe.img).into(holder.binding.recipeImage)

        // Törlés gomb eseménykezelő
        holder.binding.btnDelete.setOnClickListener {
            actionListener.onDeleteClicked(recipe)
        }

        // Módosítás gomb eseménykezelő
        holder.binding.btnEdit.setOnClickListener {
            actionListener.onEditClicked(recipe)
        }

        // Kattintás a recept elemre
        holder.itemView.setOnClickListener {
            actionListener.onItemClicked(recipe)
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}
