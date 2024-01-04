package com.arshapshap.versati.feature.auth.presentation.signin

import android.text.TextUtils
import android.util.Patterns
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.arshapshap.versati.feature.auth.domain.model.SignInError
import com.arshapshap.versati.feature.auth.domain.usecase.SignInUseCase
import com.arshapshap.versati.feature.auth.presentation.common.contract.EmailFieldError
import com.arshapshap.versati.feature.auth.presentation.common.contract.PasswordFieldError
import com.arshapshap.versati.feature.auth.presentation.signin.contract.SignInErrorWithMessage
import com.arshapshap.versati.feature.auth.presentation.signin.contract.SignInState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.SimpleContext
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

private typealias IntentContext = SimpleSyntax<SignInState, Nothing>
private typealias ReduceContext = SimpleContext<SignInState>

internal class SignInScreenModel(
    private val signInUseCase: SignInUseCase
) : ContainerHost<SignInState, Nothing>, ScreenModel {

    override val container = coroutineScope.container<SignInState, Nothing>(SignInState())

    fun signIn() = intent {
        if (!checkIfEmailAndPasswordValid()) return@intent

        reduce { state.copy(loading = true) }
        val result = signInUseCase(state.email, state.password)
        reduce { state.copy(loading = false) }

        if (result.isSuccessful) {
            reduce { state.copy(success = true) }
            //TODO("Уйти с экрана авторизации через несколько секунд")
        } else {
            handleSignInError(result.error!!)
        }
    }

    fun goToRegistration() = intent {
        //TODO("Перейти к регистрации")
    }

    @OptIn(OrbitExperimental::class)
    fun updateEmail(email: String) = blockingIntent {
        reduce {
            if (state.signInError == null)
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
            if (state.signInError == null)
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

    private suspend fun IntentContext.handleSignInError(error: SignInError) {
        when (error) {
            SignInError.WrongPassword -> reduce { getStateWithError(SignInErrorWithMessage.WrongEmailOrPassword) }
            SignInError.InvalidEmail -> reduce { getStateWithEmailError(EmailFieldError.InvalidEmail) }
            SignInError.UserDisabled -> reduce { getStateWithError(SignInErrorWithMessage.UserDisabled) }
            SignInError.UserNotFound -> reduce { getStateWithError(SignInErrorWithMessage.UserNotFound) }
            SignInError.UnknownError -> reduce {
                getStateWithError(
                    SignInErrorWithMessage.UnknownError,
                    highlightError = false
                )
            }
        }
    }

    private fun ReduceContext.getStateWithError(
        signInError: SignInErrorWithMessage,
        highlightError: Boolean = true
    ) = state.copy(
        showEmailFieldError = highlightError,
        showPasswordFieldError = highlightError,
        emailFieldError = null,
        passwordFieldError = null,
        signInError = signInError
    )

    private fun ReduceContext.getStateWithEmailError(
        emailFieldError: EmailFieldError
    ) = state.copy(
        showEmailFieldError = true,
        emailFieldError = emailFieldError
    )

    private fun ReduceContext.getStateWithNoErrors() = state.copy(
        showEmailFieldError = false,
        showPasswordFieldError = false,
        emailFieldError = null,
        passwordFieldError = null,
        signInError = null
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
        return null
    }
}
