package com.udistrital.asab_music_repository

import android.R
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.SoundPool
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.sqrt


class MaracaActivity : AppCompatActivity(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var soundPool: SoundPool? = null
    private var maracaSoundId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        soundPool = SoundPool.Builder().setMaxStreams(1).build()
        maracaSoundId = soundPool!!.load(this, R.raw.shortbass, 1)


        // Registrar el listener del sensor
        sensorManager!!.registerListener(
            this,
            sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
        setContent {
            TopAppBar()
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppBar() {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            "Maracas",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            var intent = Intent(this@MaracaActivity, PracticarMaracaActivity::class.java)
                            startActivity( intent, null)
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },

                    scrollBehavior = scrollBehavior,
                )
            },
        ) { innerPadding ->
            MaracaScreen(innerPadding)
        }
    }
    @Composable
    fun MaracaScreen(innerPadding :PaddingValues) {
        val imgMaraca = painterResource(R.drawable.maracas)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text("¡Mueve las maracas!",
                modifier = Modifier.padding(24.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Image(
                painter = imgMaraca,
                contentDescription = null,
                modifier = Modifier.size(500.dp)
            )
        }

    }

    override fun onSensorChanged(event: SensorEvent?) {
        val x = event!!.values[0]
        val y = event.values[1]
        val z = event.values[2]

        // Calcular la intensidad del movimiento
        val magnitude = sqrt((x * x + y * y + z * z).toDouble()).toFloat()


        // Ajustar el volumen del sonido
        soundPool?.setVolume(maracaSoundId, magnitude / 10.0f, magnitude / 10.0f,)

        // Reproducir el sonido
        soundPool?.play(maracaSoundId, 1.0f, 1.0f, 0, 0, 1.0f)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }


}

