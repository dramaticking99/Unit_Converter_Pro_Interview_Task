package com.example.unitconverterpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.unitconverterpro.ui.ConverterScreen
import com.example.unitconverterpro.ui.theme.UnitConverterProTheme

import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.unitconverterpro.ui.HistoryScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UnitConverterProTheme {
                Surface(color = MaterialTheme.colorScheme.background) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "converter"
                    ) {
                        composable("converter") {
                            ConverterScreen(navController)
                        }
                        composable("history") {
                            HistoryScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
