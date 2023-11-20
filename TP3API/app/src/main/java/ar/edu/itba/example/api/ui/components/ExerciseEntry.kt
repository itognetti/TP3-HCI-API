package ar.edu.itba.example.api.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExerciseEntry (
    title: String,
    reps : Int,
    time: Int,
    ) {
    Card(modifier = Modifier
        .padding(horizontal = 8.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title,
                    fontSize = 22.sp
                )
                if (reps>0){
                    Text(
                        text = "$reps repetitions",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                }
                if (time>0){
                    Text(
                        text = "$time seconds",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}