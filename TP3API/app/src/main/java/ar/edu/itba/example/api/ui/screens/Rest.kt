package ar.edu.itba.example.api.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.ui.theme.FOrange


@Preview(showSystemUi = true)
@Composable
fun Rest() {
    var isTimerPaused by remember { mutableStateOf(false) }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 0.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 250.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.sentado),
                contentDescription = null,
                modifier = Modifier.size(300.dp)
            )
        }



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { /* Manejar acción de repetición */ }) {
                Icon(Icons.Default.Refresh, contentDescription = "Repetición", tint = FOrange)
            }
            IconButton(onClick = { /* Manejar acción de flecha hacia atrás */ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Flecha hacia atrás", tint = FOrange)
            }

            IconButton(onClick = {
                // Manejar acción de pausa
                isTimerPaused = !isTimerPaused
            }) {
                val icon = if (isTimerPaused) {
                    Icons.Default.Close
                } else {
                    Icons.Default.PlayArrow
                }
                Icon(icon, contentDescription = "Pausar", tint = FOrange)
            }

            IconButton(onClick = { /* Manejar acción de siguiente */ }) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Flecha hacia adelante", tint = FOrange)
            }

            IconButton(onClick = { /* Manejar acción de siguiente */ }) {
                Icon(Icons.Default.List, contentDescription = "Listar", tint = FOrange)
            }
        }
    }
}