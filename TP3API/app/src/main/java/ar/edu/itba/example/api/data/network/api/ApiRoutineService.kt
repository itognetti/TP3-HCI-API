package ar.edu.itba.example.api.data.network.api

import ar.edu.itba.example.api.data.network.model.NetworkPagedContent
import ar.edu.itba.example.api.data.network.model.NetworkRoutine
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRoutineService {
    @GET("routines")
    suspend fun getRoutines(@Query("page") page: Int) : Response<NetworkPagedContent<NetworkRoutine>>

    @GET("routines")
    suspend fun getFilteredRoutines(@Query("page") page: Int, @Query("orderBy") orderBy: String,
                                    @Query("direction") direction: String) : Response<NetworkPagedContent<NetworkRoutine>>

    @GET("routines/{routineId}")
    suspend fun getRoutine(@Path("routineId") routineId: Int) : Response<NetworkRoutine>
}