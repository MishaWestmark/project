package ru.westmark.app42.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import ru.westmark.app42.R
import ru.westmark.app42.navigation.Screens
import ru.westmark.app42.screens.view_models.EmployerViewModel

@Composable
fun SplashScreenNotConnected(
    navHostController: NavHostController,
    employerViewModel: EmployerViewModel
) {
    val isConnection = employerViewModel.networkLiveData.observeAsState().value
    val context = LocalContext.current
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Blue) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.logo_main_notconnected),
                    contentDescription = null
                )
                Text(text = "Похоже проблемы с сетью", color = Color.White)
                Button(onClick = {
                    if (isConnection == true) {
                        navHostController.navigate(Screens.SplashScreen.route)
                    } else {
                        Toast.makeText(context, "No Internet connection", Toast.LENGTH_LONG).show()
                    }
                }) {
                    Text(text = "Попробовать еще раз")
                }
            }
        }
    }
}