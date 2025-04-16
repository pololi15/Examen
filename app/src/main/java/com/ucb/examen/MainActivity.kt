// app/MainActivity.kt
package com.ucb.examen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ucb.examen.navigation.AppNavigation
//import com.ucb.app.navigation.AppNavigation
//import com.ucb.app.ui.theme.UcbTheme
import com.ucb.examen.ui.theme.ExamenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExamenTheme {
                AppNavigation()
            }
        }
    }
}
