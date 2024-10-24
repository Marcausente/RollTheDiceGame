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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.ej1.ui.theme.Ej1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ej1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RollTheDicePortada(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun RollTheDicePortada(modifier: Modifier = Modifier) {

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
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween, // Ajustamos el espacio entre los elementos
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo de la empresa
            Image(
                painter = painterResource(id = R.drawable.logo), // Reemplaza con el ID de tu logo
                contentDescription = "Company Logo",
                modifier = Modifier
                    .size(150.dp) // Tamaño del logo
                    .clip(RoundedCornerShape(30.dp))
            )

            // Título grande y estilizado (más cerca del logo)
            Text(
                "ROLL THE DICE",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 40.sp, // Título grande
                fontWeight = FontWeight.Bold, // Negrita
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color.Gray.copy(alpha = 0.7f)) // Fondo estilizado
                    .padding(16.dp)
                    .padding(top = 16.dp) // Ajustamos el espacio superior para acercarlo al logo
            )

            // Imagen de los dados (ajustada más arriba)
            Image(
                painter = painterResource(id = R.drawable.dicesmenu), // Reemplaza con el ID de tu imagen de dados
                contentDescription = "Dice Image",
                modifier = Modifier
                    .size(180.dp) // Tamaño un poco reducido para que suba más
                    .clip(RoundedCornerShape(30.dp))
                    .padding(top = 16.dp) // Reducimos el espacio superior
            )

            // Botón para cambiar a la otra pantalla (subido más arriba)
            Button(
                onClick = { /* Aquí pondrás la navegación a otra página cuando esté lista */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.7f)),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .padding(bottom = 120.dp)
                    .padding(top = 8.dp) // Reducimos el espacio superior para subir el botón
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

@Preview(showBackground = true)
@Composable
fun PreviewRollTheDicePortada() {
    Ej1Theme {
        RollTheDicePortada()
    }
}