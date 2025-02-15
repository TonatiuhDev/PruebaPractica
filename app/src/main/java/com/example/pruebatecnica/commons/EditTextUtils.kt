package com.example.pruebatecnica.commons

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

fun EditText.validateEmail(errorMessage: String, textInputLayout: TextInputLayout? = null) =
    if (text.toString().trim().isNotBlank() && text.toString().trim().isValidEmail()) {
        if (textInputLayout != null) textInputLayout.error = null else error = null
        true
    } else {
        requestFocus()
        if (textInputLayout != null) textInputLayout.error = errorMessage else error = errorMessage
        false
    }

fun EditText.validatePassword(errorMessage: String, textInputLayout: TextInputLayout? = null) =
    if (text.toString().trim().isNotBlank() && text.toString().trim().isValidPassword()) {
        if (textInputLayout != null) textInputLayout.error = null else error = null
        true
    } else {
        requestFocus()
        if (textInputLayout != null) textInputLayout.error = errorMessage else error = errorMessage
        false
    }