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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ej1.ui.theme.Ej1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ej1Theme {
                // Recordamos el NavController
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Llamamos a AppNavHost, que maneja la navegación
                    AppNavHost(navController = navController, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// Definición de AppNavHost que maneja la navegación entre las pantallas
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
        modifier = Modifier.fillMaxSize()
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
                painter = painterResource(id = R.drawable.dicesmenu),
                contentDescription = "Dice Image",
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .padding(top = 16.dp)
            )

            Button(
                onClick = onJugarClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.7f)),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .padding(bottom = 116.dp)
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = "Jugar",
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
        }
    }
}
//PAGINA EN LA QUE RUEDAN
@Composable
fun DicesRollingScreen() {
    // Estado para el contador de créditos
    val creditos = remember { mutableStateOf(100) }  // Inicializamos con 100 créditos

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(60.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Contador de créditos en la parte superior
            Text(
                text = "Créditos: ${creditos.value}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color.Gray.copy(alpha = 0.7f))
                    .padding(16.dp)
            )

            // Aquí puedes agregar más contenido como los dados rodando
            Text(
                text = "¡Aquí es donde los dados van a rodar!",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 16.dp)
            )

            // Espacio para agregar más interactividad
            Spacer(modifier = Modifier.height(16.dp))

            // Botón para simular una acción (como rodar los dados)
            Button(
                onClick = {
                    // Acción simulada que resta créditos al usuario
                    if (creditos.value > 0) {
                        creditos.value -= 10
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.7f)),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Rodar Dados (-10 créditos)",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewRollTheDicePortada() {
    Ej1Theme {
        RollTheDicePortada(onJugarClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDicesRollingScreen() {
    Ej1Theme {
        DicesRollingScreen()
    }
}