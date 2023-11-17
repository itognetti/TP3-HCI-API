package ar.edu.itba.example.api.data.network.model

import ar.edu.itba.example.api.data.model.CycleExercise
import ar.edu.itba.example.api.data.model.CycleExercises
import com.google.gson.annotations.SerializedName

class NetworkCycleExercises(

    @SerializedName("totalCount" ) var totalCount : Int? = null,
    @SerializedName("orderBy"    ) var orderBy : String,
    @SerializedName("direction"  ) var direction : String,
    @SerializedName("content"    ) var content : List<CycleExercise> = emptyList(),
    @SerializedName("size"       ) var size : Int? = null,
    @SerializedName("page"       ) var page: Int? = null,
    @SerializedName("isLastPage" ) var isLastPage : Boolean
) {
    fun asModel() : CycleExercises {
        return CycleExercises(
            content = content,
            orderBy = orderBy,
            direction = direction,
            isLastPage = isLastPage
        )
    }
}