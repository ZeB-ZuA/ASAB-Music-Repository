package com.udistrital.asab_music_repository

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat

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
fun mainMenu(title : String, content: @Composable (PaddingValues) -> Unit) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val options = listOf(
        "Guardar Letra",
        "Practicar Tambor",
        "Practicar maraca"
    )
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }

                        Text(
                            text = title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(onClick = {}, enabled = false) {
                            Icon(Icons.Default.Menu, contentDescription = null)
                        }
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }) {
                        options.forEach { option ->
                            DropdownMenuItem(text = { Text(text = option) }, onClick = {
                                if (option == "Practicar maraca") {
                                    var intent =
                                        Intent(context, PracticarMaracaActivity::class.java)
                                    ContextCompat.startActivity(context, intent, null)
                                }
                                if (option == "Practicar Tambor") {
                                    var intent =
                                        Intent(context, PracticarTamborActivity::class.java)
                                    ContextCompat.startActivity(context, intent, null)
                                }
                                if (option == "Guardar Letra") {
                                    var intent = Intent(context, GuardarLetraActivity::class.java)
                                    ContextCompat.startActivity(context, intent, null)
                                }

                            })
                        }
                    }
                }
            )
        },
        content = content
    )
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
