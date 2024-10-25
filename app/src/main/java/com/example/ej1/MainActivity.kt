package com.example.ej1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.random.Random
import android.widget.Button
import android.widget.ImageView
import java.util.*
import com.example.ej1.ui.theme.Ej1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ej1Theme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(navController = navController, modifier = Modifier.padding(innerPadding))

                }
            }
        }
        lateinit var diceImage: ImageView

    }
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier .fillMaxSize() .wrapContentSize(Alignment.Center)) {
    var result by remember { mutableStateOf( 1) }
    val imageResource = when(result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = result.toString()

        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result = (1..6).random()}) {
            Text("Dados tirados")
        }
    }

}

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "portada") {
        composable("portada") {
            RollTheDicePortada(onJugarClick = {
                navController.navigate("dicesrolling")
            })
        }
        composable("dicesrolling") {
            DicesRollingScreen()
        }
    }
}

//TODO ESTO ES LA PORTADA
@Composable
fun RollTheDicePortada(modifier: Modifier = Modifier, onJugarClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize() // Ocupa todo el tamaño disponible
    ) {
        Image(
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            modifier = Modifier
                .fillMaxSize()
                .matchParentSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo de la compañía
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Company Logo",
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(30.dp))
            )

            Text(
                "ROLL THE DICE",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color.Gray.copy(alpha = 0.7f))
                    .padding(16.dp)
                    .padding(top = 16.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.dicesmenu), // Carga la imagen de los dados
                contentDescription = "Dice Image", // Descripción para accesibilidad
                modifier = Modifier
                    .size(180.dp) // Tamaño de la imagen de los dados
                    .clip(RoundedCornerShape(30.dp)) // Esquinas redondeadas
                    .padding(top = 16.dp) // Espaciado superior
            )

            // Botón para iniciar el juego
            Button(
                onClick = onJugarClick, // Acción a realizar al hacer clic en el botón
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.7f)), // Color del botón
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp)) // Esquinas redondeadas
                    .padding(bottom = 116.dp) // Espaciado inferior
                    .padding(top = 8.dp) // Espaciado superior
            ) {
                // Texto dentro del botón
                Text(
                    text = "Jugar", // Texto que se mostrará en el botón
                    fontSize = 24.sp, // Tamaño de la fuente
                    color = Color.White // Color del texto
                )
            }
        }
    }
}

//PAGINA EN LA QUE RUEDAN
@Composable
fun DicesRollingScreen() {
    // Inicializamos el estado de los créditos y los dados
    val creditos = remember { mutableStateOf(10) }  // Inicializamos con 10 créditos
    val dado1 = remember { mutableStateOf(1) }  // Valor inicial del dado 1
    val dado2 = remember { mutableStateOf(1) }  // Valor inicial del dado 2

    // Contenedor principal que ocupa toda la pantalla
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Imagen de fondo
        Image(
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            modifier = Modifier
                .fillMaxSize()
                .matchParentSize()
        )

        // Columna que organiza los elementos verticalmente
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(60.dp), // Espaciado alrededor de la columna
            verticalArrangement = Arrangement.SpaceBetween, // Espaciado entre los elementos
            horizontalAlignment = Alignment.CenterHorizontally // Alineación horizontal centrada
        ) {

            // Muestra los créditos actuales
            Text(
                text = "Créditos: ${creditos.value}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp)) // Esquinas redondeadas
                    .background(Color.Gray.copy(alpha = 0.7f)) // Fondo gris transparente
                    .padding(16.dp) // Espaciado interno
            )

            // Mensaje de bienvenida
            Text(
                text = "¡Aquí es donde los dados van a rodar!",
                textAlign = TextAlign.Center, // Alineación centrada
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 16.dp) // Espaciado superior
            )

            // Fila que contiene los dados
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly, // Espaciado uniforme entre los dados
                modifier = Modifier.padding(top = 16.dp) // Espaciado superior
            ) {
                // Dado 1
                Image(
                    painter = painterResource(id = getDiceImage(dado1.value)), // Obtiene la imagen del dado según su valor
                    contentDescription = "Dado 1",
                    modifier = Modifier
                        .size(100.dp) // Tamaño del dado
                        .clickable { // Acción al hacer clic en el dado
                            if (creditos.value > 0) { // Verifica si hay créditos disponibles
                                creditos.value -= 1 // Resta 1 crédito
                                dado1.value = (1..6).random() // Genera un nuevo valor aleatorio para el dado
                            }
                        }
                )

                // Dado 2
                Image(
                    painter = painterResource(id = getDiceImage(dado2.value)), // Obtiene la imagen del dado según su valor
                    contentDescription = "Dado 2",
                    modifier = Modifier
                        .size(100.dp) // Tamaño del dado
                        .clickable { // Acción al hacer clic en el dado
                            if (creditos.value > 0) { // Verifica si hay créditos disponibles
                                creditos.value -= 1 // Resta 1 crédito
                                dado2.value = (1..6).random() // Genera un nuevo valor aleatorio para el dado
                            }
                        }
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Espaciador entre los dados y el botón

            // Botón para rodar los dados
            Button(
                onClick = {
                    if (creditos.value > 0) { // Verifica si hay suficientes créditos
                        creditos.value -= 2 // Resta 2 créditos por lanzar los dados
                        dado1.value = (1..6).random() // Genera un nuevo valor aleatorio para el dado 1
                        dado2.value = (1..6).random() // Genera un nuevo valor aleatorio para el dado 2

                        // Lógica para otorgar créditos si los dados son iguales
                        if (dado1.value == dado2.value) {
                            if (dado1.value == 6) { // Si ambos dados son 6
                                creditos.value += 10 // Otorga 10 créditos
                                Toast.makeText(LocalContext.current, "DAUS IGUALS! +10 créditos", Toast.LENGTH_SHORT).show() // Mensaje de éxito
                            } else { // Si ambos dados son iguales pero no son 6
                                creditos.value += 5 // Otorga 5 créditos
                                Toast.makeText(LocalContext.current, "DAUS IGUALS! +5 créditos", Toast.LENGTH_SHORT).show() // Mensaje de éxito
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.7f)), // Color del botón
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp)) // Esquinas redondeadas
                    .padding(bottom = 16.dp) // Espaciado inferior
            ) {
                Text(
                    text = "Rodar Dados (-2 créditos)",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}

// Vista previa de la pantalla de portada
@Preview(showBackground = true) // Muestra un fondo en la vista previa
@Composable
fun PreviewRollTheDicePortada() {
    Ej1Theme { // Aplica el tema de la aplicación
        RollTheDicePortada(onJugarClick = {}) // Llama a la función de la pantalla de portada sin acción en el clic
    }
}

// Vista previa de la pantalla de lanzamiento de dados
@Preview(showBackground = true) // Muestra un fondo en la vista previa
@Composable
fun PreviewDicesRollingScreen() {
    Ej1Theme { // Aplica el tema de la aplicación
        DicesRollingScreen() // Llama a la función de la pantalla de lanzamiento de dados
    }
}


