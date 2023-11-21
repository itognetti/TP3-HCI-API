package ar.edu.itba.example.api.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ar.edu.itba.example.api.R
import ar.edu.itba.example.api.ui.login.LoginViewModel
import ar.edu.itba.example.api.ui.theme.FOrange

@Composable
fun LogInButton(viewModel: LoginViewModel, username: String, password: String, onNavigateToHomeScreen: () -> Unit){
    Button(
        onClick = {
            viewModel.login(username, password).invokeOnCompletion {
                onNavigateToHomeScreen()
            }
        },
        colors = ButtonDefaults.buttonColors(FOrange),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(text = stringResource(id = R.string.login))
    }
}