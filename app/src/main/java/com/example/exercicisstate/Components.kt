package com.example.exercicisstate

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun NumberField(text: String, decimals: Boolean = false, modifier: Modifier): String {
    var myText by remember { mutableStateOf("") }

    val pattern = if (decimals) {
        Regex("^\\d*\\.?\\d*\$") // Permite números con o sin decimales
    } else {
        Regex("^\\d+\$") // Solo números enteros
    }

    TextField(
        value = myText,
        onValueChange = {
            if (it.isEmpty() || it.matches(pattern)) {
                myText = it
            }
        },
        label = { Text(text) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), // Cambiar a Decimal
        modifier = modifier
    )

    return myText
}
