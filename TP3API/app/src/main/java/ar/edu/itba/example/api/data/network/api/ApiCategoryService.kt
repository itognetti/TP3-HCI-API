package ar.edu.itba.example.api.data.network.api

import ar.edu.itba.example.api.data.network.model.NetworkCategory
import ar.edu.itba.example.api.data.network.model.NetworkPagedContent
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiCategoryService {
    @GET("categories")
    suspend fun getCategories() : Response<NetworkPagedContent<NetworkCategory>>

    @GET("categories/{categoryId}")
    suspend fun getCategory(@Path("categoryId") categoryId: Int) : Response<NetworkCategory>

    @POST("categories")
    suspend fun addCategory(@Body category: NetworkCategory) : Response<NetworkCategory>
}