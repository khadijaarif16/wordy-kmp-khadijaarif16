package edu.gvsu.cis.kmp_wordy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items


@Composable
fun HistoryScreen(viewModel: AppViewModel, onBack:()->Unit)
{
    val history by  viewModel.gameHistory.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp))
        {
            Text("Game History")
            //sprting buttons
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly)
            {
                Button(onClick = {viewModel.sortAlphabetically()}){Text("A-Z")}
                Button(onClick = {viewModel.sortbyPoints()}){Text("Points")}
                Button(onClick = {viewModel.sortbyLength()}){Text("Length")}
                Button(onClick = {viewModel.sortbyMovesAndTime()}){Text("Time and Moves")}
                }
                HorizontalDivider()


                LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(history) { session ->
                        Card {
                            Text(
                                text = "${session.word}: ${session.points} points, ${session.numMoves} moves,${session.time} s ",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
                    Button(onClick = onBack, modifier = Modifier.fillMaxWidth()){Text("Back")}
                }

        }


