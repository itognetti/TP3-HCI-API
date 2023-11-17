package ar.edu.itba.example.api.data.network

import ar.edu.itba.example.api.data.network.api.ApiSimpleExerciseService
import ar.edu.itba.example.api.data.network.model.NetworkPagedContent
import ar.edu.itba.example.api.data.network.model.NetworkSimpleExercise

class ExerciseRemoteDataSource(
    private val apiSimpleExerciseService: ApiSimpleExerciseService
) : RemoteDataSource() {

    suspend fun getExercises(page: Int) : NetworkPagedContent<NetworkSimpleExercise> {
        return handleApiResponse {
            apiSimpleExerciseService.getExercises(page)
        }
    }

    suspend fun getExercise(exerciseId: Int) : NetworkSimpleExercise {
        return handleApiResponse {
            apiSimpleExerciseService.getExercise(exerciseId)
        }
    }
}

