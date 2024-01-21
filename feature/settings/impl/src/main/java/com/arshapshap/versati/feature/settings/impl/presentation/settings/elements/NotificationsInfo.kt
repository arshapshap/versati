package com.arshapshap.versati.feature.settings.impl.presentation.settings.elements

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arshapshap.versati.core.designsystem.elements.ConfirmationAlertDialog
import com.arshapshap.versati.core.designsystem.elements.OutlinedButtonWithIcon
import com.arshapshap.versati.feature.settings.impl.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun NotificationsInfo(
    modifier: Modifier = Modifier
) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU)
        return

    val cameraPermissionState =
        rememberPermissionState(android.Manifest.permission.POST_NOTIFICATIONS)
    val openDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (openDialog.value) {
        PermissionDialog(openDialog = openDialog) {
            openSettings(context)
        }
    }
    NotificationsInfo(
        modifier = modifier,
        hasPermission = cameraPermissionState.status.isGranted
    ) {
        if (cameraPermissionState.status.shouldShowRationale)
            cameraPermissionState.launchPermissionRequest()
        else
            openDialog.value = true
    }
}

@Composable
private fun NotificationsInfo(
    modifier: Modifier = Modifier,
    hasPermission: Boolean,
    onRequestPermission: () -> Unit
) {
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
            Text(
                text = stringResource(R.string.explanation_of_notification_permission),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(8.dp))
            OutlinedButtonWithIcon(
                modifier = Modifier.fillMaxWidth(),
                onClick = onRequestPermission,
                text = if (hasPermission) stringResource(R.string.permission_has_been_received)
                else stringResource(R.string.grant_permission),
                painter = if (hasPermission) painterResource(id = R.drawable.ic_notifications_active)
                else painterResource(id = R.drawable.ic_notifications),
                enabled = !hasPermission
            )
        }
    }
}

@Composable
private fun PermissionDialog(
    openDialog: MutableState<Boolean>,
    onConfirm: () -> Unit
) {
    ConfirmationAlertDialog(
        onDismissRequest = { openDialog.value = false },
        onConfirmation = {
            openDialog.value = false
            onConfirm()
        },
        dialogTitle = stringResource(R.string.granting_permission),
        dialogText = stringResource(R.string.warning_about_opening_smartphone_settings),
        icon = painterResource(id = R.drawable.ic_notifications_active)
    )
}

private fun openSettings(context: Context) {
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts(
            "package", (context as Activity).packageName, null
        )
    )
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
private fun HavePermissionsPreview() {
    NotificationsInfo(
        modifier = Modifier,
        hasPermission = true
    ) { }
}

@Preview(showBackground = true)
@Composable
private fun NoPermissionsPreview() {
    NotificationsInfo(
        modifier = Modifier,
        hasPermission = false
    ) { }
}