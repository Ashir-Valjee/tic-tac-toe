package com.example.tictactoe.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.R
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource


@Composable
fun HomeScreen(
    modifier: Modifier= Modifier,
    onStart: (String, String) -> Unit
) {


    var playerOne by remember { mutableStateOf("") }
    var playerTwo by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background)))

    {
//      Title
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

//        Players info form

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(26.dp)
            ,
            verticalArrangement = Arrangement.SpaceBetween
        )
        {
//            Enter player 1 section
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Enter player 1 name"
                )

                OutlinedTextField(
                    value = playerOne,
                    onValueChange = { playerOne = it },
                    label = { Text("Player 1 name") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

//            Enter player 2 section
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Enter player 2 name"
                )

                OutlinedTextField(
                    value = playerTwo,
                    onValueChange = { playerTwo = it },
                    label = { Text("Player 2 name") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

        }
// image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.tic_tac), // your drawable
                contentDescription = "Tic Tac Toe Illustration",
                modifier = Modifier
                    .fillMaxWidth(0.6f) // make it 60% of screen width
                    .padding(top = 24.dp)
            )
        }

//      Start Game
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            contentAlignment = Alignment.Center
        )
        {

            Button(onClick = {
                onStart(playerOne.trim(), playerTwo.trim())
            },
                enabled = playerOne.isNotBlank() && playerTwo.isNotBlank()
            ) {
                Text(stringResource(R.string.start_game_button))
            }
        }

    }

}