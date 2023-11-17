package ar.edu.itba.example.api.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.itba.example.api.ui.theme.Black
import ar.edu.itba.example.api.ui.theme.FOrange


class MainAct2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                // A surface container using the 'background' color from the theme
                MyApp(modifier = Modifier.fillMaxSize())

        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable {mutableStateOf(false)} //para que no se pierda cuando  rompa la actividad , rote pantalla
    val extraPadding by animateDpAsState(
        if(expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )
        Surface(color = Black,
        modifier = Modifier.padding(vertical=4.dp,horizontal=8.dp)
    ) {
        Row ( modifier = Modifier
            .padding(24.dp)){
            Column (modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)){//le agrego este padding que hace que le hace un recuadro mas grande a la frase
                Text(text = "EJERCICIO",color = FOrange)
                Text(text=name ,color = FOrange,
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
fun Greetings(modifier: Modifier= Modifier,
              names:List<String> =List(20){"$it" }){
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items=names){name ->   //para que me imprima solo los de la pantalla
            Greeting(name)
        }
    }

}

@Composable
fun OnboardingScreen(modifier: Modifier=Modifier,
                     onContinueClicked:() ->Unit){
    Surface (color = Black){
        Column(
            modifier=modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text("FINSPO",color = FOrange)
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
        MyApp()
}