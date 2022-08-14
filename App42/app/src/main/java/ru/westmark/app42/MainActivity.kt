package ru.westmark.app42

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.westmark.app42.navigation.SetupNavHost
import ru.westmark.app42.screens.view_models.EmployerViewModel
import ru.westmark.app42.ui.theme.App42Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val employerViewModel: EmployerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            App42Theme {
                SetupNavHost(
                    navHostController = navController,
                    employerViewModel = employerViewModel
                )
            }
        }
    }
}

