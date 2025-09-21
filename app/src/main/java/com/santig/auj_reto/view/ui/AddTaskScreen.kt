package com.santig.auj_reto.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.santig.auj_reto.domain.Task
import com.santig.auj_reto.view.presentation.HomeViewModel
import com.santig.auj_reto.view.states.UiState

@Composable
fun AddTaskScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val data by viewModel.taskData.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TaskTitle()
        Spacer(modifier = Modifier.height(26.dp))
        TitleTextField(task = data, viewModel = viewModel)
        Spacer(modifier = Modifier.height(8.dp))
        DescriptionTextField(task = data, viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        AddTaskButton(
            goTo = {
                viewModel.addTask(task = data)
                viewModel.uiState(uiStates = uiState.copy(loading = false))
                navController.popBackStack()
            },
            uiState = uiState
        )
        Spacer(modifier = Modifier.height(42.dp))
        IconBack(back = {navController.popBackStack()})
    }
}

@Composable
fun TaskTitle(){
    Text(
        text = "Agrega una Tarea",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun TitleTextField(
    task: Task,
    viewModel: HomeViewModel
){
    OutlinedTextField(
        value = task.title ?: "",
        onValueChange = {
            if (it.length <= 25) {
                viewModel.taskData(task = task.copy(title = it))
            }
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 3.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(10.dp)
            ),
        placeholder = { Text(text = "Titulo", fontSize = 13.sp) },
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
fun DescriptionTextField(
    task: Task,
    viewModel: HomeViewModel
){
    OutlinedTextField(
        value = task.description ?: "",
        onValueChange = {
            if (it.length <= 55) {
                viewModel.taskData(task = task.copy(description = it))
            }
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 3.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(10.dp)
            ),
        placeholder = { Text(text = "Descripcion", fontSize = 13.sp) },
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
fun AddTaskButton(
    goTo: () -> Unit,
    uiState: UiState
) {
    if (uiState.loading){
        CircularProgressIndicator(color = Color.Gray, modifier = Modifier.heightIn(max = 25.dp).widthIn(max = 30.dp))
    }else{
        Button(
            onClick = { goTo() },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 45.dp)
                .padding(horizontal = 24.dp),
            colors = ButtonDefaults.buttonColors(Color.Blue),
            enabled = uiState.enabled
        ) {
            Text(
                text = "Guardar Tarea",
                fontSize = 13.sp,
                color = Color.White
            )
        }
    }
}
@Composable
fun IconBack(back : () -> Unit){
    IconButton(
        onClick = {back()},
        modifier = Modifier.padding(top = 8.dp, start = 4.dp)
    ){
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = ""
        )
    }
}