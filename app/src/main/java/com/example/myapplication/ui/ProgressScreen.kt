package com.example.myapplication.ui

import android.content.Context
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
fun ProgressScreen(
    modifier: Modifier = Modifier,
    onProgressButtonClicked: (context: Context, number: String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "How many numbers?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
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
        Button(
            onClick = { onProgressButtonClicked(context, text) },
            shape = RoundedCornerShape(50.dp),
            elevation = ButtonDefaults.elevation(),
            border = BorderStroke(1.dp, Color.Blue),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = modifier
                .height(40.dp)
                .width(80.dp)
        ) {
            Text(
                stringResource(R.string.go),
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressScreenPreview() {
    ProgressScreen(onProgressButtonClicked = { _, _ -> })
}