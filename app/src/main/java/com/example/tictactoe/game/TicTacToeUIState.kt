package com.example.tictactoe.game

data class TicTacToeUiState(
    val playerOneName: String = "Player 1",
    val playerTwoName: String = "Player 2",
    val board: List<List<Int>> = List(3) { List(3) { 0 } },
    val currentPlayer: Int = 1,
    val winner: Int? = null,
    val isDraw: Boolean = false,
    val status: String = "Player 1's turn",
    val winningCells: Set<Pair<Int, Int>> = emptySet()
)