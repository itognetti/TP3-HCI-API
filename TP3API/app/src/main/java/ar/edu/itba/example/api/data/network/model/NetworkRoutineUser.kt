package ar.edu.itba.example.api.data.network.model

import ar.edu.itba.example.api.data.model.RoutineUser
import com.google.gson.annotations.SerializedName
import java.util.Date

class NetworkRoutineUser (
    @SerializedName("id"           ) var id           : Int?    = null,
    @SerializedName("username"     ) var username     : String? = null,
    @SerializedName("gender"       ) var gender      : String? = null,
    @SerializedName("avatarUrl"    ) var avatarUrl    : String? = null,
    @SerializedName("date"         ) var date         : Date?    = null,
    @SerializedName("lastActivity" ) var lastActivity : Date?    = null
){
    fun asModel() : RoutineUser {
        return RoutineUser(
            id = id,
            username = username,
            gender = gender,
            avatarUrl = avatarUrl,
            date = date,
            lastActivity = lastActivity
        )
    }
}