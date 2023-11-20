package ar.edu.itba.example.api.ui.cycleDetails

import ar.edu.itba.example.api.data.model.CycleExercise
import ar.edu.itba.example.api.data.model.User
import ar.edu.itba.example.api.data.model.Error

data class CycleDetailsUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val cycleExercises: List<CycleExercise>? = null,
    val error: Error? = null
)

val CycleDetailsUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val CycleDetailsUiState.canGetAllCycleExercises: Boolean get() = isAuthenticated