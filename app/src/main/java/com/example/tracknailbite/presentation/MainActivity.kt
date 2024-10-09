package com.example.tracknailbite.presentation

import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.tracknailbite.R
import com.example.tracknailbite.presentation.theme.TrackNailBiteTheme

class MainActivity : ComponentActivity() {
    // Declare the vibrator here
    private lateinit var vibrator: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        // Initialize the VibratorManager and get the default vibrator
        val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibrator = vibratorManager.defaultVibrator

        setContent {
            WearApp { vibrateWatch() } // Pass the vibrateWatch function as a lambda
        }
    }

    // Function to make the watch vibrate
    private fun vibrateWatch() {
        // Check if vibration is supported
        if (vibrator.hasVibrator()) {
            // Create a vibration pattern, adjust the duration as needed
            val vibrationEffect = VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(vibrationEffect)
        }
    }
}

@Composable
fun WearApp(onVibrateClick: () -> Unit) {
    TrackNailBiteTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            TimeText()
            // Add the button that triggers vibration
            Button(
                onClick = { onVibrateClick() } // Call the vibrate function when button is clicked
            ) {
                Text("Vibrate")
            }
        }
    }
}



@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp(onVibrateClick = {})
}