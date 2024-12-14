package com.practice.recipesapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.practice.recipesapp.databinding.ActivityRecipesBinding

class RecipesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipesBinding
    private lateinit var rvAdapter: SearchAdapter
    private lateinit var dataList: ArrayList<Recipe>
    private lateinit var recipes: List<Recipe?>

    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.selectedItemId = R.id.navigation_recipes
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.navigation_recipes -> {
                    true
                }
                R.id.navigation_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }

        // Initialize the database and fetch all recipes
        val db = Room.databaseBuilder(this@RecipesActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .createFromAsset("recipe.db")
            .build()

        val daoObject = db.getDao()
        recipes = daoObject.getAll()!!

        // Setup RecyclerView to display all recipes
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        dataList = ArrayList()
        binding.rvRecipes.layoutManager = LinearLayoutManager(this)

        // Add all recipes to the data list
        for (i in recipes.indices) {
            if (recipes[i] != null) {
                dataList.add(recipes[i]!!)
            }
        }

        // Set up the adapter
        rvAdapter = SearchAdapter(dataList, this)
        binding.rvRecipes.adapter = rvAdapter
    }
}
