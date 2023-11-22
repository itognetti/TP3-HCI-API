package ar.edu.itba.example.api.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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
fun CycleEntry (
    title: String,
    rounds: Int,
    onNavigateToCycleDetails: (id:Int) -> Unit,
    cycleId: Int
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { onNavigateToCycleDetails(cycleId) },
        shape = RoundedCornerShape(12.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Black)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column (
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title,
                    fontSize = 22.sp,
                    color = White
                )
                Text(
                    text = "$rounds rounds",
                    fontSize = 18.sp,
                    color = White
                )
            }
            Column (
                horizontalAlignment = Alignment.End,
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier
                        .size(24.dp),
                )
            }
        }
    }
}