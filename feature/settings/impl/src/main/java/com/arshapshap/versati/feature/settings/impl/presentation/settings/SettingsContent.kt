package com.arshapshap.versati.feature.settings.impl.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arshapshap.versati.feature.auth.api.domain.model.User
import com.arshapshap.versati.feature.settings.impl.presentation.settings.contract.SettingsState
import com.arshapshap.versati.feature.settings.impl.presentation.settings.elements.AccountInfo

@Composable
internal fun SettingsContent(
    state: SettingsState,
    viewModel: SettingsViewModel
) {
    SettingsContent(
        state = state,
        onLogInClick = viewModel::navigateToSignIn,
        onLogOutClick = viewModel::logOutUnconfirmed,
        onCancelLogOut = viewModel::cancelLogOut,
        onLogOut = viewModel::logOutConfirmed,
    )
}

@Composable
private fun SettingsContent(
    state: SettingsState,
    onLogInClick: () -> Unit = { },
    onLogOutClick: () -> Unit = { },
    onCancelLogOut: () -> Unit = { },
    onLogOut: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AccountInfo(
            modifier = Modifier.height(170.dp),
            user = state.user,
            loading = state.userLoading,
            showDialogToConfirmLogOut = state.showDialogToConfirmLogOut,
            onLogInClick = onLogInClick,
            onLogOutClick = onLogOutClick,
            onCancelLogOut = onCancelLogOut,
            onLogOut = onLogOut
        )
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val state = SettingsState(
        user = User(uid = "asd", email = "example@gmail.com")
    )
    SettingsContent(state = state)
}