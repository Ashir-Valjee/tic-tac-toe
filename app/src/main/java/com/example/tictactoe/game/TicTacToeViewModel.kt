package com.example.tictactoe.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class TicTacToeViewModel : ViewModel() {

    // Names
    var playerOne by mutableStateOf("Player 1")
    var playerTwo by mutableStateOf("Player 2")

    // Game state
    var boardColors by mutableStateOf(List(3) { MutableList(3) { Color.LightGray } })
        private set

    var currentPlayerNumber by mutableStateOf(1)
        private set

    var winningPlayerNumber by mutableStateOf<Int?>(null)
        private set

    var isDraw by mutableStateOf(false)

    fun setPlayers(player1: String, player2: String) {
        playerOne = player1
        playerTwo = player2
    }

    fun resetGame() {
        boardColors = List(3) { MutableList(3) { Color.LightGray } }
        currentPlayerNumber = 1
        winningPlayerNumber = null
        isDraw = false
    }

    // Helpers the UI can use
    fun placeColor(row: Int, col: Int, color: Color) {
        val copy = boardColors.map { it.toMutableList() }
        copy[row][col] = color
        boardColors = copy
    }

    fun setWinner(playerNumber: Int?) { winningPlayerNumber = playerNumber }

    fun togglePlayer() {
        currentPlayerNumber = if (currentPlayerNumber == 1) 2 else 1
    }
}