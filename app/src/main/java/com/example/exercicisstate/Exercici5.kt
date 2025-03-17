package com.example.exercicisstate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.exercicisstate.ui.theme.ExercicisStateTheme


@Composable
fun ViewEx5(modifier: Modifier = Modifier) {
    var value by remember { mutableStateOf("") }
    var conversionResult by remember { mutableStateOf("") }
    var selectedConversion by remember { mutableIntStateOf(0) }
    var showResult by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val conversionOptions = listOf(
        "Polzades a Centímetres",
        "Iardes a Metres",
        "Milles a Quilòmetres",
        "Centímetres a Polzades",
        "Metres a Iardes",
        "Quilòmetres a Milles"
    )

    val conversionFactors = listOf(
        2.54,
        0.9144,
        1.60934,
        0.393701,
        1.09361,
        0.621371
    )

    val factor = conversionFactors[selectedConversion]

    fun convertUnits() {
        if (value.isNotEmpty()) {
            val inputValue = value.toDoubleOrNull()
            if (inputValue != null) {
                val result = inputValue * factor
                conversionResult = "Resultat: %.2f ${conversionOptions[selectedConversion].split(" ")[2]}".format(result)
                showResult = true
            } else {
                conversionResult = "Valor no vàlid"
                showResult = true
            }
        }
    }

    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (title, desc, field, dropdown, button, result) = createRefs()
        Text(
            text = "Exercici 5",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(title) {
                    bottom.linkTo(desc.top, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = "Conversor d'unitats",
            modifier = Modifier
                .constrainAs(desc) {
                    bottom.linkTo(field.top, margin = 40.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        value = NumberField(
            text = "Valor en ${conversionOptions[selectedConversion].split(" ")[0].toLowerCase()}",
            decimals = true,
            modifier = Modifier
                .constrainAs(field) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Column(
            modifier = Modifier
                .constrainAs(dropdown) {
                    top.linkTo(field.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            OutlinedButton (
                onClick = { expanded = !expanded },
                modifier = Modifier.width(300.dp)
            ) {
                Text(text = conversionOptions[selectedConversion])
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                conversionOptions.forEachIndexed { index, option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            selectedConversion = index
                            expanded = false
                        }
                    )
                }
            }
        }
        Button(
            onClick = { convertUnits() },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(dropdown.bottom, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text(text = "Convertir")
        }
        if (showResult) {
            Text(
                text = conversionResult,
                modifier = Modifier
                    .constrainAs(result) {
                        top.linkTo(button.bottom, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ViewUI5() {
    ExercicisStateTheme {
        ViewEx5(Modifier)
    }
}