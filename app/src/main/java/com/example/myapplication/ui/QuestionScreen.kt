package com.example.myapplication.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun QuestionScreen(
    totalNumber: Int,
    questionState: List<Int>,
    modifier: Modifier = Modifier,
    onQuestionButtonClicked: () -> Unit,
) {

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
        Text(
            questionState[number - 1].toString(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(32.dp))
        if (number == totalNumber) {
            Button(
                onClick = onQuestionButtonClicked,
                shape = RoundedCornerShape(100.dp),
                elevation = ButtonDefaults.elevation(),
                border = BorderStroke(1.dp, Color.Blue),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = modifier
                    .height(40.dp)
                    .width(150.dp)
            ) {
                Text(
                    stringResource(R.string.start_quiz),
                    color = Color.Black
                )
            }
        } else {
            Button(
                onClick = {
                    number++
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
fun QuestionScreenPreview() {
    QuestionScreen(1, listOf(), onQuestionButtonClicked = {})
}