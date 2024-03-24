package com.udistrital.asab_music_repository

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mainMenu()
        }
    }
}


@Composable
fun mainMenu() {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val options = listOf(
        "Guardar Letra",
        "Practicar Tambor",
        "Practicar maraca"

    )
    Button(onClick = { expanded = !expanded }) {
        Text(text = "Menu")
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }) {
        options.forEach { option ->
            DropdownMenuItem(text = { Text(text = option) }, onClick = {
                if (option == "Practicar maraca") {
                    var intent = Intent(context, PracticarMaracaActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
                if (option == "Practicar Tambor"){
                    var intent = Intent(context, PracticarTamborActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
                if (option == "Guardar Letra"){
                    var intent = Intent(context, GuardarLetraActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }

            })
        }
    }

}


@Preview
@Composable
fun mainMenuView(){
    mainMenu()
}
