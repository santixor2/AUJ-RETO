package com.santig.auj_reto.view.navigation

sealed class Home(val route : String){
    data object HomeScreen : Home(
        route = "home_screen"
    )
    data object TaskScreen : Home(
        route = "task_screen"
    )
}