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
    val creditos = remember { mutableStateOf(10) }  // Inicializamos con 100 créditos
    val dado1 = remember { mutableStateOf(1) }  // Valor inicial del dado 1
    val dado2 = remember { mutableStateOf(1) }  // Valor inicial del dado 2

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
                .padding(60.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

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

            Text(
                text = "¡Aquí es donde los dados van a rodar!",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 16.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                // Dado 1
                Image(
                    painter = painterResource(id = getDiceImage(dado1.value)),
                    contentDescription = "Dado 1",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            if (creditos.value > 0) {
                                creditos.value -= 1
                                dado1.value = (1..6).random()
                            }
                        }
                )

                // Dado 2
                Image(
                    painter = painterResource(id = getDiceImage(dado2.value)),
                    contentDescription = "Dado 2",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            if (creditos.value > 0) {
                                creditos.value -= 1
                                dado2.value = (1..6).random()
                            }
                        }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            Button(
                onClick = {
                    if (creditos.value > 0) {
                        creditos.value -= 2
                        dado1.value = (1..6).random()
                        dado2.value = (1..6).random()

                        // Lógica para otorgar créditos
                        if (dado1.value == dado2.value) {
                            if (dado1.value == 6) {
                                creditos.value += 10
                                Toast.makeText(LocalContext.current, "DAUS IGUALS! +10 créditos", Toast.LENGTH_SHORT).show()
                            } else {
                                creditos.value += 5
                                Toast.makeText(LocalContext.current, "DAUS IGUALS! +5 créditos", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.7f)),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .padding(bottom = 16.dp)
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


