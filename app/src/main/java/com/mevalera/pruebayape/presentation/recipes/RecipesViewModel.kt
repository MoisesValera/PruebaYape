package com.mevalera.pruebayape.presentation.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mevalera.pruebayape.data.models.Recipe
import com.mevalera.pruebayape.data.source.repositories.RecipesRepository
import com.mevalera.pruebayape.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(private val userRepository: RecipesRepository) :
    ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _recipeDetails = MutableStateFlow<Recipe?>(null)
    val recipeDetails = _recipeDetails.asStateFlow()

    private val _recipes = MutableStateFlow(mutableListOf<Recipe>())
    val recipes = _recipes.asStateFlow()

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    suspend fun getRecipes() = viewModelScope.launch {
        userRepository.getRecipes().collect { result ->
            when (result) {
                is Result.Loading -> {
                    _isSearching.update { true }
                }
                is Result.Success -> {
                    _isSearching.update { false }
                    _recipes.value = result.data.toMutableList()
                }
                is Result.Error -> {
                    _isSearching.update { false }
                }
            }
        }
    }

    suspend fun getRecipeDetails(recipeId :Int) = viewModelScope.launch {
        userRepository.getRecipeDetail(recipeId).collect { result ->
            when (result) {
                is Result.Loading -> {
                    _isSearching.update { true }
                }
                is Result.Success -> {
                    _isSearching.update { false }
                    _recipeDetails.value = result.data
                }
                is Result.Error -> {
                    _isSearching.update { false }
                }
            }
        }
    }
}