package com.santig.auj_reto

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.santig.auj_reto.navigation.Home

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val task by viewModel.tasks.collectAsState()

    LaunchedEffect(Unit){
        viewModel.loadTasks()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleHome()
            Spacer(modifier = Modifier.height(16.dp))
            if(task.isEmpty()){
                EmptyTask()
            }
            LazyColumn{
                items(task){ tasks ->
                    TaskCard(
                        task = tasks,
                        onCheckChanged = {
                            viewModel.markTaskCompleted(taskId = it)
                        }
                    )
                }
            }
        }
        AddTask(
            modifier = Modifier.align(Alignment.BottomEnd),
            goTo = {navController.navigate(Home.TaskScreen.route)}
        )
    }
}

@Composable
private fun EmptyTask() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "SIN TAREAS", fontSize = 18.sp)
    }
}

@Composable
fun AddTask(modifier: Modifier,goTo: () -> Unit) {
    FloatingActionButton(
        onClick = {goTo()},
        containerColor = Color.Blue,
        modifier = modifier
            .padding(20.dp)
            .clip(CircleShape)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Añadir",
            tint = Color.White
        )
    }
}

@Composable
fun TitleHome() {
    Text(
        text = "Lista de Tareas",
        fontSize = 26.sp,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 12.dp),
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun TaskCard(
    task: Task,
    onCheckChanged: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp, vertical = 6.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(0.5f)),
        onClick = {}
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Titulo : ${task.title}",
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, top = 4.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Descripcion : ${task.description}",
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, top = 2.dp)
                )
            }
            if (!task.completed){
                Checkbox(
                    checked = task.completed,
                    onCheckedChange = {onCheckChanged(task.id)}
                )
            }else{
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "",
                    tint = Color.Green
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
//    HomeScreen()
}