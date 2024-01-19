package com.arshapshap.versati.presentation.elements

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arshapshap.versati.R

@Composable
internal fun Splash() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        val animatedSize = remember { Animatable(1f) }
        LaunchedEffect(animatedSize) {
            animatedSize.animateTo(
                2f,
                animationSpec = repeatable(
                    iterations = 1,
                    animation = tween(durationMillis = 10000, easing = LinearOutSlowInEasing)
                )
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .size(200.dp * animatedSize.value)
                .fillMaxSize(),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun SplashPreview() {
    Splash()
}