package com.practice.recipesapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.practice.recipesapp.databinding.ActivityEditRecipeBinding

class EditRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRecipeBinding
    private var recipeId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipeId = intent.getIntExtra("RECIPE_ID", -1)
        Log.d("EditRecipeActivity", "Received RECIPE_ID: $recipeId")

        if (recipeId != -1) {
            loadRecipeDetails(recipeId)
        } else {
            Log.e("EditRecipeActivity", "Invalid RECIPE_ID received")
            Toast.makeText(this, "Hiba a recept betöltésekor!", Toast.LENGTH_SHORT).show()
            finish()
        }

        // Mentés gomb eseménykezelő
        binding.saveButton.setOnClickListener {
            val title = binding.recipeTitleInput.text.toString()
            val description = binding.recipeDescriptionInput.text.toString()
            val ingredients = binding.recipeIngredientsInput.text.toString()
            val imageUrl = binding.recipeImageUrlInput.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty() && ingredients.isNotEmpty() && imageUrl.isNotEmpty()) {
                updateRecipeInDatabase(recipeId, title, description, ingredients, imageUrl)
            } else {
                Toast.makeText(this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadRecipeDetails(id: Int) {
        val db = DatabaseProvider.getDatabase(this)
        val dao = db.getDao()
        val recipe = dao.getRecipeById(id)

        if (recipe != null) {
            binding.recipeTitleInput.setText(recipe.tittle)
            binding.recipeDescriptionInput.setText(recipe.des)
            binding.recipeIngredientsInput.setText(recipe.ing)
            binding.recipeImageUrlInput.setText(recipe.img)
        } else {
            Toast.makeText(this, "A recept nem található!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun updateRecipeInDatabase(
        id: Int,
        title: String,
        description: String,
        ingredients: String,
        imageUrl: String
    ) {
        val db = DatabaseProvider.getDatabase(this)
        val dao = db.getDao()

        val updatedRecipe = Recipe(
            img = imageUrl,
            tittle = title,
            des = description,
            ing = ingredients,
            category = "User Added"
        )
        updatedRecipe.uid = id // Az azonosító megőrzése frissítéshez

        dao.updateRecipe(updatedRecipe)

        Toast.makeText(this, "Recept frissítve!", Toast.LENGTH_SHORT).show()
        finish()
    }
}
