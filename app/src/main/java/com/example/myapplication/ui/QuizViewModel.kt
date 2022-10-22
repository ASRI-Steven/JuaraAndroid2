import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class QuizViewModel : ViewModel() {

    private val _totalState = MutableStateFlow(0)
    var totalState: StateFlow<Int> = _totalState

    private val _questionState = MutableStateFlow(emptyList<Int>())
    var questionState: StateFlow<List<Int>> = _questionState

    private val _answerState = MutableStateFlow(emptyList<Int>())
    var answerState: StateFlow<List<Int>> = _answerState

    fun totalQuestion(total: Int) {
        _totalState.value = total
    }

    fun addQuestion(number: Int) {
        _questionState.update {
            it.plus(number)
        }
    }

    fun addAnswer(number: Int) {
        _answerState.update {
            it.plus(number)
        }
    }

    fun clearViewModel() {
        _totalState.update {
            0
        }
        _questionState.update {
            listOf()
        }
        _answerState.update {
            listOf()
        }
    }
}