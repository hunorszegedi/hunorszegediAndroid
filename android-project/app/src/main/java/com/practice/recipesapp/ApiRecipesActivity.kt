package com.practice.recipesapp

import ApiRecipesAdapter
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
    private val recipesList = ArrayList<ApiRecipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiRecipesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchRecipesFromApi()
    }

    private fun setupRecyclerView() {
        binding.rvApiRecipes.layoutManager = LinearLayoutManager(this)
        apiRecipesAdapter = ApiRecipesAdapter(recipesList, this)
        binding.rvApiRecipes.adapter = apiRecipesAdapter
    }

    private fun fetchRecipesFromApi() {
        val apiService = ApiClient.getApiService()
        apiService.getRecipes().enqueue(object : Callback<List<ApiRecipe>> {
            override fun onResponse(call: Call<List<ApiRecipe>>, response: Response<List<ApiRecipe>>) {
                if (response.isSuccessful && response.body() != null) {
                    recipesList.clear()
                    recipesList.addAll(response.body()!!)
                    apiRecipesAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@ApiRecipesActivity, "Nem sikerült lekérni a recepteket.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ApiRecipe>>, t: Throwable) {
                Log.e("ApiError", "Hiba: ${t.message}")
                Toast.makeText(this@ApiRecipesActivity, "Hálózati hiba történt.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
