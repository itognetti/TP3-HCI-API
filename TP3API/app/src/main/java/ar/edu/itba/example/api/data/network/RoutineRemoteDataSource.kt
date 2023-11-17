package ar.edu.itba.example.api.data.network

import ar.edu.itba.example.api.data.network.api.ApiRoutineService
import ar.edu.itba.example.api.data.network.model.NetworkPagedContent
import ar.edu.itba.example.api.data.network.model.NetworkRoutine

class RoutineRemoteDataSource (
    private val apiRoutineService: ApiRoutineService
) : RemoteDataSource() {

    suspend fun getRoutines(page: Int) : NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse {
            apiRoutineService.getRoutines(page)
        }
    }

    suspend fun getFilteredRoutines(page: Int, order: String, direction: String) : NetworkPagedContent<NetworkRoutine>{
        return handleApiResponse {
            apiRoutineService.getFilteredRoutines(page, order, direction)
        }
    }

    suspend fun getRoutine(routineId: Int) : NetworkRoutine {
        return handleApiResponse {
            apiRoutineService.getRoutine(routineId)
        }
    }
}