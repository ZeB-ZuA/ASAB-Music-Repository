package com.udistrital.asab_music_repository

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class GuardarLetraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GuardarLetraScreen()

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GuardarLetraScreen() {
        Text("¡Bienvenido a la actividad de Guardar Letra!")
    }










}