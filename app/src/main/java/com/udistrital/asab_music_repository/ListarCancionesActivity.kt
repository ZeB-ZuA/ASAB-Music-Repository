package com.udistrital.asab_music_repository

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.udistrital.asab_music_repository.entity.Cancion
import com.udistrital.asab_music_repository.repository.DataBaseHelper
import com.udistrital.asab_music_repository.service.CancionService


class ListarCancionesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            mainMenu("Listar Canciones") { padding -> listarCancionesScreen(padding) }

        }


    }

    @Composable
    fun listarCancionesScreen(padding: PaddingValues) {
        val context = LocalContext.current
        val cancionService = CancionService(DataBaseHelper(context))
        var canciones by remember { mutableStateOf(cancionService.getAllCanciones()) }
        var openDialog by remember { mutableStateOf(false) }
        var selectedCancion by remember { mutableStateOf(Cancion(0, "", "", "")) }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (50).dp)

        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(canciones) { cancion ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                selectedCancion = cancion
                                openDialog = true
                            }, elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "ID: ${cancion.id}", modifier = Modifier.weight(1f))
                            Text(text = "Nombre: ${cancion.nombre}", modifier = Modifier.weight(3f))
                            Text(text = "Fecha: ${cancion.fecha}\n", modifier = Modifier.weight(3f))
                            IconButton(onClick = {
                                cancionService.delete(cancion.id)
                                canciones = cancionService.getAllCanciones()
                                Toast.makeText(context, "Canción eliminada", Toast.LENGTH_SHORT)
                                    .show()
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                            }
                        }
                    }

                }

            }
        }

        if (openDialog) {
            AlertDialog(onDismissRequest = { openDialog = false },
                title = { Text(text = selectedCancion.nombre) },
                text = { Text(text = selectedCancion.letra) },
                confirmButton = {
                    Button(onClick = { openDialog = false }) {
                        Text("Cerrar")
                    }
                })
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun listarCancionesScreen() {
        ListarCancionesActivity().listarCancionesScreen(PaddingValues())
    }
}