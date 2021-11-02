package com.udacity.shoestore.utils

import androidx.core.text.isDigitsOnly
import androidx.databinding.InverseMethod

/**
 * Converter object helps data binding perform proper type conversion
 */
object Converter {
    @InverseMethod("stringToSize")
    @JvmStatic
    fun sizeToString(
        value: Double
    ): String {
        return when (value) {
            0.0 -> ""
            else -> value.toString()
        }
    }

    @JvmStatic
    fun stringToSize(
        value: String
    ): Double {
        return when {
            value.isBlank() || !value.isDigitsOnly() -> 0.0 //to prevent print and save dot without numbers and empty value
            else -> value.toDouble()
        }
    }
}
