package ar.edu.itba.example.api.ui.main

import ar.edu.itba.example.api.data.model.Error
import ar.edu.itba.example.api.data.model.Sport
import ar.edu.itba.example.api.data.model.User

data class MainUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val error: Error? = null,
    val sports: List<Sport>? = null,
    val currentSport: Sport? = null,
)

val MainUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val MainUiState.canGetAllSports: Boolean get() = isAuthenticated
val MainUiState.canGetCurrentSport: Boolean get() = isAuthenticated && currentSport != null
val MainUiState.canAddSport: Boolean get() = isAuthenticated && currentSport == null
val MainUiState.canModifySport: Boolean get() = isAuthenticated && currentSport != null
val MainUiState.canDeleteSport: Boolean get() = canModifySport