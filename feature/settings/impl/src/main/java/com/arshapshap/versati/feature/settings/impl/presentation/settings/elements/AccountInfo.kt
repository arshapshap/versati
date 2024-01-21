package com.arshapshap.versati.feature.settings.impl.presentation.settings.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.arshapshap.versati.feature.settings.impl.R

@Composable
internal fun AccountInfo(
    modifier: Modifier = Modifier,
    user: User?,
    showDialogToConfirmLogOut: Boolean,
    loading: Boolean,
    onLogInClick: () -> Unit,
    onLogOutClick: () -> Unit,
    onCancelLogOut: () -> Unit,
    onLogOut: () -> Unit
) {
    if (showDialogToConfirmLogOut) {
        ConfirmationAlertDialog(
            onDismissRequest = onCancelLogOut,
            onConfirmation = onLogOut,
            dialogTitle = stringResource(R.string.log_out),
            dialogText = stringResource(R.string.are_you_sure_you_want_to_log_out_of_your_account),
            icon = painterResource(id = R.drawable.ic_log_out)
        )
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (loading) {
                CircularProgressIndicator()
            } else if (user != null)
                AuthorizedUserInfo(user = user, onLogOutClick = onLogOutClick)
            else
                UnauthorizedUserInfo(onLogInClick = onLogInClick)
        }
    }
}

@Composable
private fun AuthorizedUserInfo(
    user: User,
    onLogOutClick: () -> Unit
) {
    Text(text = stringResource(R.string.you_are_logged_in))
    Text(
        text = user.email,
        style = MaterialTheme.typography.titleMedium,
    )
    Spacer(modifier = Modifier.padding(8.dp))
    OutlinedButton(
        onClick = onLogOutClick,
        modifier = Modifier
            .height(ButtonHeight)
            .fillMaxWidth()
    ) {
        Text(text = stringResource(id = R.string.log_out))
        Icon(
            painter = painterResource(id = R.drawable.ic_log_out),
            contentDescription = null
        )
    }
}

@Composable
private fun UnauthorizedUserInfo(
    onLogInClick: () -> Unit
) {
    Text(text = stringResource(R.string.you_are_not_logged_in))
    Spacer(modifier = Modifier.padding(8.dp))
    OutlinedButton(
        onClick = onLogInClick,
        modifier = Modifier
            .height(ButtonHeight)
            .fillMaxWidth()
    ) {
        Text(text = stringResource(id = R.string.log_in))
        Icon(
            painter = painterResource(id = R.drawable.ic_log_in),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthorizedPreview() {
    AccountInfo(
        modifier = Modifier.height(170.dp),
        user = User(uid = "asd", email = "example@gmail.com"),
        showDialogToConfirmLogOut = false,
        loading = false,
        onLogInClick = { },
        onLogOutClick = { },
        onCancelLogOut = { },
        onLogOut = { },
    )
}

@Preview(showBackground = true)
@Composable
private fun NotAuthorizedPreview() {
    AccountInfo(
        modifier = Modifier.height(170.dp),
        user = null,
        showDialogToConfirmLogOut = false,
        loading = false,
        onLogInClick = { },
        onLogOutClick = { },
        onCancelLogOut = { },
        onLogOut = { },
    )
}

@Preview(showBackground = true)
@Composable
private fun LoadingPreview() {
    AccountInfo(
        modifier = Modifier.height(170.dp),
        user = null,
        showDialogToConfirmLogOut = false,
        loading = true,
        onLogInClick = { },
        onLogOutClick = { },
        onCancelLogOut = { },
        onLogOut = { },
    )
}