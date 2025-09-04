package com.example.tictactoe.screens

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.R
import com.example.tictactoe.game.TicTacToeBoard
import com.example.tictactoe.game.TicTacToeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.LaunchedEffect

@Composable
fun TicTacToeGame(
    modifier: Modifier = Modifier,
    playerOne: String,
    playerTwo: String
) {
    val viewModel: TicTacToeViewModel = viewModel()

    val initialStatus = "$playerOne's turn"
    var status by remember { mutableStateOf(initialStatus) }
    var gameId by remember { mutableStateOf(0) }

    LaunchedEffect(playerOne, playerTwo) {
        viewModel.setPlayers(playerOne, playerTwo)
        status = "$playerOne's turn"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background)))

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
                    .fillMaxWidth()
                ,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = colorResource(id=R.color.title),
                text = stringResource(R.string.title))
        }

//        game area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            contentAlignment = Alignment.Center) {
            key (gameId) {
                TicTacToeBoard(
                    onStatusChange = {status = it},
                    playerOne = playerOne,
                    playerTwo = playerTwo )
            }
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
                    text = stringResource(R.string.player_1_info, playerOne)
                )
                Box(modifier = Modifier
                    .size(25.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(colorResource(id = R.color.player_one_color))
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


                    text = stringResource(R.string.player_2_info, playerTwo)
                )
                Box(modifier = Modifier
                    .size(25.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = colorResource(id = R.color.player_two_color))
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
                    text = status
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
                    // reset View model state
                    viewModel.resetGame()
                    gameId++
                    status = "$playerOne's turn"
                }
                ) {
                    Text(stringResource(R.string.new_game_button))
                }
            }
        }
    }
}
