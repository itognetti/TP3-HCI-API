package ar.edu.itba.example.api.data.network

import ar.edu.itba.example.api.data.network.api.ApiCycleExercisesService
import ar.edu.itba.example.api.data.network.model.NetworkCycleExercise
import ar.edu.itba.example.api.data.network.model.NetworkPagedContent

class CycleExercisesRemoteDataSource (
    private val cycleExerciseService : ApiCycleExercisesService
) : RemoteDataSource() {

    suspend fun getCycleExercises(cycleId : Int) : NetworkPagedContent<NetworkCycleExercise> {
        return handleApiResponse {
            cycleExerciseService.getCycleExercises(cycleId)
        }
    }
}