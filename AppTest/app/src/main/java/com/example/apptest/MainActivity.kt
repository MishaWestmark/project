package com.example.apptest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apptest.ui.theme.AppTestTheme

class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val card = viewModel.cardInfoStateFlow.collectAsState().value ?: CardInformation()
            Log.e("Main", card.toString())
            AppTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(card, viewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreen(cardInformation: CardInformation, viewModel: MainViewModel) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row() {
                SearchEditText(viewModel)
            }
            Row {
                BankCard(cardInformation)
            }
        }
    }
}

@Composable
fun CardText(title: String, value: String?) {

    Column() {
        Text(text = title, fontWeight = FontWeight.Bold)
        Text(text = value.toString())
    }
}

@Composable
fun BankInfo(title: String, siteLink: String, phone: String) {
    Column() {
        Text(text = "BANK", fontWeight = FontWeight.Bold)
        Text(text = title)
        Text(text = siteLink)
        Text(text = phone)
    }
}

@Composable
fun BankCard(cardInformation: CardInformation) {
    Card() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CardText(title = "Scheme", value = cardInformation.scheme)
                CardText(title = "Type", value = cardInformation.type)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CardText(title = "Brand", value = cardInformation.brand)
                CardText(title = "Prepaid", value = if (cardInformation.prepaid == true) "Yes" else "No" )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CardText(title = "Card Number (length)", value = cardInformation.number?.length)
                CardText(title = "Country", value = cardInformation.country?.name)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp)
            ) {
                BankInfo(title = cardInformation.bank?.name.toString(), siteLink = cardInformation.bank?.url.toString(), phone = cardInformation.bank?.phone.toString())
            }
        }
    }
}

@Composable
fun SearchEditText(viewModel: MainViewModel) {
    var searchText by  remember {
        mutableStateOf("")
    }
    if (searchText.length == 8) viewModel.getCardInfo(searchText)
    TextField(
        value = searchText,
        onValueChange = { searchText = it },
        shape = RoundedCornerShape(20.dp),
        label = {
            Row() {
                Image(imageVector = Icons.Filled.Search, contentDescription = "search")
                Text(text = "Номер карты")
            }
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen(CardInformation(), MainViewModel())
}



