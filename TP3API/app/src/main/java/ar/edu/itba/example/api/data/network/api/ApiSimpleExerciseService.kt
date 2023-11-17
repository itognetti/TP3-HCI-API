package ar.edu.itba.example.api.data.network.api

import ar.edu.itba.example.api.data.network.model.NetworkPagedContent
import ar.edu.itba.example.api.data.network.model.NetworkSimpleExercise
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiSimpleExerciseService {
    @GET("exercises")
    suspend fun getExercises(@Query("page") page: Int) : Response<NetworkPagedContent<NetworkSimpleExercise>>

    @GET("exercises/{exerciseId}")
    suspend fun getExercise(@Path("exerciseId") exerciseId: Int) : Response<NetworkSimpleExercise>
}

