package com.example.lembretedecompras.extensions

import android.app.Activity
import android.view.View
import android.view.WindowManager
import android.widget.EditText


fun Activity.hideKeyboard() {
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

}


