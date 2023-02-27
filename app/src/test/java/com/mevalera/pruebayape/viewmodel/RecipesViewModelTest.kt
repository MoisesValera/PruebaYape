package com.mevalera.pruebayape.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mevalera.pruebayape.common.TestCoroutineRule
import com.mevalera.pruebayape.data.models.Recipe
import com.mevalera.pruebayape.data.source.repositories.RecipesRepository
import com.mevalera.pruebayape.presentation.recipes.RecipesViewModel
import com.mevalera.pruebayape.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runCurrent
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class RecipesViewModelTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val recipesRepository = Mockito.mock(RecipesRepository::class.java)
    private val userViewModel = RecipesViewModel(recipesRepository)

    @Test
    fun getRecipesListSuccess() = testCoroutineRule.runTest {
        //given
        val recipes = listOf(
            Recipe(
                id = 1,
                title = "test 1",
                image = "",
                readyInMinutes = 1,
                pricePerServing = 1.0,
                summary = "",
                sourceName = "",
                vegetarian = false,
                glutenFree = false
            ),
            Recipe(
                id = 2,
                title = "test 2",
                image = "",
                readyInMinutes = 2,
                pricePerServing = 2.0,
                summary = "",
                sourceName = "",
                vegetarian = false,
                glutenFree = false
            )
        )
        val flow = flow {
            emit(Result.Success(recipes))
        }
        Mockito.`when`(recipesRepository.getRecipes()).thenReturn(flow)

        //when
        userViewModel.getRecipes()

        //then
        runCurrent()
        val actual = userViewModel.recipes.value
        assertEquals(recipes, actual)
    }

    @Test
    fun getRecipesListException() = testCoroutineRule.runTest {
        //given
        val exception = Exception("Something went wrong")
        val flow = flow {
            emit(Result.Error(exception))
        }
        Mockito.`when`(recipesRepository.getRecipes()).thenReturn(flow)

        //when
        userViewModel.getRecipes()

        //then
        runCurrent()
        val expected = emptyList<Recipe>()
        val actual = userViewModel.recipes.value
        assertEquals(expected, actual)
    }

    @Test
    fun getRecipeDetailsSuccess() = testCoroutineRule.runTest {
        val recipe = Recipe(
            id = 1,
            title= "",
            image = "",
            readyInMinutes = 1,
            pricePerServing = 1.0,
            summary = "",
            sourceName = "",
            vegetarian = false,
            glutenFree = false
        )

        //given
        val flow = flow {
            emit(Result.Success(recipe))
        }
        Mockito.`when`(recipesRepository.getRecipeDetail(1)).thenReturn(flow)

        //when
        userViewModel.getRecipeDetails(1)

        //then
        runCurrent()
        val actual = userViewModel.recipeDetails.value
        assertEquals(recipe, actual)
    }
}