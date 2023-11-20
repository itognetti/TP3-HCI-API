package ar.edu.itba.example.api.ui.explore

import ar.edu.itba.example.api.data.model.Error
import ar.edu.itba.example.api.data.model.Routine
import ar.edu.itba.example.api.data.model.User

data class ExploreUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val routines: List<Routine>? = null,
    val error: Error? = null
)

val ExploreUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val ExploreUiState.canGetAllRoutines: Boolean get() = isAuthenticated
val ExploreUiState.canGetAllReviews: Boolean get() = isAuthenticated