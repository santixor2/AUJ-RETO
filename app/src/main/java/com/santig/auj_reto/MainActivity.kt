package com.santig.auj_reto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.santig.auj_reto.view.navigation.AppNavigation
import com.santig.auj_reto.ui.theme.AUJ_RETOTheme
import com.santig.auj_reto.utils.TaskManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TaskManager.init(this)
        enableEdgeToEdge()
        setContent {
            AUJ_RETOTheme {
                AppNavigation()
            }
        }
    }
}
