package ar.edu.itba.example.api.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.ui.main.MainViewModel
import ar.edu.itba.example.api.ui.theme.FOrange

@Composable
fun LoginRegisterScreen(
    onNavegateToLoginScreen: () -> Unit,
    onNavegateToRegisterScreen: () -> Unit,
    onNavegateToAboutUs: () -> Unit
) {

    val isScreenHorizontal =
        LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.loginregisterscreen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

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
                modifier = Modifier
                    .size(250.dp)
            )
        }

        if (isScreenHorizontal) {
            // Botones en una fila si la pantalla está horizontal
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                // Botón de Iniciar Sesión
                Button(
                    onClick = {
                        onNavegateToLoginScreen()
                    },
                    modifier = Modifier
                        .widthIn(200.dp)
                        .height(80.dp), // Ajusta el tamaño aquí
                    colors = ButtonDefaults.buttonColors(FOrange),
                ) {
                    Text(
                        text = stringResource(id = R.string.login),
                        fontSize = 24.sp
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Botón de Registrarse
                Button(
                    onClick = {
                        onNavegateToRegisterScreen()
                    },
                    modifier = Modifier
                        .widthIn(200.dp)
                        .height(80.dp), // Ajusta el tamaño aquí
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    border = BorderStroke(3.dp, FOrange),
                ) {
                    Text(
                        text = stringResource(id = R.string.register),
                        fontSize = 24.sp
                    )
                }
            }
        } else {
            // Botones en una columna si la pantalla está vertical
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .height(250.dp)
                    .padding(50.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Botón de Iniciar Sesión
                Button(
                    onClick = {
                        onNavegateToLoginScreen()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(FOrange),
                ) {
                    Text(
                        text = stringResource(id = R.string.login),
                        fontSize = 24.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botón de Registrarse
                Button(
                    onClick = {
                        onNavegateToRegisterScreen()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    border = BorderStroke(3.dp, FOrange),
                ) {
                    Text(
                        text = stringResource(id = R.string.register),
                        fontSize = 24.sp
                    )
                }
            }
        }

        IconButton(
            onClick = {
                onNavegateToAboutUs()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Info",
                tint = FOrange,
                modifier = Modifier.size(35.dp)
            )
        }
    }
}
