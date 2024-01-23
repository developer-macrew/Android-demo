package com.example.dashboard.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.dashboard.R

fun TextView.setSpanText(s1: String, s2: String, callback: (() -> Unit)? = null) {
    val spannable = SpannableString(s1 + s2)

    val spannableClick = object : ClickableSpan() {
        override fun onClick(view: View) {
            callback?.invoke()
        }
    }
    spannable.setSpan(
        spannableClick,
        s1.length, // start
        s1.length + s2.length, // end
        0
    )
    spannable.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(context, R.color.teal_700)),
        s1.length, // start
        s1.length + s2.length, // end
        Spannable.SPAN_EXCLUSIVE_INCLUSIVE
    )
    text = spannable
    movementMethod = LinkMovementMethod.getInstance()
}