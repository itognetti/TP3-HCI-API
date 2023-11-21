package ar.edu.itba.example.api.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.ui.theme.Black
import ar.edu.itba.example.api.ui.theme.FOrange
import ar.edu.itba.example.api.util.getViewModelFactory
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToAboutUs: () -> Unit,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = getViewModelFactory())
) {

    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val uiState = viewModel.uiState

    val toastError = Toast.makeText(LocalContext.current, uiState.error?.message, Toast.LENGTH_SHORT)

    LaunchedEffect(key1 = uiState.error) {
        launch {
            if (uiState.error != null) {
                toastError.show()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.loginregisterscreen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            IconButton(
                onClick = {
                    onNavigateToAboutUs()
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd),
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Información",
                    tint = FOrange,
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        val widthInDp = LocalConfiguration.current.screenWidthDp.dp

        if (widthInDp > 600.dp) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logotext),
                    contentDescription = null,
                    modifier = Modifier.size(250.dp)
                )

                Spacer(modifier = Modifier.width(64.dp))

                Card(
                    modifier = Modifier
                        .width(300.dp)
                        .fillMaxHeight()
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = stringResource(id = R.string.login),
                            fontSize = 30.sp,
                            color = Black,
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Espacio para el usuario
                        TextField(
                            value = username,
                            onValueChange = { username = it },
                            label = { Text(text = stringResource(id = R.string.username)) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Espacio para la contraseña
                        TextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text(text = stringResource(id = R.string.login_password)) },
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Botón de Inicio
                        Button(
                            onClick = {
                                viewModel.login(username, password)
                                onNavigateToHome()
                            },
                            colors = ButtonDefaults.buttonColors(FOrange),
                            modifier = Modifier.width(150.dp)
                        ) {
                            Text(text = stringResource(id = R.string.login))
                        }
                    }
                }


            }

        }else{

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logotext),
                    contentDescription = null,
                    modifier = Modifier.size(250.dp)
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.65f)
                    .padding(top = 150.dp)
                    .align(Alignment.Center),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        fontWeight = Bold,
                        text = stringResource(id = R.string.login),
                        fontSize = 30.sp,
                        color = Black,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // espacio para el usuario
                    TextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text(text = stringResource(id = R.string.username)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Espacio para la contraseña
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = stringResource(id = R.string.login_password)) },
                        visualTransformation = PasswordVisualTransformation(), //esta linea y la de abajo son para que no se vea la contraseña
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botón de Inicio
                    Button(
                        onClick = {
                            viewModel.login(username, password)
                            onNavigateToHome()
                        },
                        colors = ButtonDefaults.buttonColors(FOrange),
                        modifier = Modifier
                            .width(150.dp)
                    ) {
                        Text(text = stringResource(id = R.string.login))
                    }
                }
            }

        }
    }
}