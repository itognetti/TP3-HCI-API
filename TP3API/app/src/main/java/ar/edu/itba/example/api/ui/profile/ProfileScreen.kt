package ar.edu.itba.example.api.ui.profile

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import ar.edu.itba.example.api.ui.main.canGetCurrentUser
import ar.edu.itba.example.api.ui.theme.White
import ar.edu.itba.example.api.util.getViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    onNavigateToLogin:()->Unit,
    viewModel: ProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = getViewModelFactory())
) {
    val uiState = viewModel.uiState

    LaunchedEffect(key1 = Unit){
        launch {
            if(uiState.canGetCurrentUser)
                viewModel.getCurrentUser()
        }
    }

    val toastError = Toast.makeText(LocalContext.current, uiState.error?.message ?: "", Toast.LENGTH_SHORT)

    LaunchedEffect(key1 = uiState.error){
        if (uiState.error != null){
            toastError.show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val widthInDp = LocalConfiguration.current.screenWidthDp.dp

            if (widthInDp > 600.dp){
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top=5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .size(100.dp)
                                .clip(CircleShape),
                            tint = White
                        )
                        Button(
                            onClick = {
                                viewModel.logout()
                                onNavigateToLogin()
                            },
                            modifier = Modifier
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(Icons.Filled.ExitToApp, contentDescription = null, tint = White)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = stringResource(id = R.string.log_out_profile),
                                    color = White,
                                    fontSize = 24.sp
                                )
                            }
                        }
                    }

                    if (viewModel.uiState.isAuthenticated) {
                        Column {
                            uiState.currentUser?.let {
                                Text(
                                    text = stringResource(id = R.string.login_mail) + ": " + it.email,
                                    color = White,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                            uiState.currentUser?.let {
                                Text(
                                    text = stringResource(id = R.string.username) + ": " + it.username,
                                    color = White,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                            uiState.currentUser?.let {
                                Text(
                                    text = stringResource(id = R.string.first_name) + ": " + it.firstName,
                                    color = White,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                            uiState.currentUser?.let {
                                Text(
                                    text = stringResource(id = R.string.last_name) + ": " + it.lastName,
                                    color = White,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape),
                        tint = White
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    if (viewModel.uiState.isAuthenticated) {

                        Column(horizontalAlignment = Alignment.Start) {
                            uiState.currentUser?.let {
                                Text(
                                    text = stringResource(id = R.string.login_mail) + ": " + it.email,
                                    color = White,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(6.dp)
                                )
                            }
                            uiState.currentUser?.let {
                                Text(
                                    text = stringResource(id = R.string.username) + ": " + it.username,
                                    color = White,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(6.dp)
                                )
                            }
                            uiState.currentUser?.let {
                                Text(
                                    text = stringResource(id = R.string.first_name) + ": " + it.firstName,
                                    color = White,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(6.dp)
                                )
                            }
                            uiState.currentUser?.lastName?.let {lastName ->
                                if (lastName.isNotEmpty()) {
                                    Text(
                                        text = stringResource(id = R.string.last_name) + ": $lastName",
                                        color = White,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(6.dp)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

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
                            Icon(Icons.Filled.ExitToApp, contentDescription = null, tint = White)
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
    }
}