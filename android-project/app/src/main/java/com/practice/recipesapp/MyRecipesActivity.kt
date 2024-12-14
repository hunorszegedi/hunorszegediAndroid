package com.practice.recipesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.recipesapp.databinding.ActivityMyRecipesBinding

class MyRecipesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyRecipesBinding
    private lateinit var recipeList: ArrayList<Recipe>
    private lateinit var myRecipesAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyRecipesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        setupRecyclerView() // Frissíti az adatokat, amikor visszatérsz az Activity-hez
    }

    private fun setupRecyclerView() {
        val db = DatabaseProvider.getDatabase(this)
        val dao = db.getDao()
        val userAddedRecipes = dao.getRecipesByCategory("User Added") ?: emptyList()

        recipeList = ArrayList(userAddedRecipes.filterNotNull())
        binding.rvMyRecipes.layoutManager = LinearLayoutManager(this)
        myRecipesAdapter = SearchAdapter(recipeList, this)
        binding.rvMyRecipes.adapter = myRecipesAdapter
    }
}
