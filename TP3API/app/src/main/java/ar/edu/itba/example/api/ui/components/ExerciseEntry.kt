package ar.edu.itba.example.api.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.example.api.ui.theme.Black
import ar.edu.itba.example.api.ui.theme.White

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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Black)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {

            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .size(24.dp),
            )

            Spacer(modifier = Modifier.width(100.dp))

            Icon(
                imageVector = Icons.Default.Alarm,
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .size(24.dp),
            )

            Spacer(modifier = Modifier.width(32.dp))
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Black)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = title,
                fontSize = 22.sp,
                color = White
            )
            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = if (reps>0) "$reps reps." else "-",
                fontSize = 18.sp,
                color = White
            )
            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text =  if (time>0) "$time s." else "-",
                fontSize = 18.sp,
                color = White
            )
        }
    }
}