package ar.edu.itba.example.api.ui.main

import ar.edu.itba.example.api.data.model.Category
import ar.edu.itba.example.api.data.model.Cycle
import ar.edu.itba.example.api.data.model.CycleData
import ar.edu.itba.example.api.data.model.CycleExercise
import ar.edu.itba.example.api.data.model.Error
import ar.edu.itba.example.api.data.model.Routine
import ar.edu.itba.example.api.data.model.SimpleExercise
import ar.edu.itba.example.api.data.model.Sport
import ar.edu.itba.example.api.data.model.User
import ar.edu.itba.example.api.ui.components.FilterVariant

data class MainUiState(

    // User
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val error: Error? = null,

    // Sports
    val sports: List<Sport>? = null,
    val currentSport: Sport? = null,

    // Categories
    val categories: List<Category>? = null,
    val currentCategory: Category? = null,

    // Exercises
    val exercises: List<SimpleExercise>? = null,
    val currentExercise: SimpleExercise? = null,

    // Routines
    val routines: List<Routine>? = null,
    val currentRoutine: Routine? = null,
    val userRoutines: List<Routine>? = null,
    val routineCycles: List<Cycle> = emptyList(),
    val cycleExercises: List<CycleExercise> = emptyList(),
    var cycleDataList: List<CycleData> = emptyList(),
    val orderBy: Int = 0,
    val filters: List<FilterVariant> = listOf(
        FilterVariant.DateAsc,
        FilterVariant.DateDesc,
        FilterVariant.RatingAsc,
        FilterVariant.RatingDesc,
        FilterVariant.DifficultyAsc,
        FilterVariant.DifficultyDesc
    ),

    // Executing
    val routineSize: Int = 1,
    val exerciseCount: Int = 0,
    val seriesRepetitionsCount: Int = 1,

    val currentExerciseTimer: Int = 0,
    val pauseExecuting: Boolean = false,

    val currentExerciseExecuting: CycleExercise? = null,
    val currentExerciseExecutingIndex: Int = 0,
    val currentSeriesExecuting: CycleData? = null,
    val currentSeriesExecutingIndex: Int = 0,
    val nextExerciseExecuting: CycleExercise? = null,

    val finishedExecuting: Boolean = false
    )

val MainUiState.canGetCurrentUser: Boolean get() = isAuthenticated

val MainUiState.canGetAllSports: Boolean get() = isAuthenticated

val MainUiState.canGetCurrentSport: Boolean get() = isAuthenticated && currentSport != null

val MainUiState.canAddSport: Boolean get() = isAuthenticated && currentSport == null

val MainUiState.canModifySport: Boolean get() = isAuthenticated && currentSport != null

val MainUiState.canDeleteSport: Boolean get() = canModifySport

/*

val MainUiState.canGetAllCategories: Boolean get() = isAuthenticated

val MainUiState.canGetCurrentCategory: Boolean get() = isAuthenticated && currentCategory != null

val MainUiState.canGetAllExercises: Boolean get() = isAuthenticated

val MainUiState.canGetCurrentExercise: Boolean get() = isAuthenticated && currentExercise != null

val MainUiState.canGetAllRoutines: Boolean get() = isAuthenticated

val MainUiState.canGetCurrentRoutine: Boolean get() = isAuthenticated && currentRoutine != null

*/