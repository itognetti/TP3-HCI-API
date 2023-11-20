package ar.edu.itba.example.api.ui.details

import ar.edu.itba.example.api.data.model.Cycle
import ar.edu.itba.example.api.data.model.User
import ar.edu.itba.example.api.data.model.Error

data class DetailsUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val routineCycles: List<Cycle>? = null,
    val error: Error? = null
)

val DetailsUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val DetailsUiState.canGetAllRoutineCycles: Boolean get() = isAuthenticated