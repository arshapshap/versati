package com.arshapshap.versati.feature.auth.impl.presentation.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arshapshap.versati.feature.auth.impl.R
import com.arshapshap.versati.feature.auth.impl.presentation.common.ui.EmailTextField
import com.arshapshap.versati.feature.auth.impl.presentation.common.ui.PasswordTextField
import com.arshapshap.versati.feature.auth.impl.presentation.signin.contract.SignInState

@Composable
internal fun SignInContent(
    state: SignInState,
    viewModel: SignInViewModel
) {
    SignInContent(
        state = state,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onSignIn = viewModel::signIn,
        onSwitchToRegister = viewModel::navigateToRegistration
    )
}

@Composable
private fun SignInContent(
    state: SignInState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignIn: () -> Unit,
    onSwitchToRegister: () -> Unit
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
            .padding(horizontal = 16.dp)
            .alpha(if (state.loading || state.success) 0.5f else 1f)
    ) {
        Text(
            text = stringResource(R.string.authorization_label),
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Black
        )
        Form(
            modifier = Modifier.fillMaxSize(),
            state = state,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onSignIn = onSignIn,
            onSwitchToRegister = onSwitchToRegister
        )
    }
}

@Composable
private fun Form(
    modifier: Modifier = Modifier,
    state: SignInState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignIn: () -> Unit,
    onSwitchToRegister: () -> Unit
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
            onValueChange = onEmailChange,
            enabled = !state.loading && !state.success
        )
        PasswordTextField(text = state.password,
            isError = state.showPasswordFieldError,
            errorText = state.signInError?.res?.let { stringResource(id = it) }
                ?: state.passwordFieldError?.res?.let { stringResource(id = it) }
                ?: "",
            modifier = Modifier
                .padding(vertical = 8.dp),
            onValueChange = onPasswordChange,
            enabled = !state.loading && !state.success
        )
        Button(
            onClick = onSignIn,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(vertical = 8.dp),
            enabled = !state.loading && !state.success
        ) {
            Text(
                text = stringResource(R.string.sign_in),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }
        HintToRegister(
            modifier = Modifier.fillMaxWidth(),
            onClick = onSwitchToRegister
        )
    }
}

@Composable
fun HintToRegister(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onBackground
            )
        ) {
            append(stringResource(R.string.don_t_you_have_an_account))
        }
        append(" ")
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        ) {
            append(stringResource(R.string.register))
        }
    }
    Column(modifier = modifier) {
        ClickableText(
            text = text,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { onClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInContentPreview() {
    val state = SignInState()
    SignInContent(
        state = state,
        onEmailChange = { },
        onPasswordChange = { },
        onSignIn = { },
        onSwitchToRegister = { }
    )
}