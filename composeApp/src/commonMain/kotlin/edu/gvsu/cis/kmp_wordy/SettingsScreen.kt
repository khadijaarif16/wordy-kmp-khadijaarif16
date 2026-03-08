package edu.gvsu.cis.kmp_wordy

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun SettingsScreen(viewModel: AppViewModel, onConfirm:()->Unit, onCancel: () -> Unit)
{
    val savedSettings by viewModel.settings.collectAsState()
    var red by remember(savedSettings){mutableStateOf<Float>(savedSettings.red)}
    var green by remember(savedSettings){mutableStateOf<Float>(savedSettings.green)}
    var blue by remember(savedSettings){mutableStateOf<Float>(savedSettings.blue)}
    var minLen by remember(savedSettings){mutableStateOf(savedSettings.minWordLength.toFloat())}
    var maxLen by remember(savedSettings){mutableStateOf(savedSettings.maxWordLength.toFloat())}
    var stockLetters by remember(savedSettings){mutableStateOf(savedSettings.stockLetters.toFloat())}
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        Text("Setting")
        Text("Red: ${(red*255).toInt()}")
        Slider(value = red, onValueChange = {red=it}, valueRange = 0f..1f)
        Text("Green: ${(green*255).toInt()}")
        Slider(value = green, onValueChange = {green=it}, valueRange = 0f..1f)
        Text("Blue: ${(blue*255).toInt()}")
        Slider(value = blue, onValueChange = {blue=it}, valueRange = 0f..1f)

        //word lengths
        Text("Min Word Length: ${(minLen).toInt()}")
        Slider(value = minLen, onValueChange = {
            minLen=it
            if(maxLen<it)
                maxLen =it
                                               }, valueRange = 2f..12f)
        Text("Max Word Length: ${(maxLen).toInt()}")
        Slider(value = maxLen, onValueChange = {
            maxLen=it
            if(minLen>it)
                minLen =it
            if(stockLetters<it)
                stockLetters=it
                                               }, valueRange = 2f..12f)


        Text("Stock Letters: ${(stockLetters).toInt()}")
        Slider(value = stockLetters, onValueChange = {
            stockLetters=it.coerceAtLeast(maxLen)
                                                     }, valueRange = 4f..20f)

        //buttons
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth())
        {
            Button(onClick = onCancel) { Text("Cancel") }
            Button(onClick = {
                viewModel.settingsApply(GameSettings(
                    red =red, green =green, blue =blue,
                    minWordLength = minLen.toInt(),
                    maxWordLength = maxLen.toInt(),
                    stockLetters=stockLetters.toInt().coerceAtLeast(maxLen.toInt())
                ))
                onConfirm()
            }) {Text("Confirm")}
        }
    }
}
