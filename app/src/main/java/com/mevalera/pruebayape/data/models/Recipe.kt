package com.mevalera.pruebayape.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "recipes")
data class Recipe(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String = "No title",
    @ColumnInfo(name = "image")
    val image: String? = emptyImage,
    @ColumnInfo(name = "readyInMinutes")
    val readyInMinutes: Int? = 0,
    @ColumnInfo(name = "pricePerServing")
    val pricePerServing: Double? = 0.0,
    @ColumnInfo(name = "summary")
    val summary: String? = "No summary",
    @ColumnInfo(name = "sourceName")
    val sourceName: String = "No source name",
    @ColumnInfo(name = "vegetarian")
    val vegetarian: Boolean? = false,
    @ColumnInfo(name = "glutenFree")
    val glutenFree: Boolean? = false
) : Parcelable

fun Recipe.doesMatchSearchQuery(query: String): Boolean {
    val matchingCombinations = listOf(
        "$title$sourceName",
        "$title $sourceName",
        "${title.first()} ${sourceName.first()}",
    )

    return matchingCombinations.any {
        it.contains(query, ignoreCase = true)
    }
}

const val emptyImage = "https://www.generationsforpeace.org/wp-content/uploads/2018/03/empty.jpg"