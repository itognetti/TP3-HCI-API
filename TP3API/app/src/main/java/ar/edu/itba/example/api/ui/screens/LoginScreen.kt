package ar.edu.itba.example.api.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.ui.theme.Black
import ar.edu.itba.example.api.ui.theme.FOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onNavegateToHomeScreen: () -> Unit) {
    var nombreCompleto by remember { mutableStateOf("") }
    var correoElectronico by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.register),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
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
                modifier = Modifier.size(250.dp)
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.7f)
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

                // Espacio para el correo electrónico
                TextField(
                    value = correoElectronico,
                    onValueChange = { correoElectronico = it },
                    label = { Text(text = stringResource(id = R.string.login_mail))},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)

                )

                Spacer(modifier = Modifier.height(16.dp))

                // Espacio para la contraseña
                TextField(
                    value = contrasena,
                    onValueChange = { contrasena = it },
                    label = { Text(text = stringResource(id = R.string.login_password))},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)


                )

                Spacer(modifier = Modifier.height(16.dp))



                Spacer(modifier = Modifier.height(16.dp))

                // Botón de Inicio
                Button(
                    onClick = {
                        onNavegateToHomeScreen()
                    },
                    colors = ButtonDefaults.buttonColors(FOrange),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)

                ) {
                    Text(text = stringResource(id = R.string.login))
                }

            }
        }
    }
}






