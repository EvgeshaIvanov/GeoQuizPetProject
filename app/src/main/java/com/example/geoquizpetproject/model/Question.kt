package com.example.geoquizpetproject.model

import androidx.annotation.StringRes

class Question(@StringRes val textResId: Int, val answer: Boolean, var checkAnswer: Boolean = false) {
}