package ar.edu.itba.example.api.ui.login

import ar.edu.itba.example.api.data.model.User
import ar.edu.itba.example.api.data.model.Error

data class LoginUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val error: Error? = null
)

val LoginUiState.canGetCurrentUser: Boolean get() = isAuthenticated