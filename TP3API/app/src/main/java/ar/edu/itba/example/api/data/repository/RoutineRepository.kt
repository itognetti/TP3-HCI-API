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

    suspend fun getRoutines(refresh: Boolean = false) : List<Routine> {
        var page = 0
        if (refresh || routines.isEmpty()){
            this.routines = emptyList()
            do {
                val result = remoteDataSource.getRoutines(page)
                routineMutex.withLock {
                    this.routines = this.routines.plus(result.content.map { it.asModel() })
                }
                page++
            }while(!result.isLastPage)
        }
        return routineMutex.withLock{ this.routines }
    }

    suspend fun getFilteredRoutines(order: String, direction: String) : List<Routine> {
        var page = 0
        this.routines = emptyList()
        do {
            val result = remoteDataSource.getFilteredRoutines(page, order, direction)
            routineMutex.withLock{
                this.routines = this.routines.plus(result.content.map { it.asModel() })
            }
            page++
        } while(!result.isLastPage)
        return routineMutex.withLock{ this.routines }
    }

    suspend fun getRoutine(routineId: Int) : Routine {
        return remoteDataSource.getRoutine(routineId).asModel()
    }
}