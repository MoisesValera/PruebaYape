package com.mevalera.pruebayape.presentation.recipes

import android.text.Html
import android.widget.TextView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.mevalera.pruebayape.presentation.LoadingSpinner
import com.mevalera.pruebayape.theme.Green200

@Composable
fun RecipeDetail(recipesViewModel: RecipesViewModel, recipeId: Int) {
    val recipeDetails by recipesViewModel.recipeDetails.collectAsState()
    val loading by recipesViewModel.isSearching.collectAsState()
    val screenBackgroundColor = MaterialTheme.colors.background

    LaunchedEffect(key1 = Unit) {
        recipesViewModel.getRecipeDetails(recipeId)
    }

    when (loading) {
        true -> {
            LoadingSpinner()
        }
        else -> {
            recipeDetails?.let { recipe ->
                // Render the recipe title, price and source
                Column(modifier = Modifier.fillMaxSize()) {
                    Column {
                        SubcomposeAsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(recipe.image)
                                .crossfade(true)
                                .build(),
                            loading = {
                                CircularProgressIndicator()
                            },
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .graphicsLayer { alpha = 0.99f }
                                .drawWithContent {
                                    val colors = listOf(
                                        Color.Transparent,
                                        Color.White
                                    )
                                    drawContent()
                                    drawRect(
                                        brush = Brush.verticalGradient(
                                            colors = colors
                                        )
                                    )
                                }
                        )
                    }
                    Box(contentAlignment = Alignment.BottomCenter) {
                        Card(modifier = Modifier.padding(20.dp), elevation = 15.dp) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 10.dp)

                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(10.dp)
                                ) {
                                    Text(
                                        text = recipe.title,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Green200
                                    )
                                    Spacer(modifier = Modifier.size(10.dp))
                                    Row {
                                        Icon(Icons.Filled.ShoppingCart, "", tint = Green200)
                                        Text(text = "$" + recipe.pricePerServing.toString())
                                    }
                                    Row {
                                        Icon(Icons.Filled.Info, "", tint = Green200)
                                        Text(text = recipe.sourceName)
                                    }
                                    Spacer(modifier = Modifier.size(20.dp))
                                    Column(modifier= Modifier
                                        .verticalScroll(rememberScrollState())){
                                        recipe.summary?.let {
                                            AndroidView(factory = { context ->
                                                TextView(context).apply {
                                                    text = Html.fromHtml(it)
                                                }
                                            })
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
