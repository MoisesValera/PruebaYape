package com.mevalera.pruebayape.navigation

sealed class TestNavigation(val route: String) {
    object Recipes : TestNavigation("recipes")
    object RecipeDetail : TestNavigation("recipes/{recipeId}") {
        fun createRoute(recipeId: Int) = "recipes/$recipeId"
    }
}