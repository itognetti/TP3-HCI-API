package ar.edu.itba.example.api.data.repository

import ar.edu.itba.example.api.data.model.Routine
import ar.edu.itba.example.api.data.network.RoutineRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutineRepository(
    private val remoteDataSource: RoutineRemoteDataSource
) {
    private val routineMutex = Mutex()
    private var routines: List<Routine> = emptyList()

    suspend fun getRoutines(refresh: Boolean = false, orderBy: String) : List<Routine> {
        if (refresh || routines.isEmpty()){
            val result = remoteDataSource.getRoutines(orderBy)
            routineMutex.withLock {
                this.routines = result.content.map { it.asModel() }
            }
        }
        return routineMutex.withLock{ this.routines }
    }

    suspend fun getRoutine(routineId: Int) : Routine {
        return remoteDataSource.getRoutine(routineId).asModel()
    }
}