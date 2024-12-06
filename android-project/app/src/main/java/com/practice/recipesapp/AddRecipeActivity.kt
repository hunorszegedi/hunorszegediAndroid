package com.practice.recipesapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.practice.recipesapp.databinding.ActivityAddRecipeBinding

class AddRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAddRecipe.setOnClickListener {
            binding.fabAddRecipe.visibility = View.GONE

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NewRecipeFragment())
                .addToBackStack(null)
                .commit()
        }

    }
}
