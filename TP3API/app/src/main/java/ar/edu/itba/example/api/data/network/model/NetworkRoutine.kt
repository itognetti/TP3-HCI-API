package ar.edu.itba.example.api.data.network.model

import ar.edu.itba.example.api.data.model.Routine
import com.google.gson.annotations.SerializedName
import java.util.Date

data class NetworkRoutine (

    @SerializedName("id"         ) var id         : Int,
    @SerializedName("name"       ) var name       : String,
    @SerializedName("detail"     ) var detail     : String?   = null,
    @SerializedName("date"       ) var date       : Date?      = null,
    @SerializedName("score"      ) var score      : Int?      = null,
    @SerializedName("isPublic"   ) var isPublic   : Boolean?  = null,
    @SerializedName("difficulty" ) var difficulty : String?   = null,
    @SerializedName("user"       ) var user       : NetworkUser?     = null,          //desde el converter estaba User()
    @SerializedName("category"   ) var category   : NetworkCategory,
    @SerializedName("metadata"   ) var metadata   : String?   = null
) {
    fun asModel() : Routine {
        return Routine(
            id = id,
            name = name,
            detail = detail,
            date = date,
            score = score,
            difficulty = difficulty,
            user = user,
            category = category
        )
    }
}