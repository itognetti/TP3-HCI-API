package ar.edu.itba.example.api.ui.profile

import ar.edu.itba.example.api.data.model.Error
import ar.edu.itba.example.api.data.model.User

data class ProfileUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val error: Error? = null
)

val ProfileUiState.canGetCurrentUser: Boolean get() = isAuthenticated