package ar.edu.itba.example.api.ui.home

import ar.edu.itba.example.api.data.model.Routine
import ar.edu.itba.example.api.data.model.User
import ar.edu.itba.example.api.data.model.Error

data class HomeUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val routines: List<Routine>? = null,
    val error: Error? = null
)

val HomeUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val HomeUiState.canGetAllRoutines: Boolean get() = isAuthenticated