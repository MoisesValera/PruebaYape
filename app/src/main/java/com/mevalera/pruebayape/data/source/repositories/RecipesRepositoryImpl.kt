package com.mevalera.pruebayape.data.source.repositories

import com.mevalera.pruebayape.data.models.Recipe
import com.mevalera.pruebayape.data.source.local.TestAppDatabase
import com.mevalera.pruebayape.data.source.remote.ApiService
import com.mevalera.pruebayape.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val db: TestAppDatabase
) : RecipesRepository {
    override suspend fun getRecipes(): Flow<Result<List<Recipe>>> =
        flow {
            emit(Result.Loading)
            try {
                db.testAppDao().getAllRecipes().collect { recipesList ->
                    if (recipesList.isEmpty()) {
                        val response = apiService.getRecipes(15)
                        db.testAppDao().addRecipes(response.recipes)
                        emit(Result.Success(response.recipes))
                    } else {
                        emit(Result.Success(recipesList))
                    }
                }
            } catch (e: Exception) {
                emit(Result.Loading)
                emit(Result.Error(e))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getRecipeDetail(recipeId: Int): Flow<Result<Recipe>> =
        flow {
            emit(Result.Loading)
            try {
                db.testAppDao().getRecipeDetail(recipeId).collect { recipe ->
                    recipe?.let {
                        emit(Result.Success(recipe))
                    } ?: run {
                        val response = apiService.getRecipeDetails(15)
                        response?.let {
                            db.testAppDao().addRecipe(it)
                            emit(Result.Success(it))
                        }
                    }

                }
            } catch (e: Exception) {
                emit(Result.Loading)
                emit(Result.Error(e))
            }
        }.flowOn(Dispatchers.IO)

}