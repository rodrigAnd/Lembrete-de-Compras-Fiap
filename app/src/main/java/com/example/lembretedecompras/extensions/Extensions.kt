package com.example.lembretedecompras.extensions

import android.app.Activity
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.example.lembretedecompras.R
import com.example.lembretedecompras.databinding.ActivityLoginBinding

fun Activity.hideKeyboard() {
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
}


