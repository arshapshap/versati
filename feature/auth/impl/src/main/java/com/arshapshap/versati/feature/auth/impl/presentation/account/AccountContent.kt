package com.arshapshap.versati.feature.auth.impl.presentation.account

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arshapshap.versati.core.designsystem.elements.ConfirmationAlertDialog
import com.arshapshap.versati.core.designsystem.theme.ButtonHeight
import com.arshapshap.versati.feature.auth.api.domain.model.User
import com.arshapshap.versati.feature.auth.impl.R
import com.arshapshap.versati.feature.auth.impl.presentation.account.contract.AccountState

@Composable
internal fun AccountContent(
    state: AccountState,
    viewModel: AccountViewModel
) {
    AccountContent(
        state = state,
        onLogOutClick = viewModel::logOutUnconfirmed,
        onCancelLogOut = viewModel::cancelLogOut,
        onLogOut = viewModel::logOutConfirmed,
    )
}

@Composable
private fun AccountContent(
    state: AccountState,
    onLogOutClick: () -> Unit = { },
    onCancelLogOut: () -> Unit = { },
    onLogOut: () -> Unit = { }
) {
    if (state.showDialogToConfirmLogOut) {
        ConfirmationAlertDialog(
            onDismissRequest = onCancelLogOut,
            onConfirmation = onLogOut,
            dialogTitle = stringResource(R.string.log_out),
            dialogText = stringResource(R.string.are_you_sure_you_want_to_log_out_of_your_account),
            icon = painterResource(id = R.drawable.ic_logout)
        )
    }
    if (state.user == null) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        return
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))
        Text(text = stringResource(R.string.you_are_logged_in))
        Text(
            text = state.user.email,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.padding(16.dp))
        OutlinedButton(
            onClick = onLogOutClick,
            modifier = Modifier
                .height(ButtonHeight)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.log_out))
            Icon(
                painter = painterResource(id = R.drawable.ic_logout),
                contentDescription = null
            )
        }
        Spacer(Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val state = AccountState(
        user = User(uid = "asd", email = "example@gmail.com")
    )
    AccountContent(state = state)
}