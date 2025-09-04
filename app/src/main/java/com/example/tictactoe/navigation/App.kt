package com.example.tictactoe.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tictactoe.screens.HomeScreen
import com.example.tictactoe.screens.TicTacToeGame

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        // Home screen route
        composable("home") {
            HomeScreen { playerOneName, playerTwoName ->
                val encodedPlayerOne = Uri.encode(playerOneName)
                val encodedPlayerTwo = Uri.encode(playerTwoName)
                navController.navigate("game/$encodedPlayerOne/$encodedPlayerTwo")
            }
        }

        // Game screen route with arguments
        composable(
            route = "game/{playerOne}/{playerTwo}",
            arguments = listOf(
                navArgument("playerOne") { type = NavType.StringType },
                navArgument("playerTwo") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val playerOne = Uri.decode(backStackEntry.arguments?.getString("playerOne") ?: "Player 1")
            val playerTwo = Uri.decode(backStackEntry.arguments?.getString("playerTwo") ?: "Player 2")

            TicTacToeGame(
                playerOne = playerOne,
                playerTwo = playerTwo
            )
        }
    }
}