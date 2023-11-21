package ar.edu.itba.example.api.ui.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun RoutineCard(
    name: String,
    description: String,
    id: Int,
    imgId: Int,
    onNavigateToRoutineDetails: (id:Int) -> Unit,
    onNavigateToExecution: (id:Int) -> Unit,
) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "https://finspo.com/"+id.toString())
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onNavigateToRoutineDetails(id) },
        shape = RoundedCornerShape(7),
    ) {
        Column(
            modifier = Modifier.background(color = Color.Black)
        ) {
            Box(contentAlignment = Alignment.TopEnd) {
                Image(
                    painter = painterResource(id = imgId),
                    contentDescription = "Routine image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.FillWidth
                )
            }
            Text(
                name,
                modifier = Modifier.padding(start = 12.dp, top = 12.dp)
            )
            Text(
                description,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
            ) {
                Row() {
                    Button(onClick = { context.startActivity(shareIntent) },
                        modifier = Modifier
                            .padding(end = 15.dp, bottom = 5.dp)
                    ) {
                        Icon(
                            tint = Color.White,
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Share"
                        )
                    }
                    Button(
                        onClick = { onNavigateToExecution(id) },
                        modifier = Modifier
                            .padding(end = 15.dp, bottom = 5.dp)
                    ) {
                        Text(
                            text = "start",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
