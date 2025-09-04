package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.tictactoe.navigation.App
import com.example.tictactoe.ui.theme.TicTacToeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        App()
                    }
                }
            }
        }
    }
}




//@Preview(showBackground = true)
//@Composable
//fun TicTacToeGamePreview() {
//    TicTacToeTheme {
//        TicTacToeGame()
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    TicTacToeTheme {
//        HomeScreen()
//    }
//}

