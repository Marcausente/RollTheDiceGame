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
            verticalArrangement = Arrangement.SpaceEvenly, // Distribuimos los elementos uniformemente
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen del logo de la empresa (ahora encima del título)
            Image(
                painter = painterResource(id = R.drawable.logo), // Reemplaza con el ID de tu logo
                contentDescription = "Company Logo",
                modifier = Modifier
                    .size(150.dp) // Tamaño del logo
                    .clip(RoundedCornerShape(30.dp))
            )

            // Título grande y estilizado
            Text(
                "ROLL THE DICE",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 40.sp, // Título grande
                fontWeight = FontWeight.Bold, // Negrita para que se vea más bonito
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color.Gray.copy(alpha = 0.7f)) // Fondo transparente estilizado
                    .padding(16.dp)
            )

            // Imagen de los dados (centrado entre el título y el botón)
            Image(
                painter = painterResource(id = R.drawable.dicemenu), // Reemplaza con el ID de tu imagen de dados
                contentDescription = "Dice Image",
                modifier = Modifier
                    .size(200.dp) // Tamaño de los dados
                    .clip(RoundedCornerShape(30.dp))
            )

            // Botón para cambiar a la otra pantalla
            Button(
                onClick = { /* Aquí pondrás la navegación a otra página cuando esté lista */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.7f)),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .padding(bottom = 16.dp)
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