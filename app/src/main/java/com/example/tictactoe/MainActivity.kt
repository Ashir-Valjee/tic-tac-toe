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
                .weight(2f)) {
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
                    .background(Color.hsl(0f,1f, .76f))
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
                    .background(Color.hsl(131f, 0.52f, 0.50f))
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
fun TicTacToeCell(row: Int, col: Int, modifier: Modifier= Modifier) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
            ) {}

}

@Composable
fun TicTacToeBoard(modifier: Modifier= Modifier) {
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
                    TicTacToeCell(row, col)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicTacToeGamePreview() {
    TicTacToeTheme {
        TicTacToeGame()
    }
}

//@Preview(showBackground = true)
//@Composable
//fun TicTacToeCellPreview() {
//    TicTacToeBoard()
//}
