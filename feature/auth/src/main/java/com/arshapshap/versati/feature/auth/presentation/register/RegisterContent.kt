package com.arshapshap.versati.feature.auth.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arshapshap.versati.feature.auth.R
import com.arshapshap.versati.feature.auth.presentation.common.contract.PasswordFieldError
import com.arshapshap.versati.feature.auth.presentation.common.ui.EmailTextField
import com.arshapshap.versati.feature.auth.presentation.common.ui.PasswordTextField
import com.arshapshap.versati.feature.auth.presentation.register.contract.RegisterState

@Composable
internal fun RegisterContent(state: RegisterState, screenModel: RegisterScreenModel) {
    RegisterContent(
        state = state,
        onUpdateEmail = screenModel::updateEmail,
        onUpdatePassword = screenModel::updatePassword,
        onRegister = screenModel::register
    )
}

@Composable
private fun RegisterContent(
    state: RegisterState,
    onUpdateEmail: (String) -> Unit,
    onUpdatePassword: (String) -> Unit,
    onRegister: () -> Unit
) {
    if (state.loading)
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    if (state.success)
        Box(modifier = Modifier.fillMaxSize()) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .alpha(if (state.loading || state.success) 0.5f else 1f)
    ) {
        Text(
            text = stringResource(R.string.registration_label),
            fontSize = 50.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Black
        )
        Form(
            modifier = Modifier.fillMaxSize(),
            state = state,
            onUpdateEmail = onUpdateEmail,
            onUpdatePassword = onUpdatePassword,
            onSignIn = onRegister,
        )
    }
}

@Composable
private fun Form(
    modifier: Modifier = Modifier,
    state: RegisterState,
    onUpdateEmail: (String) -> Unit,
    onUpdatePassword: (String) -> Unit,
    onSignIn: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailTextField(text = state.email,
            isError = state.showEmailFieldError,
            errorText = state.emailFieldError?.res?.let { stringResource(id = it) } ?: "",
            modifier = Modifier
                .padding(vertical = 8.dp),
            onValueChange = onUpdateEmail,
            enabled = !state.loading && !state.success
        )
        PasswordTextField(text = state.password,
            isError = state.showPasswordFieldError,
            errorText = state.registerError?.res?.let { stringResource(id = it) }
                ?: state.passwordFieldError?.res?.let { stringResource(id = it) }
                ?: "",
            modifier = Modifier
                .padding(vertical = 8.dp),
            onValueChange = onUpdatePassword,
            enabled = !state.loading && !state.success
        )

        Button(
            onClick = onSignIn,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            enabled = !state.loading && !state.success
        ) {
            Text(stringResource(R.string.register), fontSize = 25.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInContentPreview() {
    val state = RegisterState(
        showEmailFieldError = true,
        showPasswordFieldError = true,
        passwordFieldError = PasswordFieldError.EmptyPassword
    )
    RegisterContent(
        state = state,
        onUpdateEmail = { },
        onUpdatePassword = { },
        onRegister = { }
    )
}