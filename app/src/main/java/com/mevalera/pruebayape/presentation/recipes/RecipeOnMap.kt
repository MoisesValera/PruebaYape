package com.mevalera.pruebayape.presentation.recipes

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.mevalera.pruebayape.presentation.LoadingSpinner
import com.mevalera.pruebayape.theme.Magenta200
import com.mevalera.pruebayape.R

@Composable
fun RecipeOnMap(recipesViewModel: RecipesViewModel, recipeId: Int) {
    val recipeDetails by recipesViewModel.recipeDetails.collectAsState()
    val loading by recipesViewModel.isSearching.collectAsState()

    LaunchedEffect(key1 = Unit) {
        recipesViewModel.getRecipeDetails(recipeId)
    }

    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 12f)
    }

    val mapProperties by remember {
        mutableStateOf(
            MapProperties(maxZoomPreference = 15f, minZoomPreference = 10f)
        )
    }
    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(mapToolbarEnabled = false)
        )
    }
    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            properties = mapProperties,
            uiSettings = mapUiSettings,
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = singapore),
                title = stringResource(id = R.string.marker_details)
            )
        }
        when (loading) {
            true -> {
                LoadingSpinner()
            }
            else -> {
                recipeDetails?.let { recipe ->
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .align(Alignment.TopStart)
                            .fillMaxWidth()
                    ) {
                        Card(modifier = Modifier.padding(20.dp), elevation = 15.dp) {
                            Row(
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
                                        color = Magenta200
                                    )
                                    Spacer(modifier = Modifier.size(10.dp))
                                    Row {
                                        Icon(Icons.Filled.ShoppingCart, "", tint = Magenta200)
                                        Text(text = "$" + recipe.pricePerServing.toString())
                                    }
                                    Row {
                                        Icon(Icons.Filled.Info, "", tint = Magenta200)
                                        Text(text = recipe.sourceName)
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