package ar.edu.itba.example.api.data.network.model

import ar.edu.itba.example.api.data.model.Exercise
import com.google.gson.annotations.SerializedName
import java.util.Date

data class NetworkExercise(

    @SerializedName("id"        ) var id     : Int?    = null,
    @SerializedName("name"      ) var name   : String? = null,
    @SerializedName("detail"    ) var detail : String? = null,
    @SerializedName("type"      ) var type   : String? = null,
    @SerializedName("date"      ) var date   : Date?    = null,
    @SerializedName("metadata"  ) var metadata  : Unit? = null

) {
    fun asModel() : Exercise {
        return Exercise(
            name = name,
        )
    }
}