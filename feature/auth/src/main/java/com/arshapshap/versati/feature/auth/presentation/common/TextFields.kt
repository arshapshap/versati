package com.arshapshap.versati.feature.auth.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
internal fun EmailTextField(
    modifier: Modifier = Modifier,
    text: String,
    isError: Boolean,
    errorText: String = "",
    enabled: Boolean,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
        trailingIcon = {
            if (isError) Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null
            )
        },
        modifier = modifier
            .fillMaxWidth(),
        supportingText = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = errorText,
                color = MaterialTheme.colorScheme.error
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        label = { Text(text = "Email") },
        placeholder = { Text(text = "Your email") },
        onValueChange = onValueChange,
        isError = isError,
        enabled = enabled
    )
}

@Composable
internal fun PasswordTextField(
    modifier: Modifier = Modifier,
    text: String,
    isError: Boolean,
    errorText: String = "",
    enabled: Boolean,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
        trailingIcon = {
            if (isError) Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null
            )
        },
        modifier = modifier
            .fillMaxWidth(),
        singleLine = true,
        supportingText = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = errorText,
                color = MaterialTheme.colorScheme.error
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation(),
        label = { Text(text = "Password") },
        placeholder = { Text(text = "Your password") },
        onValueChange = onValueChange,
        isError = isError,
        enabled = enabled
    )
}