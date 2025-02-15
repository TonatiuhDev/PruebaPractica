package com.example.pruebatecnica.commons

import android.util.Patterns
import java.util.regex.Pattern

fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword() =
    Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&+=_,.-])(?=\\S+$).{8,16}$").matcher(this).matches()
