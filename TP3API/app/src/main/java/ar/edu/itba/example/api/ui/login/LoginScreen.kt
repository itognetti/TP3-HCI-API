package ar.edu.itba.example.api.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
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
    onNavigateToHomeScreen: () -> Unit,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = getViewModelFactory())
) {

    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val uiState = viewModel.uiState

    val toastError =
        Toast.makeText(LocalContext.current, uiState.error?.message, Toast.LENGTH_SHORT)

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
            painter = painterResource(id = R.drawable.loginscreen),
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

<<<<<<< HEAD
                // Espacio para el username
=======
                // Espacio para el usuario
>>>>>>> 3b9ee491321bb82773da6a5406a2df2fccb0998f
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

                Spacer(modifier = Modifier.height(16.dp))

                // Botón de Inicio
                Button(
                    onClick = {
                        viewModel.login(username, password)
                        onNavigateToHomeScreen()
                    },
                    colors = ButtonDefaults.buttonColors(FOrange),
                    modifier = Modifier
                        .width(150.dp)
                ) {
                    Text(text = stringResource(id = R.string.login))
                }

                //se puede agregar el about us
            }
        }
    }
}
<<<<<<< HEAD
=======

//    val usernameValue = rememberSaveable{ mutableStateOf("") }
//    val passwordValue = rememberSaveable{ mutableStateOf("") }
//    var passwordVisibility by remember { mutableStateOf(false) }
//    val focusManager = LocalFocusManager.current
//    val uiState = viewModel.uiState

//    val toastError = Toast.makeText(LocalContext.current, uiState.error, Toast.LENGTH_SHORT)
//
//    LaunchedEffect(key1 = uiState.error){
//        launch {
//            if(uiState.error != null){
//                toastError.show()
//            }
//        }
//    }

//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Black)
//    ) {
//        Image(
//            modifier = Modifier
//                .matchParentSize(),
//            painter = painterResource(id = R.drawable.bg),
//            contentDescription = "Login Image",
//            contentScale = ContentScale.FillHeight
//        )
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.BottomCenter
//        ) {
//            ConstraintLayout {
//
//                var (surface, fab) = createRefs()
//
//                Surface (
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(400.dp)
//                        .constrainAs(surface) {
//                            bottom.linkTo(parent.bottom)
//                        },
//                    color = MaterialTheme.colors.background,
//                    shape = RoundedCornerShape(
//                        topStartPercent = 8,
//                        topEndPercent = 8
//                    )
//                ){
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp),
//                        verticalArrangement = Arrangement.SpaceEvenly
//                    ) {
//                        Text(
//                            stringResource(id = R.string.welcome_message),
//                            style = MaterialTheme.typography.h4.copy(
//                                fontWeight = FontWeight.Medium
//                            )
//                        )
//                        Text(
//                            stringResource(id = R.string.welcome_subtitle),
//                            style = MaterialTheme.typography.h5.copy(
//                                color = MaterialTheme.colors.primary
//                            )
//                        )
//                        Column(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(horizontal = 16.dp),
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.spacedBy(8.dp)
//                        ) {
//                            TransparentTextField(
//                                textFieldValue = usernameValue,
//                                textLabel = stringResource(id = R.string.username),
//                                keyboardType = KeyboardType.Text,
//                                keyboardActions = KeyboardActions(
//                                    onNext = {
//                                        focusManager.moveFocus(FocusDirection.Down)
//                                    }
//                                ),
//                                imeAction = ImeAction.Next
//                            )
//                            TransparentTextField(
//                                textFieldValue = passwordValue,
//                                textLabel = stringResource(id = R.string.password),
//                                keyboardType = KeyboardType.Password,
//                                keyboardActions = KeyboardActions(
//                                    onDone = {
//                                        focusManager.clearFocus()
//                                    }
//                                ),
//                                imeAction = ImeAction.Done,
//                                trailingIcon = {
//                                    IconButton(
//                                        onClick = {
//                                            passwordVisibility = !passwordVisibility
//                                        }
//                                    ) {
//                                        Icon(
//                                            imageVector = if(passwordVisibility) {
//                                                Icons.Default.Visibility
//                                            } else {
//                                                Icons.Default.VisibilityOff
//                                            },
//                                            contentDescription = "Toggle password icon"
//                                        )
//                                    }
//                                },
//                                visualTransformation = if(passwordVisibility) {
//                                    VisualTransformation.None
//                                } else {
//                                    PasswordVisualTransformation()
//                                }
//                            )
//                        }
//                        Column(
//                            modifier = Modifier.fillMaxWidth(),
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.spacedBy(8.dp)
//                        ) {
//                            RoundedButton(
//                                text = stringResource(id = R.string.log_in),
//                                displayProgressBar = false,
//                                onClick = {
//                                    viewModel.login(usernameValue.value, passwordValue.value).invokeOnCompletion {
//                                        onNavigateToHomeScreen()
//                                    }
//                                }
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
>>>>>>> 3b9ee491321bb82773da6a5406a2df2fccb0998f
