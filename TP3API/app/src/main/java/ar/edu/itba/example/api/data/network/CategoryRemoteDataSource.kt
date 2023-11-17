package ar.edu.itba.example.api.data.network

import ar.edu.itba.example.api.data.network.api.ApiCategoryService
import ar.edu.itba.example.api.data.network.model.NetworkCategory
import ar.edu.itba.example.api.data.network.model.NetworkPagedContent

class CategoryRemoteDataSource(
    private val apiCategoryService : ApiCategoryService
) : RemoteDataSource() {

    suspend fun getCategories() : NetworkPagedContent<NetworkCategory> {
        return handleApiResponse {
            apiCategoryService.getCategories()
        }
    }

    suspend fun getCategory(categoryId : Int) : NetworkCategory {
        return handleApiResponse {
            apiCategoryService.getCategory(categoryId)
        }
    }

    suspend fun addCategory(category: NetworkCategory) : NetworkCategory {
        return handleApiResponse {
            apiCategoryService.addCategory(category)
        }
    }
}