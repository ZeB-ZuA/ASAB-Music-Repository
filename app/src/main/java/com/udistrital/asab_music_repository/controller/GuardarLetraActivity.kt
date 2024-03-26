package com.udistrital.asab_music_repository.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.udistrital.asab_music_repository.ListarCancionesActivity
import com.udistrital.asab_music_repository.entity.Cancion
import com.udistrital.asab_music_repository.mainMenu
import com.udistrital.asab_music_repository.repository.DataBaseHelper
import com.udistrital.asab_music_repository.service.CancionService
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class GuardarLetraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            mainMenu("Guardar Cancion") { padding -> guardarLetraScreen(padding) }

        }
    }


    @Composable
    fun guardarLetraScreen(padding: PaddingValues) {
        var nombre by remember { mutableStateOf("") }
        var letra by remember { mutableStateOf("") }
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val cancionService = CancionService(DataBaseHelper(context))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.run { align(Alignment.Center).offset(y = (-200).dp) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre de la canción") },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                TextField(
                    value = letra,
                    onValueChange = { letra = it },
                    label = { Text("Letra de la canción") },
                    maxLines = 10,
                    modifier = Modifier
                        .height(200.dp)
                        .width(280.dp)
                        .padding(bottom = 4.dp)

                )
                Button(
                    onClick = {
                        val calendar = Calendar.getInstance()
                        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        val fecha = format.format(calendar.time)
                        val cancion = Cancion(0, nombre, fecha, letra)
                        val result = cancionService.save(cancion)
                        if (result != -1L) {
                            scope.launch {
                                Toast.makeText(
                                    context, "Canción guardada exitosamente", Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            scope.launch {
                                Toast.makeText(
                                    context, "Error al guardar la canción", Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        letra = ""
                        nombre = ""
                    }, shape = RoundedCornerShape(0), modifier = Modifier.width(280.dp)

                ) {
                    IconButton(
                        onClick = { /*TODO*/ }, modifier = Modifier
                            .size(24.dp)
                            .width(100.dp)

                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Guardar")
                    }
                }
                Button(
                    onClick = {
                        var intent = Intent(context, ListarCancionesActivity::class.java)
                        ContextCompat.startActivity(context, intent, null)
                    },
                    shape = RoundedCornerShape(0),
                    modifier = Modifier.width(280.dp),

                    ) {
                    Text(
                        text = "¡Mis Canciones!",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun PreviewGuardarLetraScreen() {
        GuardarLetraActivity().guardarLetraScreen(PaddingValues())
    }

}