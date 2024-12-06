package com.practice.recipesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.recipesapp.databinding.ActivityMyRecipesBinding

class MyRecipesActivity : AppCompatActivity() {
//    adapterrel hagytuk abba!!
//    ne felejtsd el video url-t is megjeleniteni mindenhol!
    private lateinit var binding: ActivityMyRecipesBinding
//    private lateinit var myRecipesAdapter: MyRecipesAdapter
    private lateinit var recipeList: ArrayList<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyRecipesBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setupRecyclerView()
    }

//    private fun setupRecyclerView() {
//        recipeList = ArrayList() // Hozd létre a lista adatokat Room Database segítségével
//
//        binding.rvMyRecipes.layoutManager = LinearLayoutManager(this)
//        myRecipesAdapter = MyRecipesAdapter(recipeList)
//        binding.rvMyRecipes.adapter = myRecipesAdapter
//    }
}

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//
//befejezni: videUrl es megjelenites
//        recept hozzaadas es megjelenites, alapbol popular legyen
//        jelenjen meg az osszesnel es a sajat profil oldalon
//        toroljunk is!