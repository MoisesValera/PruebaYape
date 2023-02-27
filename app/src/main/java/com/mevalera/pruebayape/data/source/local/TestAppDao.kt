package com.mevalera.pruebayape.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mevalera.pruebayape.data.models.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface TestAppDao {
    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipes(recipes: List<Recipe>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipe(recipes: Recipe)

    @Query("SELECT * FROM recipes WHERE id IN (:recipeId)")
    fun getRecipeDetail(recipeId: Int): Flow<Recipe?>
}