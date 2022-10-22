package com.example.myapplication

import QuizViewModel
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.util.Log
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.*
import kotlin.random.Random

enum class QuizScreen {
    Start(),
    Progress(),
    Question(),
    Answer(),
    End()
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@Composable
fun QuizApp(

    viewModel: QuizViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {

    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        onDispose {
            // restore original orientation when view disappears
            activity.requestedOrientation = originalOrientation
        }
    }

    val totalState by viewModel.totalState.collectAsState()
    val questionState by viewModel.questionState.collectAsState()
    val answerState by viewModel.answerState.collectAsState()

    Scaffold {
        NavHost(
            navController = navController,
            startDestination = QuizScreen.Start.name
        ) {
            composable(route = QuizScreen.Start.name) {
                Log.d("checknum", questionState.toString())
                StartScreen(
                    onStartButtonClicked = {
                        navController.navigate(QuizScreen.Progress.name) {
                            popUpTo(QuizScreen.Start.name) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable(route = QuizScreen.Progress.name) {
                ProgressScreen(
                    onProgressButtonClicked = { context, number ->
                        if (number == "") {
                            Toast.makeText(context, "Number should not empty", Toast.LENGTH_SHORT)
                                .show()
                        } else if (number.toInt() < 0 || number.toInt() == 0) {
                            Toast.makeText(
                                context,
                                "Number should not below 0 or 0",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            viewModel.totalQuestion(number.toInt())
                            val randomGenerator = Random(System.currentTimeMillis())
                            for (i in 0 until number.toInt()) {
                                val result = randomGenerator.nextInt(0, 1000)
                                viewModel.addQuestion(result)
                            }
                            navController.navigate(QuizScreen.Question.name) {
                                popUpTo(QuizScreen.Progress.name) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                )
            }
            composable(route = QuizScreen.Question.name) {
                QuestionScreen(totalState, questionState, onQuestionButtonClicked = {
                    navController.navigate(QuizScreen.Answer.name) {
                        popUpTo(QuizScreen.Question.name) {
                            inclusive = true
                        }
                    }
                })
            }
            composable(route = QuizScreen.Answer.name) {
                AnswerScreen(totalState, onAddAnswerButtonClicked = {
                    viewModel.addAnswer(it)
                }, onAnswerButtonClicked = {
                    viewModel.addAnswer(it)
                    navController.navigate(QuizScreen.End.name) {
                        popUpTo(QuizScreen.Answer.name) {
                            inclusive = true
                        }
                    }
                })
            }
            composable(route = QuizScreen.End.name) {
                var correct = 0
                for (i in 0 until totalState) {
                    if (questionState[i] == answerState[i]) {
                        correct++
                    }
                }
                EndScreen(correct, totalState, onEndButtonClicked = {
                    viewModel.clearViewModel()
                    navController.navigate(QuizScreen.Start.name) {
                        popUpTo(QuizScreen.End.name) {
                            inclusive = true
                        }
                    }
                })
            }
        }
    }
}