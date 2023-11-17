package ar.edu.itba.example.api.data.network

import ar.edu.itba.example.api.data.network.api.ApiRoutineCyclesService
import ar.edu.itba.example.api.data.network.model.NetworkCycle
import ar.edu.itba.example.api.data.network.model.NetworkPagedContent

class RoutineCyclesRemoteDataSource(
    private val apiRoutineCyclesService: ApiRoutineCyclesService
) : RemoteDataSource() {

    suspend fun getRoutineCycles(routineId: Int, page: Int) : NetworkPagedContent<NetworkCycle> {
        return handleApiResponse {
            apiRoutineCyclesService.getRoutineCycles(routineId, page)
        }
    }
}