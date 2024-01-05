package com.arshapshap.versati.core.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

data class AppBarState(
    val title: String = "",
    val showArrowBack: Boolean = false,
    val actions: (@Composable RowScope.() -> Unit)? = null
)