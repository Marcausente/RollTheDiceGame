package com.example.ej1

import android.os.Bundle
import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ej1.ui.theme.Ej1Theme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh

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
fun DiceWithButtonAndImage(modifier: Modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
    var result by remember { mutableStateOf(1) }
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = result.toString()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result = (1..6).random() }) {
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

@Composable
fun RollTheDicePortada(modifier: Modifier = Modifier, onJugarClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize().matchParentSize()
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
                modifier = Modifier.size(150.dp).clip(RoundedCornerShape(30.dp))
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
            )

            Image(
                painter = painterResource(id = R.drawable.dicesmenu),
                contentDescription = "Dice Image",
                modifier = Modifier.size(180.dp).clip(RoundedCornerShape(30.dp)).padding(top = 16.dp)
            )

            Button(
                onClick = onJugarClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.7f)),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .padding(bottom = 116.dp, top = 8.dp)
            ) {
                Text("Jugar", fontSize = 24.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun DicesRollingScreen() {
    val context = LocalContext.current
    val creditos = remember { mutableStateOf(10) }
    val dado1 = remember { mutableStateOf(1) }
    val dado2 = remember { mutableStateOf(1) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize().matchParentSize()
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

            IconButton(
                onClick = {
                    creditos.value = 10
                    dado1.value = 1
                    dado2.value = 1
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Reiniciar Juego",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = getDiceImage(dado1.value)),
                    contentDescription = "Dado 1",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            if (creditos.value > 0) {
                                creditos.value -= 1
                                dado1.value = (1..6).random()
                                checkEqualDice(context, creditos, dado1.value, dado2.value)
                            }
                        }
                )

                Image(
                    painter = painterResource(id = getDiceImage(dado2.value)),
                    contentDescription = "Dado 2",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            if (creditos.value > 0) {
                                creditos.value -= 1
                                dado2.value = (1..6).random()
                                checkEqualDice(context, creditos, dado1.value, dado2.value)
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
                        checkEqualDice(context, creditos, dado1.value, dado2.value)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.7f)),
                modifier = Modifier.clip(RoundedCornerShape(20.dp)).padding(bottom = 16.dp)
            ) {
                Text("Rodar Dados (-2 créditos)", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}

fun checkEqualDice(context: Context, creditos: MutableState<Int>, dado1: Int, dado2: Int) {
    if (dado1 == dado2) {
        val puntosGanados = if (dado1 == 6) 10 else 5
        creditos.value += puntosGanados
        Toast.makeText(context, "¡DADOS IGUALES! +$puntosGanados créditos", Toast.LENGTH_SHORT).show()
    }
}

fun getDiceImage(diceValue: Int): Int {
    return when (diceValue) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRollTheDicePortada() {
    Ej1Theme {
        RollTheDicePortada(onJugarClick = {})
    }
}