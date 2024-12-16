package com.practice.recipesapp

import ApiRecipe
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.practice.recipesapp.databinding.ActivityApiRecipeDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApiRecipeDetailBinding
    private var recipeID: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recipe ID fogadása
        recipeID = intent.getIntExtra("RECIPE_ID", -1)

        if (recipeID != -1) {
            fetchRecipeDetail(recipeID)
        } else {
            Toast.makeText(this, "Érvénytelen recept azonosító!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun fetchRecipeDetail(id: Int) {
        val apiService = ApiClient.getApiService()
        apiService.getRecipeDetail(id).enqueue(object : Callback<ApiRecipe> {
            override fun onResponse(call: Call<ApiRecipe>, response: Response<ApiRecipe>) {
                if (response.isSuccessful && response.body() != null) {
                    val recipe = response.body()
                    displayRecipeDetails(recipe!!)
                } else {
                    Toast.makeText(this@ApiRecipeDetailActivity, "Hiba a recept betöltésekor.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiRecipe>, t: Throwable) {
                Log.e("ApiError", t.message.toString())
                Toast.makeText(this@ApiRecipeDetailActivity, "Hálózati hiba történt.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun displayRecipeDetails(recipe: ApiRecipe) {
        binding.recipeTitle.text = recipe.name
        binding.recipeDescription.text = recipe.description
        binding.recipeKeywords.text = "Kulcsszavak: ${recipe.keywords}"
        binding.recipeServings.text = "Adagok: ${recipe.numServings}"
        Glide.with(this).load(recipe.thumbnailUrl).into(binding.recipeImage)
    }
}
