package ar.edu.itba.example.api.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
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
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

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
        if(totalTime != 0L)
            value = currentTime / totalTime.toFloat()
        else
            value = 0F
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
                val center = Offset(size.width / 2f, size.height / 2f)
                val beta = (250f * value + 145f) * (PI / 180f).toFloat()
                val r = size.width / 2f
                val a = cos(beta) * r
                val b = sin(beta) * r
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
            //horizontalArrangement = Arrangement.SpaceAround,
            //modifier = Modifier
              //  .fillMaxWidth()
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
                //.align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                enabled = hasPrev,
                onClick = { prevFunc() },
                //shape = RoundedCornerShape(35.dp)
            ) {
                //Text(text = "previous", color = Color.White)
                Icon(Icons.Default.ArrowBack, contentDescription = "Listar", tint = FOrange)

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
                //shape = RoundedCornerShape(35.dp)
            ){
                val icon = if (isTimerRunning) {
                    Icons.Default.Menu
                } else {
                    Icons.Default.PlayArrow
                }
                Icon(icon, contentDescription = "Pausar", tint = FOrange)
            }
            /*
            {
                Text(
                    text = if (isTimerRunning && currentTime >= 0L) "pause"
                    else if (!isTimerRunning && currentTime >= 0L) "start"
                    else "start",
                    color = Color.White
                )
            }

             */
            IconButton(
                onClick = { nextFunc() },
                //shape = RoundedCornerShape(35.dp)
            ) {
                //Text(text = "next", color = Color.White)
                Icon(Icons.Default.ArrowForward, contentDescription = "Flecha hacia adelante", tint = FOrange)

            }
            IconButton(onClick = { /* Manejar acción de siguiente */ }) {
                Icon(Icons.Default.List, contentDescription = "Listar", tint = FOrange)
            }
        }
    }
}
