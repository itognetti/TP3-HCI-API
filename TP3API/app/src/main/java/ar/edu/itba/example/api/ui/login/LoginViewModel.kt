package ar.edu.itba.example.api.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.example.api.data.model.Error
import ar.edu.itba.example.api.data.network.DataSourceException
import ar.edu.itba.example.api.data.repository.UserRepository
import ar.edu.itba.example.api.ui.main.MainUiState
import ar.edu.itba.example.api.util.SessionManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository
) : ViewModel() {

    var uiState by mutableStateOf(MainUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set

    fun login(username: String, password: String) = runOnViewModelScope(
        { userRepository.login(username, password) },
        { state, _ -> state.copy(isAuthenticated = true) }
    )

    fun logout() = runOnViewModelScope(
        { userRepository.logout() },
        { state, _ ->
            state.copy(
                isAuthenticated = false,
                currentUser = null,
                currentSport = null,
                sports = null
            )
        }
    )

    fun getCurrentUser() = runOnViewModelScope(
        { userRepository.getCurrentUser(uiState.currentUser == null) },
        { state, response -> state.copy(currentUser = response) }
    )

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (MainUiState, R) -> MainUiState
    ): Job = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, error = null)
        runCatching {
            block()
        }.onSuccess { response ->
            uiState = updateState(uiState, response).copy(isFetching = false)
        }.onFailure { e ->
            uiState = uiState.copy(isFetching = false, error = handleError(e))
        }
    }

    private fun handleError(e: Throwable): Error {
        return if (e is DataSourceException) {
            Error(e.code, e.message ?: "", e.details)
        } else {
            Error(null, e.message ?: "", null)
        }
    }
}