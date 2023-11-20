package ar.edu.itba.example.api.util

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ar.edu.itba.example.api.data.repository.CycleExercisesRepository
import ar.edu.itba.example.api.data.repository.RoutineCyclesRepository
import ar.edu.itba.example.api.data.repository.RoutineRepository
import ar.edu.itba.example.api.data.repository.SportRepository
import ar.edu.itba.example.api.data.repository.UserRepository
import ar.edu.itba.example.api.ui.cycleDetails.CycleDetailsViewModel
import ar.edu.itba.example.api.ui.details.DetailsViewModel
import ar.edu.itba.example.api.ui.execution.ExecutionViewModel
import ar.edu.itba.example.api.ui.explore.ExploreViewModel
import ar.edu.itba.example.api.ui.home.HomeViewModel
import ar.edu.itba.example.api.ui.login.LoginViewModel
import ar.edu.itba.example.api.ui.main.MainViewModel
import ar.edu.itba.example.api.ui.profile.ProfileViewModel

class ViewModelFactory constructor(
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val sportRepository: SportRepository,
    private val routineRepository: RoutineRepository,
    private val routineCyclesRepository: RoutineCyclesRepository,
    private val cycleExercisesRepository: CycleExercisesRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(sessionManager, userRepository, sportRepository)
            isAssignableFrom(LoginViewModel::class.java) ->
                LoginViewModel(sessionManager, userRepository)
            isAssignableFrom(ProfileViewModel::class.java) ->
                ProfileViewModel(sessionManager, userRepository)
            isAssignableFrom(HomeViewModel::class.java) ->
                HomeViewModel(sessionManager, userRepository, routineRepository)
            isAssignableFrom(ExploreViewModel::class.java) ->
                ExploreViewModel(sessionManager, userRepository, routineRepository)
            isAssignableFrom(DetailsViewModel::class.java) ->
                DetailsViewModel(sessionManager, userRepository, routineCyclesRepository)
            isAssignableFrom(CycleDetailsViewModel::class.java) ->
                CycleDetailsViewModel(sessionManager, userRepository, cycleExercisesRepository)
            isAssignableFrom(ExecutionViewModel::class.java) ->
                ExecutionViewModel(sessionManager, userRepository, routineRepository, routineCyclesRepository, cycleExercisesRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}