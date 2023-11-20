package ar.edu.itba.example.api.ui

import android.app.Application
import ar.edu.itba.example.api.data.network.CycleExercisesRemoteDataSource
import ar.edu.itba.example.api.data.network.RoutineCyclesRemoteDataSource
import ar.edu.itba.example.api.data.network.RoutineRemoteDataSource
import ar.edu.itba.example.api.data.network.SportRemoteDataSource
import ar.edu.itba.example.api.data.network.UserRemoteDataSource
import ar.edu.itba.example.api.data.network.api.RetrofitClient
import ar.edu.itba.example.api.data.repository.CycleExercisesRepository
import ar.edu.itba.example.api.data.repository.RoutineCyclesRepository
import ar.edu.itba.example.api.data.repository.RoutineRepository
import ar.edu.itba.example.api.data.repository.SportRepository
import ar.edu.itba.example.api.data.repository.UserRepository
import ar.edu.itba.example.api.util.SessionManager

class MyApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(sessionManager, RetrofitClient.getApiUserService(this))

    private val sportRemoteDataSource: SportRemoteDataSource
        get() = SportRemoteDataSource(RetrofitClient.getApiSportService(this))

    private val routineRemoteDataSource : RoutineRemoteDataSource
        get() = RoutineRemoteDataSource(RetrofitClient.getApiRoutineService(this))

    private val routineCyclesRemoteDataSource : RoutineCyclesRemoteDataSource
        get() = RoutineCyclesRemoteDataSource(RetrofitClient.getApiRoutineCyclesService(this))

    private val cycleExercisesRemoteDataSource : CycleExercisesRemoteDataSource
        get() = CycleExercisesRemoteDataSource(RetrofitClient.getApiCycleExercisesService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val sportRepository: SportRepository
        get() = SportRepository(sportRemoteDataSource)

    val routineRepository: RoutineRepository
        get() = RoutineRepository(routineRemoteDataSource)

    val routineCyclesRepository: RoutineCyclesRepository
        get() = RoutineCyclesRepository(routineCyclesRemoteDataSource)

    val cycleExercisesRepository : CycleExercisesRepository
        get() = CycleExercisesRepository(cycleExercisesRemoteDataSource)
}