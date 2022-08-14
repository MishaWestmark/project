package ru.westmark.app42.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import ru.westmark.app42.data.DataStoragePref
import ru.westmark.app42.navigation.Screens
import ru.westmark.app42.screens.view_models.EmployerViewModel

@Composable
fun FilterScreen(employerViewModel: EmployerViewModel, navHostController: NavHostController) {
    val coroutine = rememberCoroutineScope()
    val context = LocalContext.current
    val dataStore = DataStoragePref(context)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val name = dataStore.getName.collectAsState(initial = null).value
        var searchText by remember {
            mutableStateOf(name)
        }
        var isCheckedCompany by rememberSaveable {
            mutableStateOf(false)
        }
        var isCheckedAgency by rememberSaveable {
            mutableStateOf(false)
        }
        var isCheckedRecruiter by rememberSaveable {
            mutableStateOf(false)
        }
        var isCheckedDirector by rememberSaveable {
            mutableStateOf(false)
        }
        val isVacancies = dataStore.getInfo.collectAsState(initial = true).value ?: false

        var isCheckedVacancies by rememberSaveable {
            mutableStateOf(isVacancies)
        }

        Column() {
            FirstRow(navHostController = navHostController)
            SearchFilterEditText(
                searchName = searchText ?: name.toString(),
                onChange = {
                    searchText = it
                    coroutine.launch {
                        dataStore.saveName(searchText!!)
                    }
                }
            )
            Row() {
                Text(text = "Тип работодателя")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                CheckBoxButton(
                    onClick = {
                        isCheckedCompany = !isCheckedCompany
                        coroutine.launch {
                            dataStore.saveType("company")
                            dataStore.isCheckedCompany(isCheckedCompany)
                        }
                    },
                    isChecked = dataStore.getCheckCompany.collectAsState(initial = false).value,
                    name = "Прямой работодатель"
                )
                CheckBoxButton(
                    onClick = {
                        isCheckedAgency = !isCheckedAgency
                        coroutine.launch {
                            dataStore.saveType("agency")
                            dataStore.isCheckedAgency(isCheckedAgency)
                        }
                    },
                    isChecked = dataStore.getCheckAgency.collectAsState(initial = false).value,
                    name = "Кадровое агентство"
                )
                CheckBoxButton(
                    onClick = {
                        isCheckedDirector = !isCheckedDirector
                        coroutine.launch {
                            dataStore.saveType("project_director")
                            dataStore.isCheckedDirector(isCheckedDirector)
                        }
                    },
                    isChecked = dataStore.getCheckDirector.collectAsState(initial = false).value,
                    name = "Руководитель проекта"
                )
                CheckBoxButton(
                    onClick = {
                        isCheckedRecruiter = !isCheckedRecruiter
                        coroutine.launch {
                            dataStore.saveType("private_recruiter")
                            dataStore.isCheckedRecruiter(isCheckedRecruiter)
                        }
                    },
                    isChecked = dataStore.getCheckRecruiter.collectAsState(initial = isCheckedVacancies).value,
                    name = "Частный рекрутер"
                )
            }
            Row() {
                Text(text = "Наличие вакансий у работодателя")
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier,
                    shape = RoundedCornerShape(20.dp),
                    elevation = ButtonDefaults.elevation(defaultElevation = 8.dp),
                    onClick = {
                        isCheckedVacancies = !isCheckedVacancies
                        coroutine.launch {
                            dataStore.saveInfo(isCheckedVacancies)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = if (isVacancies) Color.Gray else Color.White)
                ) {
                    Text(text = "Есть Открытые")
                }
                Button(
                    modifier = Modifier,
                    shape = RoundedCornerShape(20.dp),
                    elevation = ButtonDefaults.elevation(defaultElevation = 8.dp),
                    onClick = {
                        isCheckedVacancies = !isCheckedVacancies
                        coroutine.launch {
                            dataStore.saveInfo(isCheckedVacancies)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = if (isVacancies) Color.White else Color.Gray)
                ) {
                    Text(text = "Все компании")
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), contentAlignment = Alignment.Center
        ) {
            val type = dataStore.getType.collectAsState(initial = "").value
            Button(onClick = {
                coroutine.launch {
                    employerViewModel.getEmployersList(name, isVacancies, type)
                    navHostController.navigate(Screens.MainScreen.route)
                }
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Показать")
            }
        }
    }
}

@Composable
fun SearchFilterEditText(
    searchName: String,
    onChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchName,
            shape = RoundedCornerShape(20.dp),
            onValueChange = { onChange(it) },
            label = {
                Row() {
                    Image(imageVector = Icons.Filled.Search, contentDescription = "search")
                    Text(text = "Компания, ключевые слова")
                }
            })
    }
}

@Composable
fun CheckBoxButton(
    onClick: () -> Unit, isChecked: Boolean?, name: String
) {
    Button(
        modifier = Modifier,
        shape = RoundedCornerShape(20.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 8.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = if (isChecked == true) Color.Gray else Color.White)
    ) {
        Text(text = name)
    }
}

@Composable
fun FirstRow(navHostController: NavHostController) {
    val context = LocalContext.current
    val dataStore = DataStoragePref(context)
    val coroutine = rememberCoroutineScope()
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Button(
            onClick = { navHostController.navigate(Screens.MainScreen.route) },
            modifier = Modifier.size(45.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            shape = RoundedCornerShape(20.dp)
        ) {
            Image(
                imageVector = Icons.Filled.Close,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                contentScale = ContentScale.None
            )
        }
        Text(text = "Фильтры", fontWeight = FontWeight.Bold, fontSize = 40.sp)
        Button(
            onClick = {
                coroutine.launch {
                    dataStore.isCheckedRecruiter(false)
                    dataStore.isCheckedAgency(false)
                    dataStore.saveInfo(false)
                    dataStore.isCheckedDirector(false)
                    dataStore.isCheckedCompany(false)
                    dataStore.saveName(name = " ")
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(text = "Сбросить")
        }
    }
}

