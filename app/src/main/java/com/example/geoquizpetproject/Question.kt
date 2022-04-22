package com.example.geoquizpetproject

import androidx.annotation.StringRes

class Question(@StringRes val textResId: Int, val answer: Boolean, val checkAnswer: Boolean = false) {
}