package com.example.exercicisstate

import android.icu.util.Calendar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
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
fun ViewEx2(modifier: Modifier) {
    var textTotal by remember { mutableStateOf("") }
    var showResult by remember { mutableStateOf(false) }
    ConstraintLayout(
        modifier
            .fillMaxSize()
    ) {
        val (title, desc, fieldAltura, fieldPes, fieldAny, fieldNom, button, result) = createRefs()
        Text(
            text = "Exercici 2",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(title) {
                    bottom.linkTo(desc.top, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        Text(
            text = "Ingresa les teves dades per calcular el teu IMC",
            modifier = Modifier
                .constrainAs(desc) {
                    bottom.linkTo(fieldNom.top, margin = 40.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        var nom by remember { mutableStateOf("") }

        TextField(
            value = nom,
            onValueChange = { nom = it },
            label = { Text("Nom") },
            modifier = Modifier.constrainAs(fieldNom) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        val any =
            NumberField("Any de naixament", modifier = Modifier.constrainAs(fieldAny) {
                top.linkTo(fieldNom.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        val pes = NumberField(
            "Pes en kg",
            decimals = true,
            modifier = Modifier.constrainAs(fieldPes) {
                top.linkTo(fieldAny.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        val altura = NumberField(
            "Altura en m",
            decimals = true,
            modifier = Modifier.constrainAs(fieldAltura) {
                top.linkTo(fieldPes.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        Button(
            onClick = {
                showResult = true
                textTotal = "Bones ${nom}! Tens ${calculaEdat(any)} anys,\ni el teu IMC és ${
                    calculaImc(
                        pes,
                        altura
                    )
                }"
            },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(fieldAltura.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text(text = "Calculate")
        }
        if (showResult) {

            Text(
                text = textTotal,
                modifier = Modifier.constrainAs(result){
                    top.linkTo(button.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }.width(350.dp)
            )
        }
    }


}

private fun calculaImc(
    pes: String,
    altura: String
): String {
    if(pes.isBlank() || altura.isBlank()) return ""
    val imc = pes.toDouble() / (2 * altura.toDouble());
    var result = "${"%.2f".format(imc)} "
    if(imc < 18.51) result += "(insuficient)"
    else if (imc < 25) result += "(suficient)"
    else if (imc < 49.9) result += "(sobrepés)"
    else result += "(obesitat)"
    return result
}

private fun calculaEdat(anyStr: String): String {
    if(anyStr.isBlank()) return ""
    return "${Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(anyStr)}"
}

@Preview(showBackground = true)
@Composable
fun viewUI() {
    ExercicisStateTheme {
        ViewEx2(Modifier)
    }
}