package ar.edu.itba.example.api.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.ui.theme.FOrange
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import ar.edu.itba.example.api.ui.theme.Black
import ar.edu.itba.example.api.ui.theme.White
import ar.edu.itba.example.api.util.getViewModelFactory

@Composable
fun ProfileScreen(
    onNavigateToLogin:()->Unit,
    orderBy: String,
    viewModel: ProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = getViewModelFactory())
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.profile_screen),
                color = FOrange,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
            )

            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                tint = White
            )

            Spacer(modifier = Modifier.height(16.dp))

            if(viewModel.uiState.isAuthenticated) {

                Column {
                    Text(
                        text = viewModel.uiState.currentUser!!.username,
                        color = White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = viewModel.uiState.currentUser!!.firstName,
                        color = White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = viewModel.uiState.currentUser!!.lastName,
                        color = White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

//            // Botón de Editar Información
//            Button(
//                onClick = {
//                    // Agrega la lógica de editar información aquí
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                colors = ButtonDefaults.buttonColors(Color.Transparent),
//                border = BorderStroke(2.dp, FOrange),
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Icon(Icons.Filled.Edit, contentDescription = null, tint = White)
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = stringResource(id = R.string.edit_profile),
//                        color= White,
//                        fontSize = 24.sp
//                    )
//                }
//            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de Cerrar Sesión
            Button(
                onClick = {
                    viewModel.logout()
                    onNavigateToLogin()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Filled.ExitToApp, contentDescription = null,tint = White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.log_out_profile),
                        color = White,
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}