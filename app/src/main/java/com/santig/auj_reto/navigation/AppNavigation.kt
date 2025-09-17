package com.santig.auj_reto.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = Home.HomeScreen.route
    ) {
        HomeRegisterGraph(navController = navController)
    }
}