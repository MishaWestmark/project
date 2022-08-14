package ru.westmark.app42.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.westmark.app42.R
import ru.westmark.app42.navigation.Screens
import ru.westmark.app42.screens.view_models.EmployerViewModel

@Composable
fun SplashScreen(navHostController: NavHostController, employerViewModel: EmployerViewModel) {
    val coroutine = rememberCoroutineScope()
    val isNetworkAvailable = employerViewModel.networkLiveData.observeAsState().value!!
    Log.d("SplashScreen", isNetworkAvailable.toString())
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Blue) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.logo_main),
                    contentDescription = null
                )
                Text(text = "Работодатели\n3000", color = Color.White, fontSize = 30.sp)
                CircularProgressIndicator(color = Color.White, strokeWidth = 3.dp)
            }
        }
        coroutine.launch {
            delay(3000)
            if (isNetworkAvailable) {
                navHostController.navigate(Screens.MainScreen.route) {
                    popUpTo(Screens.SplashScreen.route) {
                        inclusive = true
                    }
                }
                employerViewModel.getEmployersList(name = null, isVacancies = null, type = null)
            } else {
                navHostController.navigate(Screens.SplashScreenNoConnection.route)
            }
        }
    }
}
