package com.tasty.recipesapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tasty.recipesapp.viewmodel.RecipeListViewModel

class RecipesFragment : Fragment() {

    private val recipeViewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeViewModel.loadRecipeData()

        recipeViewModel.recipeModels.observe(viewLifecycleOwner) { recipes ->
            for (recipe in recipes) {
                Log.d("Recipes", recipe.toString())
            }
        }
    }
}
