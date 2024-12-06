package com.practice.recipesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
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

    private fun setupRecyclerView() {
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        val dao = db.getDao()
        val userAddedRecipes = dao.getAll()?.filter { it?.category == "User Added" } ?: emptyList()

        recipeList = ArrayList(userAddedRecipes.filterNotNull())
        binding.rvMyRecipes.layoutManager = LinearLayoutManager(this)
        myRecipesAdapter = SearchAdapter(recipeList, this)
        binding.rvMyRecipes.adapter = myRecipesAdapter
    }
}
