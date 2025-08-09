package com.example.rickandmorty.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.rickandmorty.presentation.screens.characterDetail.CharacterDetailScreen
import com.example.rickandmorty.presentation.screens.characterDetail.CharacterDetailViewModel
import com.example.rickandmorty.presentation.screens.home.HomeScreen
import com.example.rickandmorty.presentation.screens.home.HomeViewModel
import com.example.rickandmorty.presentation.screens.home.components.FilterScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AppNavHost(navController: NavHostController){

    val homeViewModel = koinViewModel<HomeViewModel>()
    NavHost(navController = navController, "home_screen"){
        composable("home_screen") {
            HomeScreen(navController, homeViewModel)
        }

        composable("filter_screen") {
            FilterScreen(navController, homeViewModel)
        }

        composable("detail_screen/{characterId}",) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull() ?: -1

            CharacterDetailScreen(navController, characterId = characterId)
        }
    }
}