package com.example.exercicisstate

import androidx.compose.foundation.layout.fillMaxSize
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


@Composable
fun ViewEx1(modifier : Modifier) {
    var showResult by remember { mutableStateOf(false) }
    ConstraintLayout(
        modifier
            .fillMaxSize()
    ) {
        val (title, desc, fieldTotal, fieldPercentatge, button, result) = createRefs()
        Text(
            text = "Exercici 1",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(title) {
                    bottom.linkTo(desc.top, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        Text(
            text = "Ingresa el total del menú i el percentatge de propina que vols donar",
            modifier = Modifier
                .constrainAs(desc) {
                    bottom.linkTo(fieldTotal.top, margin = 40.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        val total =
            NumberField("Total del menu", modifier = Modifier.constrainAs(fieldTotal) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        val percentage = NumberField(
            "Percentatge de propina",
            modifier = Modifier.constrainAs(fieldPercentatge) {
                top.linkTo(fieldTotal.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        Button(
            onClick = { showResult = true },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(fieldPercentatge.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text(text = "Calculate")
        }
        if (showResult) {
            val textTotal = "Total: ${calculaPropina(total, percentage)} €"
            Text(
                text = textTotal,
                modifier = Modifier.constrainAs(result) {
                    top.linkTo(button.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }
    }


}

fun calculaPropina(strtotal: String, strpercentage: String): Double {
    val total : Int = Integer.parseInt(strtotal)
    val percentatge : Int = Integer.parseInt(strpercentage)
    return total + (total * (percentatge / 100.0))
}


@Preview(showBackground = true)
@Composable
fun Hola() {
    ExercicisStateTheme {
        ViewEx1(Modifier)
    }
}