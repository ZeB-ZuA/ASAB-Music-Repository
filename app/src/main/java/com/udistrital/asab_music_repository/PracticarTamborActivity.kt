package com.udistrital.asab_music_repository

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class PracticarTamborActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            mainMenu(title = "Practica tambor") { padding ->
                practicarTamborScreen(padding)
            }

        }
    }

    @Composable
    fun practicarTamborScreen(padding: PaddingValues) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("¡Practica tambor!",
                modifier = Modifier.padding(24.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Text("Instrucciones:", fontWeight = FontWeight.Bold,)
            Text("Toca el “Tambor” en la pantalla del dispositivo se debe activar el sonido")
            Button(
                modifier = Modifier.padding(24.dp),
                onClick = {
                    val intent = Intent(this@PracticarTamborActivity, TamborActivity::class.java)
                    startActivity(intent)
                }) { Text("Continuar") }
        }

    }


}