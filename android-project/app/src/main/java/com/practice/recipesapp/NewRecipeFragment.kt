package com.practice.recipesapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.practice.recipesapp.databinding.FragmentNewRecipeBinding

class NewRecipeFragment : Fragment() {

    private lateinit var binding: FragmentNewRecipeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewRecipeBinding.inflate(inflater, container, false)

        // Mentés gomb eseménykezelő
        binding.saveButton.setOnClickListener {
            val title = binding.recipeTitleInput.text.toString()
            val description = binding.recipeDescriptionInput.text.toString()
            val ingredients = binding.recipeIngredientsInput.text.toString()
            val imageUrl = binding.recipeImageUrlInput.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty() && ingredients.isNotEmpty() && imageUrl.isNotEmpty()) {
                saveRecipeToDatabase(title, description, ingredients, imageUrl)
            } else {
                Toast.makeText(requireContext(), "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun saveRecipeToDatabase(title: String, description: String, ingredients: String, imageUrl: String) {
        val db = DatabaseProvider.getDatabase(requireContext())
        val dao = db.getDao()

        val newRecipe = Recipe(
            img = imageUrl,
            tittle = title,
            des = description,
            ing = ingredients,
            category = "User Added"
        )

        Log.d("DatabaseDebug", "Insert hívás előtt: $newRecipe")
        dao.insertRecipe(newRecipe)
        Log.d("DatabaseDebug", "Insert hívás után")

        Toast.makeText(requireContext(), "Recept hozzáadva!", Toast.LENGTH_SHORT).show()
        requireActivity().supportFragmentManager.popBackStack()
    }
}
