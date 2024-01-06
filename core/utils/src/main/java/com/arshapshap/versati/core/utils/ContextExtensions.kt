package com.arshapshap.versati.core.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(text: String) =
    Toast
        .makeText(this, text, Toast.LENGTH_SHORT)
        .show()

fun Context.showToast(@StringRes resId: Int) =
    Toast
        .makeText(this, getString(resId), Toast.LENGTH_SHORT)
        .show()