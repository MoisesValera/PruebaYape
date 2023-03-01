package com.mevalera.pruebayape

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mevalera.pruebayape.navigation.TestNavigation
import com.mevalera.pruebayape.presentation.recipes.RecipeDetail
import com.mevalera.pruebayape.presentation.recipes.RecipeOnMap
import com.mevalera.pruebayape.presentation.recipes.RecipesList
import com.mevalera.pruebayape.presentation.recipes.RecipesViewModel
import com.mevalera.pruebayape.ui.theme.TestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val recipesViewModel = viewModel<RecipesViewModel>()
            val navController = rememberNavController()
            val currentBackStackEntry by navController.currentBackStackEntryAsState()

            val showBackButton by remember(currentBackStackEntry) {
                derivedStateOf {
                    navController.previousBackStackEntry != null
                }
            }

            TestTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = stringResource(id = R.string.app_bar_title),
                                    textAlign = TextAlign.Left,
                                    modifier =
                                    if (showBackButton) {
                                        Modifier.offset(x = (-10).dp)
                                    } else {
                                        Modifier.offset(x = (-50).dp)
                                    }
                                )
                            },
                            navigationIcon = {
                                if (showBackButton) {
                                    IconButton(
                                        onClick = {
                                            navController.popBackStack()
                                        }
                                    ) {
                                        Icon(Icons.Filled.ArrowBack, "")
                                    }
                                } else {
                                    //NOOP
                                }
                            },
                            actions = {
                                // search icon
                                IconButton(onClick = {
                                    //
                                }) {
                                    Image(
                                        painterResource(R.drawable.logo),
                                        "",
                                        modifier = Modifier
                                            .size(50.dp)
                                            .offset(x = (-10).dp),
                                    )
                                }
                            }
                        )
                    }
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = TestNavigation.Recipes.route
                        ) {
                            composable(TestNavigation.Recipes.route) {
                                RecipesList(recipesViewModel, navController)
                            }
                            composable(
                                TestNavigation.RecipeDetail.route,
                                arguments = listOf(navArgument(RECIPE_ID) {
                                    type = NavType.IntType
                                })
                            ) { backStackEntry ->
                                backStackEntry.arguments?.let {
                                    RecipeDetail(
                                        recipesViewModel = recipesViewModel,
                                        recipeId = it.getInt(RECIPE_ID),
                                        navController = navController
                                    )
                                }
                            }
                            composable(
                                TestNavigation.RecipeOnMap.route,
                                arguments = listOf(navArgument(RECIPE_ID) {
                                    type = NavType.IntType
                                })
                            ) {
                                    backStackEntry ->
                                backStackEntry.arguments?.let {
                                    RecipeOnMap(
                                        recipesViewModel = recipesViewModel,
                                        recipeId = it.getInt(RECIPE_ID)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    companion object{
        const val RECIPE_ID = "recipeId"
    }
}