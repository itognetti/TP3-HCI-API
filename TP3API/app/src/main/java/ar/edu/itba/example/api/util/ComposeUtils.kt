package ar.edu.itba.example.api.util

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import ar.edu.itba.example.api.ui.MyApplication

@Composable
fun getViewModelFactory(defaultArgs: Bundle? = null): ViewModelFactory {
    val application = (LocalContext.current.applicationContext as MyApplication)
    val sessionManager = application.sessionManager
    val userRepository = application.userRepository
    val sportRepository = application.sportRepository
    val routineRepository = application.routineRepository
    val routineCyclesRepository = application.routineCyclesRepository
    val cycleExercisesRepository = application.cycleExercisesRepository
    return ViewModelFactory(
        sessionManager,
        userRepository,
        sportRepository,
        routineRepository,
        routineCyclesRepository,
        cycleExercisesRepository,
        LocalSavedStateRegistryOwner.current,
        defaultArgs
    )
}