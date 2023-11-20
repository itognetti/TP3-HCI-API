package ar.edu.itba.example.api.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.example.api.data.network.DataSourceException
import ar.edu.itba.example.api.data.repository.RoutineRepository
import ar.edu.itba.example.api.data.repository.UserRepository
import ar.edu.itba.example.api.util.SessionManager
import kotlinx.coroutines.launch
import ar.edu.itba.example.api.data.model.Error

class HomeViewModel (
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val routineRepository: RoutineRepository,
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
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

    fun getCurrentUser() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            error = null
        )
        runCatching {
            userRepository.getCurrentUser(uiState.currentUser == null)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentUser = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                error = handleError(e),
                isFetching = false)
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