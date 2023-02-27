package com.mevalera.pruebayape.data.source.repositories

import com.mevalera.pruebayape.data.models.Recipe
import com.mevalera.pruebayape.util.Result
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    suspend fun getRecipes(): Flow<Result<List<Recipe>>>
    suspend fun getRecipeDetail(recipeId: Int): Flow<Result<Recipe>>
}