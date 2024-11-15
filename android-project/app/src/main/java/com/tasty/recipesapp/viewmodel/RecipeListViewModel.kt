package com.tasty.recipesapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.tasty.recipesapp.model.RecipeModel
import com.tasty.recipesapp.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RecipeRepository(application)

    private val _recipeList = MutableLiveData<List<RecipeModel>>()
    val recipeList: LiveData<List<RecipeModel>> get() = _recipeList

    fun fetchRecipeData() {
        viewModelScope.launch {
            val recipes = withContext(Dispatchers.IO) {
                repository.getRecipes()
            }
            _recipeList.value = recipes
        }
    }
}
