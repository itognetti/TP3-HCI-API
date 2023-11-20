package ar.edu.itba.example.api.ui.execution

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.example.api.data.model.Error
import ar.edu.itba.example.api.data.network.DataSourceException
import ar.edu.itba.example.api.data.repository.CycleExercisesRepository
import ar.edu.itba.example.api.data.repository.RoutineCyclesRepository
import ar.edu.itba.example.api.data.repository.RoutineRepository
import ar.edu.itba.example.api.data.repository.UserRepository
import ar.edu.itba.example.api.util.SessionManager
import kotlinx.coroutines.launch

class ExecutionViewModel (
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val routineRepository: RoutineRepository,
    private val routineCycleRepository: RoutineCyclesRepository,
    private val cycleExerciseRepository: CycleExercisesRepository
) : ViewModel() {

    var uiState by mutableStateOf(ExecutionUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set

    suspend fun getRoutineCycles(routineId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            error = null
        )
        runCatching {
            routineCycleRepository.getRoutineCycles(routineId, true)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                routineCycles = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                error = handleError(e),
                isFetching = false)
        }
    }

    fun getCycleExercises(cycleId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            error = null,
        )
        runCatching {
            cycleExerciseRepository.getCycleExercises(cycleId, true)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
            )
            if(response.isNotEmpty())
                uiState.cycleExercises?.put(cycleId, response)
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