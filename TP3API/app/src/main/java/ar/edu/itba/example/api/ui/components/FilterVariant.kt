package ar.edu.itba.example.api.ui.components

import ar.edu.itba.example.api.R

sealed class FilterVariant(
    val text: Int,
    val order: String,
    val direction: String
) {
    object DateAsc: FilterVariant(
        text = R.string.date_asc,
        order = "date",
        direction = "asc"
    )

    object DateDesc: FilterVariant(
        text = R.string.date_desc,
        order = "date",
        direction = "desc"
    )

    object RatingAsc: FilterVariant(
        text = R.string.rating_asc,
        order = "score",
        direction = "asc"
    )

    object RatingDesc: FilterVariant(
        text = R.string.rating_desc,
        order = "score",
        direction = "desc"
    )

    object DifficultyAsc: FilterVariant(
        text = R.string.difficulty_asc,
        order = "difficulty",
        direction = "asc"
    )

    object DifficultyDesc: FilterVariant(
        text = R.string.difficulty_desc,
        order = "difficulty",
        direction = "desc"
    )
}