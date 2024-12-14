package com.practice.recipesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.recipesapp.databinding.ActivityMyRecipesBinding

class MyRecipesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyRecipesBinding
    private lateinit var recipeList: ArrayList<Recipe>
    private lateinit var myRecipesAdapter: MyRecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyRecipesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        setupRecyclerView() // Frissíti az adatokat, amikor visszatér az Activity
    }

    private fun setupRecyclerView() {
        val db = DatabaseProvider.getDatabase(this)
        val dao = db.getDao()
        val userAddedRecipes = dao.getRecipesByCategory("User Added") ?: emptyList()

        recipeList = ArrayList(userAddedRecipes.filterNotNull())
        binding.rvMyRecipes.layoutManager = LinearLayoutManager(this)
        myRecipesAdapter = MyRecipesAdapter(recipeList, this, object : RecipeActionListener {
            override fun onDeleteClicked(recipe: Recipe) {
                dao.deleteRecipe(recipe) // Törli a receptet az adatbázisból
                setupRecyclerView() // Frissíti a listát
            }

            override fun onEditClicked(recipe: Recipe) {
                Log.d("MyRecipesActivity", "Navigating to EditRecipeActivity with RECIPE_ID: ${recipe.uid}")
                val intent = Intent(this@MyRecipesActivity, EditRecipeActivity::class.java)
                intent.putExtra("RECIPE_ID", recipe.uid)
                startActivity(intent)
            }

            override fun onItemClicked(recipe: Recipe) {
                val intent = Intent(this@MyRecipesActivity, RecipeActivity::class.java)
                intent.putExtra("img", recipe.img)
                intent.putExtra("tittle", recipe.tittle)
                intent.putExtra("des", recipe.des)
                intent.putExtra("ing", recipe.ing)
                startActivity(intent)
            }
        })
        binding.rvMyRecipes.adapter = myRecipesAdapter
    }
}
