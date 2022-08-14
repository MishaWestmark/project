package ru.westmark.app42.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch
import ru.westmark.app42.R
import ru.westmark.app42.data.DataStoragePref
import ru.westmark.app42.data.Employers
import ru.westmark.app42.navigation.Screens
import ru.westmark.app42.screens.view_models.EmployerViewModel

@Composable
fun MainScreen(employerViewModel: EmployerViewModel, navHostController: NavHostController) {
    val listEmployers = employerViewModel.listStateFlow.collectAsState().value
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            SearchEditText(
                navHostController = navHostController,
                employerViewModel = employerViewModel
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Работодатели", fontWeight = FontWeight.Bold, fontSize = 32.sp)
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                listEmployers.forEach { employer ->
                    EmployerCard(employer)
                }
            }
        }
    }
}

@Composable
fun EmployerCard(employer: Employers) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .clickable(onClick = {}),
        shape = RoundedCornerShape(16.dp),
        elevation = 12.dp
    ) {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = employer.name.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                    Text(text = "open vacancy: ${employer.openVacancies}")
                }
                Surface(
                    modifier = Modifier.size(100.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    GlideImage(imageModel = employer.logo_urls?.middle, placeHolder = null)
                }

            }
        }
    }
}

@Composable
fun SearchEditText(navHostController: NavHostController, employerViewModel: EmployerViewModel) {
    var searchText by remember {
        mutableStateOf("")
    }
    val coroutine = rememberCoroutineScope()
    val context = LocalContext.current
    val dataStore = DataStoragePref(context)
    val name = dataStore.getName.collectAsState(initial = null).value
    val isVacancies = dataStore.getInfo.collectAsState(initial = false).value
    val type = dataStore.getType.collectAsState(initial = null).value
    val isCompany = dataStore.getCheckCompany.collectAsState(initial = null).value
    val isAgency = dataStore.getCheckAgency.collectAsState(initial = null).value
    val isRecruiter = dataStore.getCheckRecruiter.collectAsState(initial = null).value
    val isDirector = dataStore.getCheckDirector.collectAsState(initial = null).value
    coroutine.launch {
        dataStore.saveName(searchText)
        employerViewModel.getEmployersList(name = name, isVacancies = isVacancies, type = type)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = name.toString(),
            onValueChange = { searchText = it },
            shape = RoundedCornerShape(20.dp),
            label = {
                Row() {
                    Image(imageVector = Icons.Filled.Search, contentDescription = "search")
                    Text(text = "Компания, ключевые слова")
                }
            })
        Button(
            onClick = { navHostController.navigate(Screens.FilterScreen.route) },
            modifier = Modifier.size(55.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (isAgency == true || isCompany == true ||
                    isDirector == true || isRecruiter == true || isVacancies == true
                ) Color.Blue else Color.White
            )
        ) {
            Image(painter = painterResource(id = R.drawable.icon_filter), contentDescription = null)
        }
    }
}

@Preview
@Composable
fun PreviewCardEmployer() {
    EmployerCard(employer = Employers())
}