package com.example.myapplication.ui

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun AnswerScreen(
    totalNumber: Int,
    modifier: Modifier = Modifier,
    onAddAnswerButtonClicked: (number: Int) -> Unit,
    onAnswerButtonClicked: (number: Int) -> Unit,
) {

    val context = LocalContext.current
    var text by remember { mutableStateOf("") }
    var number by remember { mutableStateOf(1) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Number $number",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(16.dp))
        TextField(
            value = text,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent
            ),
            onValueChange = {
                text = it
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = modifier
                .width(100.dp)
                .background(Color.White)
        )
        Spacer(Modifier.height(32.dp))
        if (number == totalNumber) {
            Button(
                onClick = {
                    if (text == "") {
                        Toast.makeText(context, "Number should not empty", Toast.LENGTH_SHORT)
                            .show()
                    } else if (text.toInt() < 0 || text.toInt() == 0) {
                        Toast.makeText(
                            context,
                            "Number should not below 0 or 0",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        number++
                        onAnswerButtonClicked(text.toInt())
                        text = ""
                    }
                },
                shape = RoundedCornerShape(100.dp),
                elevation = ButtonDefaults.elevation(),
                border = BorderStroke(1.dp, Color.Blue),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = modifier
                    .height(40.dp)
                    .width(150.dp)
            ) {
                Text(
                    stringResource(R.string.end),
                    color = Color.Black
                )
            }
        } else {
            Button(
                onClick = {
                    if (text == "") {
                        Toast.makeText(context, "Number should not empty", Toast.LENGTH_SHORT)
                            .show()
                    } else if (text.toInt() < 0 || text.toInt() == 0) {
                        Toast.makeText(
                            context,
                            "Number should not below 0 or 0",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        number++
                        onAddAnswerButtonClicked(text.toInt())
                        text = ""
                    }
                },
                shape = RoundedCornerShape(100.dp),
                elevation = ButtonDefaults.elevation(),
                border = BorderStroke(1.dp, Color.Blue),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = modifier
                    .height(40.dp)
                    .width(150.dp)
            ) {
                Text(
                    stringResource(R.string.next),
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnswerScreenPreview() {
    AnswerScreen(1, onAddAnswerButtonClicked = {}, onAnswerButtonClicked = {})
}