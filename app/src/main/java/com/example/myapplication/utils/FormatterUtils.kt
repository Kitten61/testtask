package com.example.myapplication.utils

object FormatterConst {

    const val PATTERN_CODE = "^[0-9]{0,6}\$"
    val codeRegex = Regex(PATTERN_CODE)

    const val PATTERN_PHONE = "^[0-9]{0,10}\$"
    val phoneRegex = Regex(PATTERN_PHONE)

    const val PHONE_DIGITS = 10
}