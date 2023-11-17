package ar.edu.itba.example.api.data.repository

import ar.edu.itba.example.api.data.model.Cycle
import ar.edu.itba.example.api.data.network.RoutineCyclesRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutineCyclesRepository(
    private val remoteDataSource: RoutineCyclesRemoteDataSource
) {
    private var routineCyclesMutex = Mutex()
    private var routineCycles: List<Cycle> = emptyList()

    suspend fun getRoutineCycles(routineId: Int, refresh: Boolean = false) : List<Cycle> {
        var page = 0
        if(refresh || routineCycles.isEmpty()){
            this.routineCycles = emptyList()
            do {
                val result = remoteDataSource.getRoutineCycles(routineId, page)
                routineCyclesMutex.withLock {
                    this.routineCycles = this.routineCycles.plus(result.content.map { it.asModel() })
                }
                page++
            } while(!result.isLastPage)
        }
        return routineCyclesMutex.withLock { this.routineCycles }
    }
}