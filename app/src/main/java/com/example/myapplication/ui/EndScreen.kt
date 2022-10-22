package com.example.myapplication.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
fun EndScreen(
    score: Int,
    total: Int,
    onEndButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "You Guess Right",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        Text(
            "$score from $total",
            fontSize = 18.sp
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onEndButtonClicked,
            shape = RoundedCornerShape(100.dp),
            elevation = ButtonDefaults.elevation(),
            border = BorderStroke(1.dp, Color.Blue),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = modifier
                .height(40.dp)
                .width(200.dp)
        ) {
            Text(
                stringResource(R.string.play_again),
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EndScreenPreview() {
    EndScreen(0, 0, onEndButtonClicked = {})
}