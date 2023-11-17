package ar.edu.itba.example.api.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import ar.edu.itba.example.api.R

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.home_screen),
            fontSize = 30.sp
        )
    }
}

/*

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable {mutableStateOf(false)} //para que no se pierda cuando  rompa la actividad , rote pantalla
    val extraPadding by animateDpAsState(
        if(expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Surface(color = Osc,
        modifier = Modifier.padding(vertical=4.dp,horizontal=8.dp)
    ) {
        Row ( modifier = Modifier
            .padding(24.dp)){
            Column (modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)){//le agrego este padding que hace que le hace un recuadro mas grande a la frase
                Text(text = "EJERCICIO",color = Orange)
                Text(text=name ,color = Orange,
                    style =MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold))
            }
            ElevatedButton(
                onClick = { expanded=!expanded}) {
                Text(text= if(expanded) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun Greetings(modifier: Modifier= Modifier, names:List<String> =List(20){"$it" }){
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items=names){name ->   //para que me imprima solo los de la pantalla
            Greeting(name)
        }
    }
}

@Composable
fun OnboardingScreen(modifier: Modifier=Modifier, onContinueClicked:() ->Unit){
    Surface (color = Osc){
        Column(
            modifier=modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("FINSPO",color = Orange)
            ElevatedButton(
                modifier= Modifier.padding(vertical=24.dp),
                onClick = onContinueClicked) {
                Text("Ver Ejercicios")
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier= Modifier) {
    var shouldShowOnboarding by remember{ mutableStateOf(true) }
    Surface(modifier) {
        if(shouldShowOnboarding){
            OnboardingScreen(onContinueClicked={shouldShowOnboarding=false})
        }else{
            Greetings()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyAppPreview() {
    MyApplicationTheme  {
        MyApp()
    }
}

@Composable
fun OnboardingScreen2(modifier: Modifier=Modifier, onContinueClicked:() ->Unit){
    Surface (color = Osc){
        Column(
            modifier=modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text("FINSPO",color = Orange)
            ElevatedButton(
                modifier= Modifier.padding(vertical=24.dp),
                onClick = onContinueClicked) {
                Text("INICIAR SESION")
            }
            ElevatedButton(
                modifier= Modifier.padding(vertical=24.dp),
                onClick = onContinueClicked) {
                Text("REGISTRARTE")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingPreview() {
    MyApplicationTheme  {
        OnboardingScreen2(onContinueClicked = {})
    }
}

 */