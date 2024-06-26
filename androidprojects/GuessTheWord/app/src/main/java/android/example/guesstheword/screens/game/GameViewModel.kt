package android.example.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map


private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
private val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 1500)
private val NO_BUZZ_PATTERN = longArrayOf(0)




class GameViewModel: ViewModel() {
    // The current word
    // The current score
    // The list of words - the front of the list is the next word to guess

    enum class BuzzType(val pattern: LongArray) {
        CORRECT(CORRECT_BUZZ_PATTERN),
        GAME_OVER(GAME_OVER_BUZZ_PATTERN),
        COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
        NO_BUZZ(NO_BUZZ_PATTERN)
    }

    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private lateinit var wordList: MutableList<String>
    private var skippedList: MutableList<String>

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    private val _eventBuzz = MutableLiveData<BuzzType>()
    val eventBuzz: LiveData<BuzzType>
        get() = _eventBuzz

    private val timer: CountDownTimer

    private val _currentTime = MutableLiveData<Long>()

    val currentTime: LiveData<Long>
        get() = _currentTime

    val currentTimeString: LiveData<String> = currentTime.map { time ->
        DateUtils.formatElapsedTime(time)
    }

    init {
        Log.i("GameViewModel", "GameViewModel created")
        _word.value = ""
        _score.value = 0
        _eventGameFinish.value = false

        skippedList = mutableListOf()

        timer = object: CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished / ONE_SECOND
                if(millisUntilFinished / ONE_SECOND <= COUNTDOWN_PANIC_SECONDS) {
                    _eventBuzz.value = BuzzType.COUNTDOWN_PANIC
                }
            }

            override fun onFinish() {
                _currentTime.value = DONE
                _eventGameFinish.value = true
                _eventBuzz.value = BuzzType.GAME_OVER
            }
        }

        timer.start()
        resetList()
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
        Log.i("GameViewModel", "GameViewModel destroyed")
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            if(skippedList.isNotEmpty()) {
                wordList.addAll(skippedList)
                skippedList.clear()
                wordList.shuffle()
            } else {
                _eventGameFinish.value = true
                return
            }
        }
        _word.value = wordList.removeAt(0)
    }

    /**
     * Methods for buttons presses
     */
    fun onSkip() {
        if(score.value!! > 0) {
            _score.value = score.value?.minus(1)
        }
        skippedList.add(word.value!!)
        nextWord()
    }

    /**
     * When the word is correctly guessed, increase the score, change the word.
     */
    fun onCorrect() {
        _score.value = score.value?.plus(1)
        _eventBuzz.value = BuzzType.CORRECT
        nextWord()
    }

    fun onGameFinish() {
        _eventGameFinish.value = false
    }

    fun onBuzzComplete() {
        _eventBuzz.value = BuzzType.NO_BUZZ
    }

    companion object {
        const val DONE = 0L

        const val ONE_SECOND = 1000L

        const val COUNTDOWN_TIME = 60000L

        const val COUNTDOWN_PANIC_SECONDS = 10L
    }
}