package ar.edu.itba.example.api.data.network.model

import ar.edu.itba.example.api.data.model.Exercise
import com.google.gson.annotations.SerializedName
import java.util.Date

class NetworkExercise(

    @SerializedName("id"     ) var id     : Int,
    @SerializedName("name"   ) var name   : String,
    @SerializedName("detail" ) var detail : String? = null,
    @SerializedName("type"   ) var type   : String,
    @SerializedName("date"   ) var date   : Date?    = null,
    @SerializedName("order"  ) var order  : Int

) {
    fun asModel() : Exercise {
        return Exercise(
            id = id,
            name = name,
            detail = detail,
            type = type,
            date = date,
            order = order
        )
    }
}