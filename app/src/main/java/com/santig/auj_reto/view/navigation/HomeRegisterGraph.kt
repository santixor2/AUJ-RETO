package com.santig.auj_reto.view.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.santig.auj_reto.view.ui.AddTaskScreen
import com.santig.auj_reto.view.ui.HomeScreen

fun NavGraphBuilder.HomeRegisterGraph(
    navController: NavHostController
){
    composable(
        route = Home.HomeScreen.route
    ){
        HomeScreen(navController = navController)
    }
    composable(
        route = Home.TaskScreen.route
    ){
        AddTaskScreen(navController = navController)
    }
}