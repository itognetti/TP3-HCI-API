package ar.edu.itba.example.api.ui.explore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.example.api.data.repository.RoutineRepository
import ar.edu.itba.example.api.data.repository.UserRepository
import ar.edu.itba.example.api.util.SessionManager
import kotlinx.coroutines.launch
import ar.edu.itba.example.api.data.model.Error
import ar.edu.itba.example.api.data.network.DataSourceException
import kotlinx.coroutines.Job

class ExploreViewModel (
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val routineRepository: RoutineRepository,
) : ViewModel() {

    var uiState by mutableStateOf(ExploreUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set

    fun getRoutines(orderBy: String) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            error = null
        )
        runCatching {
            routineRepository.getRoutines(true, orderBy = orderBy)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                routines = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                error = handleError(e),
                isFetching = false)
        }
    }

    fun getCurrentUser() = runOnViewModelScope(
        {userRepository.getCurrentUser(uiState.currentUser == null)},
        {state, response -> state.copy(currentUser = response)}
    )

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (ExploreUiState, R) -> ExploreUiState
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