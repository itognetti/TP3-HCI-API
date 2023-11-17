package ar.edu.itba.example.api.data.repository

import ar.edu.itba.example.api.data.model.CycleExercise
import ar.edu.itba.example.api.data.network.CycleExercisesRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class CycleExercisesRepository(
    private val remoteDataSource: CycleExercisesRemoteDataSource
) {
    private var cycleExercisesMutex = Mutex()
    private var cycleExercises: List<CycleExercise> = emptyList()

    suspend fun getCycleExercises(cycleId: Int, refresh: Boolean = false) : List<CycleExercise> {
        if(refresh || cycleExercises.isEmpty()){
            val result = remoteDataSource.getCycleExercises(cycleId)
            cycleExercisesMutex.withLock {
                this.cycleExercises = result.content.map { it.asModel() }
            }
        }
        return cycleExercisesMutex.withLock{ this.cycleExercises }
    }
}