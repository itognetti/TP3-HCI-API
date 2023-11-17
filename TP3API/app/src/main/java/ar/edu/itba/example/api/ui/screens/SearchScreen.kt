package ar.edu.itba.example.api.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.ui.components.CardItem
import ar.edu.itba.example.api.ui.components.SearchBar
import ar.edu.itba.example.api.ui.theme.Black
import ar.edu.itba.example.api.ui.theme.FOrange

@Preview
@Composable
fun SearchScreen() {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.search_screen),
            fontSize = 30.sp,
            color = FOrange
        )
        SearchBar(onSearchTextChanged = { newQuery ->
            searchQuery = newQuery
            // Puedes realizar acciones de búsqueda aquí según la nueva consulta
        })

        // Lista de tarjetas
        CardItem(imageResId = R.drawable.gym1, title = "Tarjeta 1", description = "Descripción de la tarjeta 1")
        CardItem(imageResId = R.drawable.gym2, title = "Tarjeta 2", description = "Descripción de la tarjeta 2")
        CardItem(imageResId = R.drawable.gym3, title = "Tarjeta 3", description = "Descripción de la tarjeta 3")
        CardItem(imageResId = R.drawable.gym4, title = "Tarjeta 4", description = "Descripción de la tarjeta 4")
        CardItem(imageResId = R.drawable.gym5, title = "Tarjeta 5", description = "Descripción de la tarjeta 5")
        // Añade más tarjetas según sea necesario
    }
}