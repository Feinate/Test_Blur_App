package com.mhdncb.testblurapp.presentation.screen.mainActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mhdncb.testblurapp.presentation.navigation.Navigation
import com.mhdncb.testblurapp.ui.theme.TestBlurAppTheme
import com.mhdncb.testblurapp.ui.theme.isLight
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {

            val systemUiController = rememberSystemUiController()

            val isLight = MaterialTheme.colorScheme.isLight()

            DisposableEffect(systemUiController, isLight) {

                systemUiController.setStatusBarColor(
                    color = Color.Transparent,
                    darkIcons = isLight
                )

                systemUiController.setNavigationBarColor(
                    color = Color.Transparent,
                    darkIcons = isLight
                )

                onDispose {}
            }

            TestBlurAppTheme {
                Navigation()
            }
        }
    }
}