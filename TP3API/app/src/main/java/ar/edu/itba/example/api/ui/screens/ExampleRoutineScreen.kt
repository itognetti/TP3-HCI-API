package ar.edu.itba.example.api.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.ui.components.CardItem
import ar.edu.itba.example.api.ui.components.ExerciseItem

@Preview
@Composable
fun ExampleRoutineScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardItem(
                imageResId = R.drawable.gym1,
                title = "Tarjeta 1",
                description = "Descripción de la tarjeta 1"
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ExerciseItem("Ejercicio 1")
            ExerciseItem("Ejercicio 2")
            ExerciseItem("Ejercicio 3")
        }
    }
}