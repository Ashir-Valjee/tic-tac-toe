package com.example.tictactoe

import android.os.Bundle
import android.view.RoundedCorner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.tictactoe.ui.theme.TicTacToeTheme
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.clickable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TicTacToeGame(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TicTacToeGame(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan))

    {

//        title area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
                contentAlignment = Alignment.Center
            ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.title))
        }

//        game area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .weight(2f),
            contentAlignment = Alignment.Center) {
            TicTacToeBoard()
        }

//        player info
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {

//            player 1 info
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
                ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.player_1_info)
                )
                Box(modifier = Modifier
                    .size(25.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(colorResource(id=R.color.player_one_color))
                ) {

                }

            }

            //            player 2 info
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier,


                    text = stringResource(R.string.player_2_info)
                )
                Box(modifier = Modifier
                    .size(25.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = colorResource(id=R.color.player_two_color))
                ) {

                }

            }
//            winner section
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.winner_info)
                )
            }
//            New game section
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
                contentAlignment = Alignment.Center
            )
            {

                Button(onClick = {
                    println("Button was clicked")
                }) {
                    Text(stringResource(R.string.new_game_button))
                }
            }
        }
    }
}



@Composable
fun TicTacToeCell(row: Int, col: Int, color: Color, onClick: ()-> Unit, modifier: Modifier= Modifier) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color)
            .clickable{onClick()},
        contentAlignment = Alignment.Center
            ) {}

}

@Composable
fun TicTacToeBoard(modifier: Modifier= Modifier) {
    val playerOneColor = colorResource(id = R.color.player_one_color)
    val playerTwoColor = colorResource(id = R.color.player_two_color)
    var board by remember { mutableStateOf(List(3) {MutableList(3) {Color.LightGray} })}
    var currentPlayer by remember { mutableStateOf(1) }
    var winningPlayer by remember { mutableStateOf<Int?> (null)}
    var isDraw by remember { mutableStateOf<Boolean> (false)}

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly)
    {
        repeat(3) {
            row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(8.dp),
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
                                val newBoard = board.map { it.toMutableList()}
                                newBoard[row][col] =
                                    if (currentPlayer == 1) playerOneColor else playerTwoColor
                                board = newBoard

                                if (hasWinner(newBoard)) {
                                    winningPlayer = currentPlayer
                                } else {
//                                  Check if there are no gray cells
                                    isDraw = newBoard.all {row -> row.all {it != Color.LightGray}}
                                    if (!isDraw) {
                                        currentPlayer = if (currentPlayer == 1) 2 else 1
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


@Preview(showBackground = true)
@Composable
fun TicTacToeGamePreview() {
    TicTacToeTheme {
        TicTacToeGame()
    }
}

