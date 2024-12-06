package com.practice.recipesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.practice.recipesapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var dataList: ArrayList<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.bottomNavigation.selectedItemId = R.id.navigation_home
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    true
                }
                R.id.navigation_recipes -> {
                    startActivity(Intent(this, RecipesActivity::class.java))
                    true
                }
                R.id.navigation_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }

                else -> false
            }
        }

        binding.search.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        binding.salad.setOnClickListener {
            val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
            intent.putExtra("TITTLE", "Salad")
            intent.putExtra("CATEGORY", "Salad")
            startActivity(intent)
        }
        binding.mainDish.setOnClickListener {
            val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
            intent.putExtra("TITTLE", "Main Dish")
            intent.putExtra("CATEGORY", "Dish")
            startActivity(intent)
        }
        binding.drinks.setOnClickListener {
            val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
            intent.putExtra("TITTLE", "Drinks")
            intent.putExtra("CATEGORY", "Drinks")
            startActivity(intent)
        }
        binding.desserts.setOnClickListener {
            val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
            intent.putExtra("TITTLE", "Desserts")
            intent.putExtra("CATEGORY", "Desserts")
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        dataList = ArrayList()
        binding.rvPopular.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val db = Room.databaseBuilder(this@HomeActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()

        val daoObject = db.getDao()
        val recipes = daoObject.getAll()

        recipes?.forEach { recipe ->
            if (recipe != null) {
                if (recipe.category.contains("Popular")) {
                    dataList.add(recipe)
                }
            }
        }

        rvAdapter = PopularAdapter(dataList, this)
        binding.rvPopular.adapter = rvAdapter
    }
}