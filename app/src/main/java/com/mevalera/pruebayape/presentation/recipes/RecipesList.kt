package com.mevalera.pruebayape.presentation.recipes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.mevalera.pruebayape.R
import com.mevalera.pruebayape.data.models.Recipe
import com.mevalera.pruebayape.data.models.doesMatchSearchQuery
import com.mevalera.pruebayape.navigation.TestNavigation
import com.mevalera.pruebayape.presentation.LoadingSpinner
import com.mevalera.pruebayape.theme.Green200

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipesList(recipesViewModel: RecipesViewModel, navController: NavController) {
    val recipesList by recipesViewModel.recipes.collectAsState()
    val loading by recipesViewModel.isSearching.collectAsState()
    val searchText by recipesViewModel.searchText.collectAsState()
    var filteredItems: List<Recipe>

    LaunchedEffect(key1 = Unit) {
        recipesViewModel.getRecipes()
    }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {

        when (loading) {
            true -> {
                LoadingSpinner()
            }
            else -> {
                Column(
                    modifier = Modifier
                        .background(color = Color.White)
                        .fillMaxWidth()
                ) {
                    SearchRecipesTextField(
                        searchText,
                        onSearchValueChanged = { newQuery ->
                            recipesViewModel.onSearchTextChange(newQuery)
                        })
                }
                Spacer(modifier = Modifier.size(10.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    filteredItems = if (searchText.isEmpty()) {
                        recipesList
                    } else {
                        recipesList.filter {
                            it.doesMatchSearchQuery(searchText)
                        }
                    }

                    items(filteredItems) { recipe ->
                        RecipesListItem(recipe = recipe, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun RecipesListItem(recipe: Recipe, navController: NavController) {
    Card {
        Column(modifier = Modifier
            .clickable {
                navController.navigate(TestNavigation.RecipeDetail.createRoute(recipe.id))
            }) {
            // Render the recipe title, price and source
            Column {
                SubcomposeAsyncImage(
                    model = recipe.image,
                    loading = {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp
                        )
                    },
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp)

            ) {
                Column {
                    Text(text = recipe.title, fontWeight = FontWeight.Bold, color = Green200)
                    Spacer(modifier = Modifier.size(10.dp))
                    Row {
                        Icon(Icons.Filled.ShoppingCart, "", tint = Green200)
                        Text(text = "$" + recipe.pricePerServing.toString())
                    }
                    Row {
                        Icon(Icons.Filled.Info, "", tint = Green200)
                        Text(text = recipe.sourceName)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchRecipesTextField(query: String, onSearchValueChanged: (String) -> Unit) {
    Text(
        text = stringResource(id = R.string.search_user),
        modifier = Modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Start,
        color = Green200
    )
    Spacer(modifier = Modifier.height(2.dp))
    BasicTextField(
        value = query,
        onValueChange = onSearchValueChanged,
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        decorationBox = { innerTextField ->
            Row(modifier = Modifier.fillMaxWidth()) {
                if (query.isEmpty()) {
                    Text(
                        text = "",
                        color = Green200,
                        fontSize = 14.sp
                    )
                }
            }
            innerTextField()
        }
    )
    Spacer(modifier = Modifier.height(2.dp))
    Divider(
        modifier = Modifier
            .height(2.dp)
            .background(color = Green200)
            .fillMaxWidth()
    )
}