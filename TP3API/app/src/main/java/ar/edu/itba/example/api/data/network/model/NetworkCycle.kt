package ar.edu.itba.example.api.data.network.model

import ar.edu.itba.example.api.data.model.Cycle
import com.google.gson.annotations.SerializedName


class NetworkCycle (

    @SerializedName("id"          ) var id          : Int?    = null,
    @SerializedName("name"        ) var name        : String,
    @SerializedName("detail"      ) var detail      : String? = null,
    @SerializedName("type"        ) var type        : String? = null,
    @SerializedName("order"       ) var order       : Int?    = null,
    @SerializedName("repetitions" ) var repetitions : Int?    = null,
    @SerializedName("metadata"    ) var metadata    : String? = null

) {
    fun asModel() : Cycle {
        return Cycle(
            id = id,
            name = name,
            repetitions = repetitions
        )
    }
}