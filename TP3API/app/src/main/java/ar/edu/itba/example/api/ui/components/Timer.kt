package ar.edu.itba.example.api.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.example.api.ui.theme.FOrange
import ar.edu.itba.example.api.ui.theme.White
import kotlinx.coroutines.delay

@Composable
fun Timer(
    totalTime: Long,
    inactiveBarColor: Color,
    activeBarColor: Color,
    modifier: Modifier = Modifier,
    nextFunc: () -> Unit,
    prevFunc: () -> Unit,
    hasPrev: Boolean,
    initialValue: Float = 1f,
    strokeWidth: Dp = 5.dp
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var value by remember {
        mutableStateOf(initialValue)
    }

    var currentTime by remember {
        mutableStateOf(totalTime)
    }

    var isTimerRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = totalTime){
        currentTime = totalTime
        isTimerRunning = false
        value = if(totalTime != 0L)
            currentTime / totalTime.toFloat()
        else
            0F
    }

    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if(currentTime > 0 && isTimerRunning) {
            delay(1000L)
            currentTime -= 1000L
            value = currentTime / totalTime.toFloat()
        }
    }
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .onSizeChanged {
                    size = it
                }
        ) {
            Canvas(modifier = modifier) {
                drawArc(
                    color = inactiveBarColor,
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
                drawArc(
                    color = activeBarColor,
                    startAngle = -90f,
                    sweepAngle = 360f * value,
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = (currentTime / 1000L).toString(),
                    fontSize = 80.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
                Text(
                    text = " s",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(
                enabled = hasPrev,
                onClick = { prevFunc()
                    currentTime = totalTime
                    isTimerRunning = true},
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Listar",
                    tint = FOrange,
                    modifier = Modifier.size(36.dp)
                )
            }
            IconButton(
                enabled = value != 0F,
                onClick = {
                    if (currentTime <= 0L) {
                        currentTime = totalTime
                        isTimerRunning = true
                    } else {
                        isTimerRunning = !isTimerRunning
                    }
                },
            ){
                val icon = if (isTimerRunning) {
                    Icons.Default.Stop
                } else {
                    Icons.Default.PlayArrow
                }
                Icon(
                    icon,
                    contentDescription = "Pausar",
                    tint = FOrange,
                    modifier = Modifier.size(36.dp)
                )
            }
            IconButton(
                onClick = { nextFunc()
                    currentTime = totalTime
                    isTimerRunning = true },
            ) {
                Icon(
                    Icons.Default.ArrowForward,
                    contentDescription = "Flecha hacia adelante",
                    tint = FOrange,
                    modifier = Modifier.size(36.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}