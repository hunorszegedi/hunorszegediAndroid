package com.practice.recipesapp

import ApiRecipe
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.recipesapp.databinding.ActivityApiRecipesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRecipesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApiRecipesBinding
    private lateinit var apiRecipesAdapter: ApiRecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiRecipesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation() // Navigációs sáv inicializálása
        setupRecyclerView()
        fetchRecipesFromApi()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.selectedItemId = R.id.navigation_api_recipes
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.navigation_recipes -> {
                    startActivity(Intent(this, RecipesActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.navigation_api_recipes -> {
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
    }

    private fun setupRecyclerView() {
        binding.rvApiRecipes.layoutManager = LinearLayoutManager(this)
        apiRecipesAdapter = ApiRecipesAdapter(arrayListOf())
        binding.rvApiRecipes.adapter = apiRecipesAdapter
    }

    private fun fetchRecipesFromApi() {
        val apiService = ApiClient.getApiService()
        apiService.getRecipes().enqueue(object : Callback<List<ApiRecipe>> {
            override fun onResponse(call: Call<List<ApiRecipe>>, response: Response<List<ApiRecipe>>) {
                if (response.isSuccessful && response.body() != null) {
                    apiRecipesAdapter.updateRecipes(response.body()!!)
                } else {
                    Toast.makeText(this@ApiRecipesActivity, "Hiba történt az adatok betöltésekor.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ApiRecipe>>, t: Throwable) {
                Log.e("ApiError", t.message.toString())
                Toast.makeText(this@ApiRecipesActivity, "Hálózati hiba történt.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
