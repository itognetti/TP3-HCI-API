package ar.edu.itba.example.api.ui.execution

import ar.edu.itba.example.api.data.model.Cycle
import ar.edu.itba.example.api.data.model.CycleExercise
import ar.edu.itba.example.api.data.model.Routine
import ar.edu.itba.example.api.data.model.Error


data class ExecutionUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val routines: List<Routine>? = null,
    val routineCycles: List<Cycle>? = null,
    val cycleExercises: HashMap<Int, List<CycleExercise>> = HashMap(),
    val error: Error? = null
)

val ExecutionUiState.canGetData: Boolean get() = isAuthenticated