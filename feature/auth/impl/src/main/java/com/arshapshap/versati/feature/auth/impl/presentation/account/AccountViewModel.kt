package com.arshapshap.versati.feature.auth.impl.presentation.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshapshap.versati.feature.auth.api.domain.usecase.GetCurrentUserUseCase
import com.arshapshap.versati.feature.auth.api.domain.usecase.LogOutUseCase
import com.arshapshap.versati.feature.auth.impl.presentation.account.contract.AccountSideEffect
import com.arshapshap.versati.feature.auth.impl.presentation.account.contract.AccountState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

internal class AccountViewModel(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logOutUseCase: LogOutUseCase
) : ContainerHost<AccountState, AccountSideEffect>, ViewModel() {

    override val container =
        viewModelScope.container<AccountState, AccountSideEffect>(AccountState())

    init {
        loadData()
    }

    fun logOutUnconfirmed() = intent {
        reduce { state.copy(showDialogToConfirmLogOut = true) }
    }

    fun cancelLogOut() = intent {
        reduce { state.copy(showDialogToConfirmLogOut = false) }
    }

    fun logOutConfirmed() = intent {
        logOutUseCase()
        navigateToSignIn()
    }

    private fun loadData() = intent {
        val user = getCurrentUserUseCase()
        if (user == null) {
            reduce { state.copy(showDialogToConfirmLogOut = false) }
            navigateToSignIn()
        } else {
            reduce { state.copy(user = user) }
        }
    }

    private fun navigateToSignIn() = intent {
        postSideEffect(AccountSideEffect.NavigateToSignIn)
    }
}