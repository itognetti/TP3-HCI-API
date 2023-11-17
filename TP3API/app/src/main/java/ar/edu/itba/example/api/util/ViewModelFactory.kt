package ar.edu.itba.example.api.util

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ar.edu.itba.example.api.data.repository.CategoryRepository
import ar.edu.itba.example.api.data.repository.CycleExercisesRepository
import ar.edu.itba.example.api.data.repository.ExerciseRepository
import ar.edu.itba.example.api.data.repository.RoutineCyclesRepository
import ar.edu.itba.example.api.data.repository.RoutineRepository
import ar.edu.itba.example.api.data.repository.SportRepository
import ar.edu.itba.example.api.data.repository.UserRepository
import ar.edu.itba.example.api.ui.main.MainViewModel

class ViewModelFactory constructor(
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val sportRepository: SportRepository,
    private val exerciseRepository: ExerciseRepository,
    private val routineRepository: RoutineRepository,
    private val categoryRepository: CategoryRepository,
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
                MainViewModel(sessionManager, userRepository, sportRepository, exerciseRepository,
                    routineRepository, categoryRepository, routineCyclesRepository, cycleExercisesRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}