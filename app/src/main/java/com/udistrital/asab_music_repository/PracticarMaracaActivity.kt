package com.udistrital.asab_music_repository

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class PracticarMaracaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PracticarMaracaScreen()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PracticarMaracaScreen() {
        Text("¡Bienvenido a la actividad de practicar maraca!")
    }
}
