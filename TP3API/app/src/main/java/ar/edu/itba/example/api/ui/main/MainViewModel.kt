package ar.edu.itba.example.api.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.itba.example.api.data.DataSourceException
import ar.edu.itba.example.api.data.model.Category
import ar.edu.itba.example.api.data.model.CycleData
import ar.edu.itba.example.api.data.model.Error
import ar.edu.itba.example.api.data.model.Sport
import ar.edu.itba.example.api.data.repository.CategoryRepository
import ar.edu.itba.example.api.data.repository.CycleExercisesRepository
import ar.edu.itba.example.api.data.repository.ExerciseRepository
import ar.edu.itba.example.api.data.repository.RoutineCyclesRepository
import ar.edu.itba.example.api.data.repository.RoutineRepository
import ar.edu.itba.example.api.data.repository.SportRepository
import ar.edu.itba.example.api.data.repository.UserRepository
import ar.edu.itba.example.api.util.SessionManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(
    sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val sportRepository: SportRepository,
    private val exerciseRepository: ExerciseRepository,
    private val routineRepository: RoutineRepository,
    private val categoryRepository: CategoryRepository,
    private val routineCyclesRepository: RoutineCyclesRepository,
    private val cycleExercisesRepository: CycleExercisesRepository
) : ViewModel() {

    var uiState by mutableStateOf(MainUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set


    fun setupViewModel(){
        getRoutines()
        getCurrentUser()
    }

    fun login(username: String, password: String) = runOnViewModelScope(
        { userRepository.login(username, password) },
        { state, _ -> state.copy(isAuthenticated = true) }
    )

    fun logout() = runOnViewModelScope(
        { userRepository.logout() },
        { state, _ ->
            state.copy(
                isAuthenticated = false,
                currentUser = null,
                currentSport = null,
                sports = null
            )
        }
    )

    fun getCurrentUser() = runOnViewModelScope(
        { userRepository.getCurrentUser(uiState.currentUser == null) },
        { state, response -> state.copy(currentUser = response) }
    )

    fun getSports() = runOnViewModelScope(
        { sportRepository.getSports(true) },
        { state, response -> state.copy(sports = response) }
    )

    fun getSport(sportId: Int) = runOnViewModelScope(
        { sportRepository.getSport(sportId) },
        { state, response -> state.copy(currentSport = response) }
    )

    fun addOrModifySport(sport: Sport) = runOnViewModelScope(
        {
            if (sport.id == null)
                sportRepository.addSport(sport)
            else
                sportRepository.modifySport(sport)
        },
        { state, response ->
            state.copy(
                currentSport = response,
                sports = null
            )
        }
    )

    fun deleteSport(sportId: Int) = runOnViewModelScope(
        { sportRepository.deleteSport(sportId) },
        { state, response ->
            state.copy(
                currentSport = null,
                sports = null
            )
        }
    )

    fun getCurrentUserRoutines() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            error = null
        )
        runCatching {
            userRepository.getCurrentUserRoutines()
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                userRoutines = response
            )
            uiState.routines.orEmpty().forEach { routine ->
                if( uiState.userRoutines.orEmpty()
                        .find { it.id == routine.id } != null){
                    routine.fromCUser = true
                }
            }
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                error = handleError(e),
                isFetching = false)
        }
    }

    fun getRoutines() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            error = null
        )
        runCatching {
            routineRepository.getRoutines(true)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                routines = response
            )
            getCurrentUserRoutines()
        }.onFailure { e ->
            uiState = uiState.copy(
                error = handleError(e),
                isFetching = false
            )
        }
    }

    fun getRoutine(routineId: Int) = runOnViewModelScope(
        {routineRepository.getRoutine(routineId)},
        {state, response ->
            state.copy(currentRoutine = response)
        }
    )

    fun getFilteredRoutines(){
        getFilteredRoutines(uiState.filters[uiState.orderBy].order, uiState.filters[uiState.orderBy].direction, uiState.orderBy)
    }

    fun getFilteredRoutines(order: String = "name", dir: String = "asc", index: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            error = null
        )
        runCatching {
            routineRepository.getFilteredRoutines(order, dir)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                routines = response,
            )
            getCurrentUserRoutines()
            uiState = uiState.copy(orderBy = index)
        }.onFailure { e ->
            uiState = uiState.copy(
                isFetching = false,
                error = handleError(e)
            )
        }
    }

    fun getExercises() = runOnViewModelScope(
        {exerciseRepository.getExercises(true)},
        {state, response ->
            state.copy(exercises = response)
        }
    )

    fun getExercise(exerciseId: Int) = runOnViewModelScope(
        {exerciseRepository.getExercise(exerciseId)},
        {state, response ->
            state.copy(currentExercise = response)
        }
    )

    fun getCategories() = runOnViewModelScope(
        {categoryRepository.getCategories(true)},
        {state, response ->
            state.copy(categories = response)
        }
    )

    fun getCategory(categoryId: Int) = runOnViewModelScope(
        {categoryRepository.getCategory(categoryId)},
        {state, response ->
            state.copy(currentCategory = response)
        }
    )

    fun addCategory(category: Category) = runOnViewModelScope(
        {categoryRepository.addCategory(category)},
        {state, response ->
            state.copy(currentCategory = response)
        }
    )

    fun getRoutinesCycle(routineId: Int) = runOnViewModelScope(
        {routineCyclesRepository.getRoutineCycles(routineId, true)},
        {state, response ->
            state.copy(routineCycles = response)
        }
    )

    fun getCycleExercises(cycleId: Int) = runOnViewModelScope(
        {cycleExercisesRepository.getCycleExercises(cycleId, true)},
        {state, response ->
            state.copy(cycleExercises = response)
        }
    )

    fun getRoutineDetails(routineId: Int) = runOnViewModelScope(
        {getRoutinesCycle(routineId).join()

        for(cycle in uiState.routineCycles){
            getCycleExercises(cycle.id).join()

            uiState.cycleDataList = uiState.cycleDataList.plus(CycleData(cycle.name, cycle.repetitions,
                uiState.cycleExercises
            ))
        }},
        { state, _ -> state}
    )

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (MainUiState, R) -> MainUiState
    ): Job = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, error = null)
        runCatching {
            block()
        }.onSuccess { response ->
            uiState = updateState(uiState, response).copy(isFetching = false)
        }.onFailure { e ->
            uiState = uiState.copy(isFetching = false, error = handleError(e))
        }
    }

    private fun handleError(e: Throwable): Error {
        return if (e is DataSourceException) {
            Error(e.code, e.message ?: "", e.details)
        } else {
            Error(null, e.message ?: "", null)
        }
    }
}