package com.arshapshap.versati.feature.auth.impl.presentation.register

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshapshap.versati.feature.auth.api.domain.model.RegisterError
import com.arshapshap.versati.feature.auth.api.domain.usecase.RegisterUseCase
import com.arshapshap.versati.feature.auth.impl.presentation.common.contract.EmailFieldError
import com.arshapshap.versati.feature.auth.impl.presentation.common.contract.PasswordFieldError
import com.arshapshap.versati.feature.auth.impl.presentation.register.contract.RegisterErrorWithMessage
import com.arshapshap.versati.feature.auth.impl.presentation.register.contract.RegisterSideEffect
import com.arshapshap.versati.feature.auth.impl.presentation.register.contract.RegisterState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.SimpleContext
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

private typealias IntentContext = SimpleSyntax<RegisterState, RegisterSideEffect>
private typealias ReduceContext = SimpleContext<RegisterState>

internal class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ContainerHost<RegisterState, RegisterSideEffect>, ViewModel() {

    override val container =
        viewModelScope.container<RegisterState, RegisterSideEffect>(RegisterState())

    fun register() = intent {
        if (!checkIfEmailAndPasswordValid()) return@intent

        reduce { state.copy(loading = true) }
        val result = registerUseCase(state.email, state.password)
        reduce { state.copy(loading = false) }

        if (result.isSuccessful) {
            reduce { state.copy(success = true) }
            postSideEffect(RegisterSideEffect.NavigateToAccount)
        } else {
            handleRegisterError(result.error!!)
        }
    }

    fun navigateToSignIn() = intent {
        postSideEffect(RegisterSideEffect.NavigateToSignIn)
    }

    @OptIn(OrbitExperimental::class)
    fun updateEmail(email: String) = blockingIntent {
        reduce {
            if (state.registerError == null)
                state.copy(
                    email = email,
                    showEmailFieldError = false,
                    emailFieldError = null
                )
            else
                getStateWithNoErrors()
                    .copy(email = email)
        }
    }

    @OptIn(OrbitExperimental::class)
    fun updatePassword(password: String) = blockingIntent {
        reduce {
            if (state.registerError == null)
                state.copy(
                    password = password,
                    showPasswordFieldError = false,
                    passwordFieldError = null
                )
            else
                getStateWithNoErrors()
                    .copy(password = password)
        }
    }

    private suspend fun IntentContext.checkIfEmailAndPasswordValid(): Boolean {
        val emailFieldError = state.email.checkEmail()
        val passwordFieldError = state.password.checkPassword()
        if (emailFieldError != null || passwordFieldError != null) {
            reduce {
                state.copy(
                    showEmailFieldError = emailFieldError != null,
                    showPasswordFieldError = passwordFieldError != null,
                    emailFieldError = emailFieldError,
                    passwordFieldError = passwordFieldError
                )
            }
        }
        return emailFieldError == null && passwordFieldError == null
    }

    private suspend fun IntentContext.handleRegisterError(error: RegisterError) {
        when (error) {
            RegisterError.EmailAlreadyInUse -> reduce { getStateWithEmailError(EmailFieldError.EmailAlreadyInUse) }
            RegisterError.InvalidEmail -> reduce { getStateWithEmailError(EmailFieldError.InvalidEmail) }
            RegisterError.WeakPassword -> reduce { getStateWithPasswordError(PasswordFieldError.WeakPassword) }
            RegisterError.UnknownError -> reduce {
                getStateWithError(
                    RegisterErrorWithMessage.UnknownError,
                    highlightError = false
                )
            }
        }
    }

    private fun ReduceContext.getStateWithError(
        registerError: RegisterErrorWithMessage,
        highlightError: Boolean = true
    ) = state.copy(
        showEmailFieldError = highlightError,
        showPasswordFieldError = highlightError,
        emailFieldError = null,
        passwordFieldError = null,
        registerError = registerError
    )

    private fun ReduceContext.getStateWithEmailError(
        emailFieldError: EmailFieldError
    ) = state.copy(
        showEmailFieldError = true,
        emailFieldError = emailFieldError
    )

    private fun ReduceContext.getStateWithPasswordError(
        passwordFieldError: PasswordFieldError
    ) = state.copy(
        showPasswordFieldError = true,
        passwordFieldError = passwordFieldError
    )

    private fun ReduceContext.getStateWithNoErrors() = state.copy(
        showEmailFieldError = false,
        showPasswordFieldError = false,
        emailFieldError = null,
        passwordFieldError = null,
        registerError = null
    )

    private fun String.checkEmail(): EmailFieldError? {
        if (TextUtils.isEmpty(this))
            return EmailFieldError.EmptyEmail
        if (!Patterns.EMAIL_ADDRESS.matcher(this).matches())
            return EmailFieldError.InvalidEmail
        return null
    }

    private fun String.checkPassword(): PasswordFieldError? {
        if (TextUtils.isEmpty(this))
            return PasswordFieldError.EmptyPassword
        if (this.length < 6)
            return PasswordFieldError.WeakPassword
        return null
    }
}