package com.mevalera.pruebayape.data.source.remote

import com.mevalera.pruebayape.BuildConfig
import com.mevalera.pruebayape.data.models.Recipe
import com.mevalera.pruebayape.data.models.Recipes
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ApiService {
    @GET("recipes/random")
    suspend fun getRecipes(
        @Query("number") number: Int,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Recipes

    @GET("recipes")
    suspend fun getRecipeDetails(
        @Query("id") id: Int,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Recipe?
}