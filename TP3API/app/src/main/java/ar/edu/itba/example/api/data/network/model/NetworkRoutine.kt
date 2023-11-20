package ar.edu.itba.example.api.data.network.model

import ar.edu.itba.example.api.data.model.Routine
import com.google.gson.annotations.SerializedName
import java.util.Date

class NetworkRoutine (
    @SerializedName("id"         ) var id         : Int?,
    @SerializedName("name"       ) var name       : String,
    @SerializedName("detail"     ) var detail     : String?   = null,
    @SerializedName("date"       ) var date       : Date?      = null,
    @SerializedName("score"      ) var score      : Int?      = null,
    @SerializedName("isPublic"   ) var isPublic   : Boolean,
    @SerializedName("difficulty" ) var difficulty : String,
    @SerializedName("user"       ) var user       : NetworkRoutineUser? = NetworkRoutineUser(),
    @SerializedName("category"   ) var category   : NetworkCategory? = NetworkCategory(),
    @SerializedName("metadata"   ) var metadata   : Unit?   = null
) {
    fun asModel() : Routine {
        return Routine(
            id = id,
            name = name,
            detail = detail,
            date = date,
            score = score,
            isPublic = isPublic,
            difficulty = difficulty,
            user = user?.asModel(),
        )
    }
}