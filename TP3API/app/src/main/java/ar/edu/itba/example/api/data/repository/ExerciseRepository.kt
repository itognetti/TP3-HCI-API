package ar.edu.itba.example.api.data.repository

import ar.edu.itba.example.api.data.model.SimpleExercise
import ar.edu.itba.example.api.data.network.ExerciseRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class ExerciseRepository(
    private val remoteDataSource: ExerciseRemoteDataSource
) {
    private val exerciseMutex = Mutex()
    private var exercises: List<SimpleExercise> = emptyList()

    suspend fun getExercises(refresh: Boolean = false) : List<SimpleExercise> {
        var page = 0
        if (refresh || exercises.isEmpty()){
            this.exercises = emptyList()
            do {
                val result = remoteDataSource.getExercises(page)
                exerciseMutex.withLock {
                    this.exercises = this.exercises.plus(result.content.map { it.asModel() })
                }
                page++
            } while(!result.isLastPage)
        }
        return exerciseMutex.withLock{ this.exercises }
    }

    suspend fun getExercise(exerciseId: Int) : SimpleExercise {
        return remoteDataSource.getExercise(exerciseId).asModel()
    }
}