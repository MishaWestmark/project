package com.westmark.examplecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.flow.flow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column() {
                Greeting(name = "Mike")
                Row() {
                    ImageButton()
                    TextImageButton(text = "Wes")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello world $name")
}

@Composable
fun ImageButton() {
    Box(modifier = Modifier.clickable {

    }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_android_black),
            contentDescription = "android image black"
        )
    }
}
@Composable
fun TextImageButton(text: String) {
    Box(modifier = Modifier.clickable(onClick = { })) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painterResource(id = R.drawable.ic_android_black),
                contentDescription = "")
            Text(text = text)
        }
    }
}