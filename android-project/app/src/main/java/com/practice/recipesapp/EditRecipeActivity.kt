package com.practice.recipesapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.practice.recipesapp.databinding.ActivityEditRecipeBinding

class EditRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRecipeBinding
    private var recipeId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipeId = intent.getIntExtra("recipe_id", -1)

        if (recipeId != -1) {
            loadRecipe(recipeId)
        }

        binding.saveButton.setOnClickListener {
            val title = binding.recipeTitleInput.text.toString()
            val description = binding.recipeDescriptionInput.text.toString()
            val ingredients = binding.recipeIngredientsInput.text.toString()
            val imageUrl = binding.recipeImageUrlInput.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty() && ingredients.isNotEmpty() && imageUrl.isNotEmpty()) {
                updateRecipeInDatabase(title, description, ingredients, imageUrl)
            } else {
                Toast.makeText(this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadRecipe(id: Int) {
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .build()

        val dao = db.getDao()
        val recipe = dao.getRecipeById(id)

        recipe?.let {
            binding.recipeTitleInput.setText(it.tittle)
            binding.recipeDescriptionInput.setText(it.des)
            binding.recipeIngredientsInput.setText(it.ing)
            binding.recipeImageUrlInput.setText(it.img)
        } ?: run {
            Toast.makeText(this, "Hiba: Nem található a recept!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun updateRecipeInDatabase(
        title: String,
        description: String,
        ingredients: String,
        imageUrl: String
    ) {
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .build()

        val dao = db.getDao()

        val updatedRecipe = Recipe(
            img = imageUrl,
            tittle = title,
            des = description,
            ing = ingredients,
            category = "User Added"
        )
        updatedRecipe.uid = recipeId

        Log.d("DatabaseDebug", "Update hívás előtt: $updatedRecipe")
        dao.updateRecipe(updatedRecipe)
        Log.d("DatabaseDebug", "Update hívás után")

        Toast.makeText(this, "Recept frissítve!", Toast.LENGTH_SHORT).show()
        finish() // Visszatérés az előző képernyőre
    }
}
