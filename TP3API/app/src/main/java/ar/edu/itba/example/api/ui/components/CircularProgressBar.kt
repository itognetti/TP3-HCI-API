package ar.edu.itba.example.api.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.example.api.ui.theme.FOrange
import ar.edu.itba.example.api.ui.theme.White
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CircularProgressBar() {
    var currentProgress by remember { mutableStateOf(0f) }
    var countdownSeconds by remember { mutableStateOf(60) } // Inicializamos con 60 segundos
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = {
            loading = true
            scope.launch {
                loadProgress { progress ->
                    currentProgress = progress
                }
                loading = false
            }
        }, enabled = !loading) {
            Text("Start exercise")  //no hardcodear
        }

        if (loading) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp),
                    progress = currentProgress,
                    color = FOrange
                )

                CountdownTimer(countdownSeconds, fontSize = 24.sp) // Ajusta el tamaño del temporizador
            }
        }
    }
}

@Composable
fun CountdownTimer(seconds: Int, fontSize: TextUnit) {
    Text(
        text = "$seconds s",
        fontWeight = FontWeight.Bold,
        fontSize = fontSize,
        color = White,
        modifier = Modifier.padding(8.dp)
    )
}

suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    val totalTimeInMillis = 60000 // 1 minute in milliseconds
    val interval = 100L // Interval between updates (100 milliseconds)
    val steps = totalTimeInMillis / interval

    for (i in 1..steps) {
        updateProgress(i.toFloat() / steps)
        delay(interval)

        // Actualizar cuenta regresiva
        val remainingSeconds = (totalTimeInMillis - i * interval) / 1000
    }
}
