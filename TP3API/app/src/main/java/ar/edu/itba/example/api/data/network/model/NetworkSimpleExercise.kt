package ar.edu.itba.example.api.data.network.model

import ar.edu.itba.example.api.data.model.SimpleExercise
import com.google.gson.annotations.SerializedName
import java.util.Date

class NetworkSimpleExercise(

    @SerializedName("id"        ) var id: Int,
    @SerializedName("name"      ) var name: String,
    @SerializedName("detail"    ) var detail: String? = null,
    @SerializedName("type"      ) var type: String,
    @SerializedName("date"      ) var date: Date? = null,
    @SerializedName("metadata"  ) var metadata: String? = null

) {
    fun asModel() : SimpleExercise {
        return SimpleExercise(
            id = id,
            name = name,
            detail = detail,
            type = type
        )
    }
}