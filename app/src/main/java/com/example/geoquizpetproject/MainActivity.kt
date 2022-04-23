package com.example.geoquizpetproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.Toast
import com.example.geoquizpetproject.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private val questionBank: List<Question> = mutableListOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0

    private var correctAnswerCounter = 0

    private var incorrectAnswerCounter = 0

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            backAnswer.setOnClickListener {
                if (currentIndex in 0..5) {
                    backAnswer.isEnabled = true
                    currentIndex = (currentIndex - 1) % questionBank.size
                    updateQuestion()
                    if (questionBank[currentIndex].checkAnswer) {
                        disableButton(trueButton)
                        disableButton(falseButton)
                    } else {
                        enableButton(trueButton)
                        enableButton(falseButton)
                    }
                } else {
                    backAnswer.isEnabled = false
                }

            }
            trueButton.setOnClickListener {
                checkAnswer(true)
                disableButton(trueButton)
                disableButton(falseButton)
            }
            falseButton.setOnClickListener {
                checkAnswer(false)
                disableButton(falseButton)
                disableButton(trueButton)
            }
            nextButton.setOnClickListener {
                if (currentIndex == 5) {
                    disableButton(nextButton)
                } else {
                    currentIndex = (currentIndex + 1) % questionBank.size
                    updateQuestion()
                    enableButton(trueButton)
                    enableButton(falseButton)
                }
            }

        }

        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)

    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)


    }

    private fun checkAnswer(userAnswer: Boolean) {

        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast

        } else {
            R.string.incorrect_toast

        }
        Snackbar.make(binding.root, messageResId, Snackbar.LENGTH_SHORT).show()
        if (userAnswer == correctAnswer) {
            correctAnswerCounter++

        } else {
            incorrectAnswerCounter++

        }

    }

    private fun disableButton(button: Button) {
        button.isEnabled = false
        questionBank[currentIndex].checkAnswer = true
    }

    private fun enableButton(button: Button) {
        button.isEnabled = true
    }

    private fun checkButton(button: Button): Boolean {
        return button.isEnabled
    }
}