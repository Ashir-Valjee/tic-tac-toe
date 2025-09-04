package com.example.tictactoe.game

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.tictactoe.R
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TicTacToeBoard(
    modifier: Modifier= Modifier,
    playerOne: String,
    playerTwo: String,
    onStatusChange: (String) -> Unit={}
) {

    val viewModel: TicTacToeViewModel = viewModel()

    val playerOneColor = colorResource(id = R.color.player_one_color)
    val playerTwoColor = colorResource(id = R.color.player_two_color)



    LaunchedEffect(playerOne, playerTwo) {
        viewModel.setPlayers(playerOne, playerTwo)
        onStatusChange("$playerOne's turn")
    }
    val board = viewModel.boardColors
    val currentPlayer = viewModel.currentPlayerNumber
    val winningPlayer = viewModel.winningPlayerNumber
    val isDraw = viewModel.isDraw

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly)
    {
        repeat(3) {
                row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                repeat(3) {
                        col ->
                    TicTacToeCell(
                        row = row,
                        col = col,
                        color = board[row][col],
                        onClick = {

//                          Stop the game if there is the winner or if it's a draw
                            if (winningPlayer != null || isDraw) return@TicTacToeCell

                            if (board[row][col] == Color.LightGray) {
                                val colorToPlace =
                                    if (currentPlayer == 1) playerOneColor else playerTwoColor
                                viewModel.placeColor(row, col, colorToPlace)
                                val updatedBoard = viewModel.boardColors

                                if (hasWinner(updatedBoard)) {
                                    viewModel.setWinner(currentPlayer)
                                    val winnerName =
                                        if (currentPlayer == 1) viewModel.playerOne
                                        else viewModel.playerTwo
                                    onStatusChange("The winner is $winnerName")
                                } else {
//                                  Check if there are no gray cells
                                    val isDrawNow = updatedBoard.all {row -> row.all {it != Color.LightGray}}
                                    if (isDrawNow) {
                                        viewModel.isDraw = true
                                        onStatusChange("It's a draw!")
                                    }
                                    if (!isDrawNow) {
                                        viewModel.togglePlayer()

                                        val nextName = if (viewModel.currentPlayerNumber == 1) viewModel.playerOne
                                        else viewModel.playerTwo
                                        onStatusChange("$nextName's turn")
                                    }
                                }

                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TicTacToeCell(
    row: Int,
    col: Int,
    color: Color,
    onClick: ()-> Unit,
    modifier: Modifier= Modifier) {

    val animatedColor by animateColorAsState(
        targetValue = color,
        animationSpec = tween(durationMillis = 300),
        label = "cellColor"
    )

    val targetScale = if (color != Color.LightGray) 1f else 0.9f
    val animatedScale by animateFloatAsState(
        targetValue = targetScale,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "cellScale"
    )
    Box(
        modifier = Modifier
            .size(100.dp)
            .scale(animatedScale)
            .clip(RoundedCornerShape(8.dp))
            .background(animatedColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {}

}



fun hasWinner(board: List<List<Color>>): Boolean {
//    Rows
    for (row in 0 ..  2) {
        val cellOne = board[row][0]
        val cellTwo = board[row][1]
        val cellThree = board[row][2]

        if (cellOne != Color.LightGray && cellOne == cellTwo && cellOne == cellThree) {
            return true
        }
    }
//  Columns
    for (col in 0 ..  2) {
        val cellOne = board[0][col]
        val cellTwo = board[1][col]
        val cellThree = board[2][col]

        if (cellOne != Color.LightGray && cellOne == cellTwo && cellOne == cellThree) {
            return true
        }
    }
//  Diagonals
    val center = board[1][1]
    if (center != Color.LightGray) {
        if (board[0][0] == center && board[2][2] == center) return true
        if (board[0][2] == center && board[2][0] == center) return true
    }

    return false
}
