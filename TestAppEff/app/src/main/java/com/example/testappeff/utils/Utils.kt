package com.example.testappeff

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.navigation.NavController

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun EditText.isEmailValid(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this.text.toString()).matches()
}

fun NavController.safeNavigate(direction: Int) {
//    Log.d(clickTag, "Click happened")
    currentDestination?.getAction(direction)?.run {
//        Log.d(clickTag, "Click Propagated")
        navigate(direction)
    }
}