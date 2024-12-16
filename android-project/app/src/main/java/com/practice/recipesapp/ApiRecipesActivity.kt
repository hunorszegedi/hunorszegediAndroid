package com.practice.recipesapp

import ApiRecipe
import ApiRecipesAdapter
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

        setupBottomNavigation()
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
                R.id.navigation_api_recipes -> true
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
        val apiService = ApiClient.getApiService()
        binding.rvApiRecipes.layoutManager = LinearLayoutManager(this)
        apiRecipesAdapter = ApiRecipesAdapter(arrayListOf(), apiService) {
            fetchRecipesFromApi() // Frissíti a listát törlés után
        }
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

    private fun deleteRecipeFromApi(recipeID: Int) {
        val apiService = ApiClient.getApiService()
        val authToken = "Bearer ya29.a0ARW5m74ORTnUNgXHYlFtok8qEHArcuRSi_FXCzg0YKiN0dGOTjoxOypk6MntwrQjiLzcHic65eXP4kXeQsfzlyFA5GMjHCucfJqXHXrId7zQXbkuT7nSmyErctY8OkJ-V8NP-9i1M-GykBeaiVVNJ14llXGXoWI7czV1ROvsaCgYKAVUSARASFQHGX2MiK6PTDBmQOJf_ziOYlh39rg0175"
        apiService.deleteRecipe(authToken, recipeID).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ApiRecipesActivity, "Recept sikeresen törölve!", Toast.LENGTH_SHORT).show()
                    apiRecipesAdapter.removeRecipeById(recipeID)
                } else {
                    Log.e("DeleteError", "Response Code: ${response.code()}, Message: ${response.message()}")
                    Toast.makeText(this@ApiRecipesActivity, "Hiba történt a törléskor.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("DeleteFailure", t.message.toString())
                Toast.makeText(this@ApiRecipesActivity, "Hálózati hiba történt a törlés során.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
