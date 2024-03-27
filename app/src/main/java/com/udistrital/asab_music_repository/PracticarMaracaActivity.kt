package com.udistrital.asab_music_repository

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

class PracticarMaracaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            mainMenu(title = "Practica Maraca") { padding ->
                practicarMaracaScreen(padding)

            }
        }
    }

    @Composable

    fun practicarMaracaScreen(padding: PaddingValues) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text("¡Practica Maraca!",
                modifier = Modifier.padding(24.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Text("Instrucciones:", fontWeight = FontWeight.Bold,)
            Text("Mueve tu dispositivo con movimientos rápidos y firmes para obtener un sonido más fuerte.\n" +
                    "Experimenta con diferentes movimientos para encontrar la forma más cómoda de usar la maraca virtual.\n" +
                    "Ajusta la sensibilidad del movimiento a tu gusto.\n" +
                    "Disfruta de la experiencia de tocar una maraca virtual con diferentes intensidades de sonido.")
            Button(
                modifier = Modifier.padding(24.dp),
                onClick = {
                    val intent = Intent(this@PracticarMaracaActivity, MaracaActivity::class.java)
                    startActivity(intent)
                }) { Text("Continuar") }

        }

    }


}

