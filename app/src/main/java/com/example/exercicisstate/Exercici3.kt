package com.example.exercicisstate

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import kotlin.random.Random


@Composable
fun ViewEx3(modifier: Modifier) {
    var n by remember { mutableStateOf(Random.nextInt(0, 100)) }
    var tries by remember { mutableStateOf(0) }
    var textTotal by remember { mutableStateOf("") }
    var showResult by remember { mutableStateOf(false) }
    ConstraintLayout(
        modifier
            .fillMaxSize()
    ) {
        val (title, desc, field, button, result) = createRefs()
        Text(
            text = "Exercici 3",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(title) {
                    bottom.linkTo(desc.top, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        Text(
            text = "Endevina el número${if (tries > 0) ", portes $tries intent${if (tries > 1) "s" else ""}" else ""}.",
            modifier = Modifier
                .constrainAs(desc) {
                    bottom.linkTo(field.top, margin = 40.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        var nom by remember { mutableStateOf("") }

        val guess = NumberField(
            "Número entre el 0 i el 100",
            decimals = true,
            modifier = Modifier.constrainAs(field) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        Button(
            onClick = {
                showResult = true
                tries++
                textTotal = when {
                    guess.toInt() < n -> "El número que busques és més gran"
                    guess.toInt() > n -> "El número que busques és més petit"
                    else -> "Has encertat!"
                }
            },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(field.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text(text = "Calculate")
        }
        if (showResult) {

            Text(
                text = textTotal,
                modifier = Modifier
                    .width(350.dp)
                    .constrainAs(result) {
                        top.linkTo(button.bottom, margin = 10.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }

            )
        }
    }


}


@Preview(showBackground = true)
@Composable
fun ViewUI() {
    ExercicisStateTheme {
        ViewEx3(Modifier)
    }
}