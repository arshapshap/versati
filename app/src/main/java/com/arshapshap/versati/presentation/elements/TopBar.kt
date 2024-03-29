package com.arshapshap.versati.presentation.elements

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.arshapshap.versati.R
import com.arshapshap.versati.core.navigation.state.AppBarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    state: AppBarState = AppBarState(),
    onSettingsClick: () -> Unit = { }
) {
    TopAppBar(
        title = {
            Text(
                text = state.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if (state.showArrowBack) {
                val backPressDispatcher = LocalOnBackPressedDispatcherOwner.current
                IconButton(
                    onClick = {
                        backPressDispatcher?.onBackPressedDispatcher?.onBackPressed()
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                )
            } else {
                IconButton(
                    onClick = onSettingsClick,
                    content = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = stringResource(com.arshapshap.versati.feature.settings.impl.R.string.settings)
                        )
                    }
                )
            }
        },
        actions = {
            state.actions?.invoke(this)
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun Preview() {
    val state = AppBarState(
        title = "Main"
    )
    TopBar(
        state = state
    )
}