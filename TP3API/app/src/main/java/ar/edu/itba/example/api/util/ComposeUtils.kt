package ar.edu.itba.example.api.util

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import ar.edu.itba.example.api.ui.main.MyApplication

@Composable
fun getViewModelFactory(defaultArgs: Bundle? = null): ViewModelFactory {
    val application = (LocalContext.current.applicationContext as MyApplication)
    val sessionManager = application.sessionManager
    val userRepository = application.userRepository
    val sportRepository = application.sportRepository
    val exerciseRepository = application.exerciseRepository
    val routineRepository = application.routineRepository
    val categoryRepository = application.categoryRepository
    val routineCyclesRepository = application.routineCyclesRepository
    val cycleExercisesRepository = application.cycleExercisesRepository
    return ViewModelFactory(
        sessionManager,
        userRepository,
        sportRepository,
        exerciseRepository,
        routineRepository,
        categoryRepository,
        routineCyclesRepository,
        cycleExercisesRepository,
        LocalSavedStateRegistryOwner.current,
        defaultArgs
    )
}