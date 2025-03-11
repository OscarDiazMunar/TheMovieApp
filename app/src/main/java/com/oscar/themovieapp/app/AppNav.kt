package com.oscar.themovieapp.app

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oscar.themovieapp.commons.NavigationScreen
import com.oscar.themovieapp.presentation.list.ListMoviesScreen

@Composable
fun App(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = NavigationScreen.Home.route){
        composable(
            NavigationScreen.Home.route
        ){
            ListMoviesScreen(hiltViewModel(), navController )
        }
    }
}