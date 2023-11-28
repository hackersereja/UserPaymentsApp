package ru.sergean.userpaymentsapp.utils

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Context.dpToPx(dp: Int): Int {
    val density = resources.displayMetrics.density
    return (dp * density).toInt()
}

fun Fragment.showSnackbar(text: String, duration: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(requireView(), text, duration).show()
}
