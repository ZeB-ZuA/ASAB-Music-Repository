package com.udistrital.asab_music_repository

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.udistrital.asab_music_repository.controller.GuardarLetraActivity
import com.udistrital.asab_music_repository.entity.Cancion
import com.udistrital.asab_music_repository.repository.DataBaseHelper
import com.udistrital.asab_music_repository.service.CancionService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mainMenu("Asab Music Repository") { padding -> ScrollContent(padding) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mainMenu(title: String, content: @Composable (PaddingValues) -> Unit) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var searchOpen by remember { mutableStateOf(false) }
    var searchTextField by remember { mutableStateOf("") }
    var foundCancion by remember { mutableStateOf<Cancion?>(null) }
    val cancionService = CancionService(DataBaseHelper(context))
    val options = listOf(
        "Guardar Letra", "Practicar tambor", "Practicar maraca"
    )
    Scaffold(
        topBar = {
            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ), title = {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                    if (!searchOpen) {
                        Text(
                            text = title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        TextField(
                            value = searchTextField,
                            onValueChange = { searchTextField = it },
                            modifier = Modifier.weight(1f),
                            placeholder = { Text("Buscar canción") },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = {
                                foundCancion = cancionService.findByNombre(searchTextField)
                                if (foundCancion == null) {
                                    Toast.makeText(
                                        context,
                                        "Cancion no encontrada",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                searchTextField =""
                                searchOpen = false
                            })
                        )

                    }


                    IconButton(onClick = { searchOpen = !searchOpen }) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    }
                }

                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            modifier = Modifier
                                .padding(4.dp),


                            text = { Text(text = option) }, onClick = {
                            if (option == "Practicar maraca") {
                                var intent = Intent(context, PracticarMaracaActivity::class.java)
                                ContextCompat.startActivity(context, intent, null)
                            }
                            if (option == "Practicar tambor") {
                                var intent = Intent(context, PracticarTamborActivity::class.java)
                                ContextCompat.startActivity(context, intent, null)
                            }
                            if (option == "Guardar Letra") {
                                var intent = Intent(context, GuardarLetraActivity::class.java)
                                ContextCompat.startActivity(context, intent, null)
                            }

                        })
                    }
                }
            })
        }, content = content
    )
    if (foundCancion != null) {
        AlertDialog(onDismissRequest = { foundCancion = null },
            title = { Text(text = "Nombre: ${foundCancion?.nombre}") },
            text = {
                Text(
                    text = "ID: ${foundCancion?.id}\n" +
                            "Fecha de Registro: ${foundCancion?.fecha}\n\nLetra: \n\n" +
                            foundCancion?.letra
                )
            },
            confirmButton = {
                Button(onClick = { foundCancion = null }) {
                    Text("Cerrar")
                }
            })
    }


}


@Preview
@Composable
fun mainMenuView() {
    mainMenu("Asab Music Repository", { padding -> ScrollContent(padding) })
}

@Composable
fun ScrollContent(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Aqui va algun contenido")
    }
}
