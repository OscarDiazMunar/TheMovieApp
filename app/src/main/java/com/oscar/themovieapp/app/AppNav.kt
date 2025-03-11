package com.oscar.themovieapp.app

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.oscar.themovieapp.commons.NavigationScreen
import com.oscar.themovieapp.presentation.detail.DetailMovieScreen
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

        composable(NavigationScreen.Detail.route + "/{itemId}",
            arguments = listOf(navArgument("itemId"){
                type = NavType.StringType
            })){
            DetailMovieScreen(
                id = it.arguments?.getString("itemId"),
                viewModel = hiltViewModel(),
                navController = navController)
        }
    }
}