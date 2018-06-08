package com.hassan.ibrahim.timefighter.timefighter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    internal lateinit var tapMeButton: Button
    internal lateinit var gameScoreTextView: TextView
    internal lateinit var timeLeftTextView: TextView
    internal lateinit var countDownTimer : CountDownTimer
    internal var gameStarted = false
    internal var initialCountDown: Long = 60000
    internal var countDownInterval: Long = 1000
    internal var score : Int = 0
    internal val TAG = MainActivity::class.java.simpleName
    internal var timeLeftOnTimer: Long = 60000

    companion object {
        private val SCORE_KEY : String = "SCORE_KEY"
        private val TIME_LEFT_KEY : String = "TIME_LEFT_KEY"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tapMeButton = findViewById<Button>(R.id.tap_me_button)
        gameScoreTextView = findViewById<TextView>(R.id.game_score_text_view)
        timeLeftTextView = findViewById<TextView>(R.id.time_left_text_view)

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(SCORE_KEY)
            timeLeftOnTimer = savedInstanceState.getLong(TIME_LEFT_KEY)
            restoreGame()
        } else {
            resetGame()
        }
        tapMeButton.setOnClickListener {
            incrementScore()
        }
    }

    fun restoreGame() {
        gameScoreTextView.text = getString(R.string.your_score, score.toString())
        val restoredTime = timeLeftOnTimer / 1000

        timeLeftTextView.text = getString(R.string.time_left, restoredTime.toString())

        countDownTimer = object : CountDownTimer(timeLeftOnTimer, countDownInterval) {
            override fun  onTick(millisecondsUntilFinished: Long) {
                Log.d(TAG, "millisecondsUntilFinished is: $millisecondsUntilFinished")

                var timeLeft = millisecondsUntilFinished / 1000
                Log.d(TAG, "timeLeft is: $timeLeft")

                timeLeftTextView.text = getString(R.string.time_left, timeLeft.toString())
            }

            override fun onFinish() {
                endGame()
            }
        }
        Log.d(TAG, " TimeLeft is: $timeLeftOnTimer")
        Log.d(TAG, " Score is: $score")
        countDownTimer.start()
        gameStarted = true
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putLong(TIME_LEFT_KEY, timeLeftOnTimer)
        outState?.putInt(SCORE_KEY, score)
        countDownTimer.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "\nonDestroyCalled")
    }

    private fun resetGame() {
        score = 0
        gameScoreTextView.text = getString(R.string.your_score, score.toString())
        val intitalTimeLeft = initialCountDown / 1000
        timeLeftTextView.text = getString(R.string.time_left, intitalTimeLeft.toString())
        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval) {
            override fun onTick(p0: Long) {
                timeLeftOnTimer = p0
                val timeLeft = p0/1000
                timeLeftTextView.text = getString(R.string.time_left, timeLeft.toString())
            }

            override fun onFinish() {
                endGame()
            }
        }
        gameStarted = false
    }

    private fun resumeGame() {
        gameScoreTextView.text = getString(R.string.your_score, score.toString())
        val timeLeft= timeLeftOnTimer/ 1000
        timeLeftTextView.text = getString(R.string.time_left, timeLeft.toString())
        countDownTimer = object : CountDownTimer(timeLeft, countDownInterval) {
            override fun onTick(p0: Long) {
                timeLeftOnTimer = p0
                val timeLeft = p0/1000
                timeLeftTextView.text = getString(R.string.time_left, timeLeft.toString())
            }

            override fun onFinish() {
                endGame()
            }
        }
        gameStarted = false

    }

    private fun startGame() {
        countDownTimer.start()
        gameStarted = true
    }

    private fun endGame() {
        val toast: Toast = Toast.makeText(this, getString(R.string.game_over_message, score.toString()), Toast.LENGTH_LONG)
        toast.show()
        resetGame()
    }

    private fun incrementScore() {
        if (!gameStarted) {
            startGame()
        }
        score++
        gameScoreTextView.text = getString(R.string.your_score, score.toString())
    }
}
